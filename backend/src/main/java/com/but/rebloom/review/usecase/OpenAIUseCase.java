package com.but.rebloom.review.usecase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

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

    /* AI 응답에서 JSON 배열만 추출 */
    private String cleanJsonResponse(String response) {
        int start = response.indexOf("[");
        int end = response.lastIndexOf("]") + 1;

        if (start >= 0 && end > start) {
            return response.substring(start, end);
        }
        return response;
    }

    /* 기본 질문 목록 (AI 실패 시 사용) */
    private List<Map<String, String>> getDefaultQuestions() {
        return List.of(
                Map.of("question", "이 활동을 하면서 가장 기억에 남았던 순간은 무엇인가요?", "dimension", "social"),
                Map.of("question", "이 활동을 통해 새롭게 배운 점이 있다면 무엇인가요?", "dimension", "learning"),
                Map.of("question", "이 활동을 하기 위해 어떤 준비나 계획을 하셨나요?", "dimension", "planning"),
                Map.of("question", "활동하는 동안 어느 정도 몰입할 수 있었나요?", "dimension", "focus"),
                Map.of("question", "이 활동이 창의적인 표현이나 아이디어에 어떤 영향을 주었나요?", "dimension", "creativity")
        );
    }
}
