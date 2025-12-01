package com.but.rebloom.domain.review.usecase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIUseCase {

    private final ChatClient.Builder chatClientBuilder;
    private final ObjectMapper objectMapper;

    // 리뷰 질문 생성
    public List<Map<String, String>> generateReviewQuestions(String hobbyName) {

        String prompt = String.format("""
                당신은 취미 활동 분석 전문가입니다. '%s' 활동을 체험한 사람에게 물어볼 5가지 주관식 질문을 생성해주세요.

                각 질문은 다음 성향 차원 중 하나와 연결되어야 합니다:
                1. social (사회성): 사람들과의 교류, 협력
                2. learning (학습): 새로운 지식이나 기술 습득
                3. planning (계획성): 체계적 접근, 준비
                4. focus (집중력): 몰입, 지속성
                5. creativity (창의성): 창작, 자유로운 표현

                다음 JSON 형식으로만 응답해주세요 (다른 설명 없이 JSON만):
                [
                  {"question": "질문 내용1", "dimension": "social"},
                  {"question": "질문 내용2", "dimension": "learning"},
                  {"question": "질문 내용3", "dimension": "planning"},
                  {"question": "질문 내용4", "dimension": "focus"},
                  {"question": "질문 내용5", "dimension": "creativity"}
                ]
                """, hobbyName);

        try {
            ChatClient chatClient = chatClientBuilder
                    .defaultOptions(OpenAiChatOptions.builder()
                            .withTemperature(0.7)
                            .build())
                    .build();

            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            log.info("AI 응답: {}", response);

            // JSON만 추출
            String cleanedResponse = cleanJsonResponse(response);

            return objectMapper.readValue(
                    cleanedResponse,
                    new TypeReference<List<Map<String, String>>>() {}
            );

        } catch (Exception e) {
            log.error("리뷰 질문 생성 중 오류 발생", e);
            // 오류 시 기본 질문 반환
            return getDefaultQuestions();
        }
    }

    // 답변을 벡터로 변환
    public Map<String, Double> convertAnswerToVector(String question, String answer, String dimension) {
        String prompt = String.format(
                """
                다음 질문과 답변을 분석하여 '%s' 차원의 점수 변화량을 -2에서 +2 사이로 평가해주세요.
                
                질문: %s
                답변: %s
                
                평가 기준:
                - 매우 부정적/낮음: -2
                - 부정적/낮음: -1
                - 보통/중립: 0
                - 긍정적/높음: +1
                - 매우 긍정적/높음: +2
                
                다음 JSON 형식으로만 응답해주세요 (다른 설명 없이 JSON만):
                {"dimension": "%s", "score": 점수}
                """,
                dimension, question, answer, dimension
        );

        try {
            ChatClient chatClient = chatClientBuilder
                    .defaultOptions(OpenAiChatOptions.builder()
                            .withTemperature(0.3)
                            .build())
                    .build();

            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            log.info("AI 벡터 분석 응답: {}", response);

            // JSON 파싱
            String cleanedResponse = cleanJsonResponse(response);
            Map<String, Object> result = objectMapper.readValue(cleanedResponse, Map.class);

            String parsedDimension = (String) result.get("dimension");
            Double score = parseScore(result.get("score"));

            Map<String, Double> vectorMap = new HashMap<>();
            vectorMap.put(parsedDimension, score);
            return vectorMap;

        } catch (Exception e) {
            log.error("벡터 변환 중 오류 발생", e);
            // 기본값 반환
            return Map.of(dimension, 0.0);
        }
    }

    // JSON 응답 정제 (```json ``` 제거)
    private String cleanJsonResponse(String response) {
        return response
                .replaceAll("```json", "")
                .replaceAll("```", "")
                .trim();
    }

    // score 파싱 (Integer 또는 Double 처리)
    private Double parseScore(Object score) {
        if (score instanceof Integer) {
            return ((Integer) score).doubleValue();
        } else if (score instanceof Double) {
            return (Double) score;
        }
        return 0.0;
    }

    // 기본 질문 (AI 호출 실패 시)
    private List<Map<String, String>> getDefaultQuestions() {
        return List.of(
                Map.of("question", "이번 활동에서 다른 사람들과 어떻게 교류했나요?", "dimension", "social"),
                Map.of("question", "새로 배우거나 익힌 것이 있나요?", "dimension", "learning"),
                Map.of("question", "활동을 어떻게 계획하고 준비했나요?", "dimension", "planning"),
                Map.of("question", "활동에 얼마나 집중할 수 있었나요?", "dimension", "focus"),
                Map.of("question", "자신만의 창의적인 시도를 했나요?", "dimension", "creativity")
        );
    }
}
