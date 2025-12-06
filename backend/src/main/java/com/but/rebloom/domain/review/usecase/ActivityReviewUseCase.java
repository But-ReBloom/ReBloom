package com.but.rebloom.domain.review.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import com.but.rebloom.domain.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import com.but.rebloom.domain.review.dto.request.CreateReviewQuestionRequest;
import com.but.rebloom.domain.review.dto.request.ReviewAnswerRequest;
import com.but.rebloom.domain.review.dto.response.CreateReviewQuestionResponse;
import com.but.rebloom.domain.review.dto.response.ReviewAnswerResponse;
import com.but.rebloom.domain.review.repository.ActivityReviewRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActivityReviewUseCase {
    private final ActivityReviewRepository repository;
    private final UserRepository userRepository;
    private final HobbyTestUseCase hobbyTestUseCase;
    private OpenAiChatModel openAiChatModel;

    // 질문 5개 생성
    public CreateReviewQuestionResponse createReviewQuestion(Long hobbyId) {
        String prompt = """
                 당신은 활동 리뷰 질문 생성기입니다.
                 다음 취미 활동을 기반으로 사용자의 취향 카테고리 별 평가할 질문을 생성하세요.
                
                취향 카테고리:
                - Society
                - Learning
                - Planning
                - Focus
                - Creativity
                
                각각의 카테고리에 대해 한 문장짜리 질문을 만들어주세요.
                """;

        String response = openAiChatModel.call(prompt);

        //파싱
        String[] lines = response.split("\n");

        return CreateReviewQuestionResponse.builder()
                .socialQuestion(lines[0])
                .learningQuestion(lines[1])
                .planningQuestion(lines[2])
                .focusQuestion(lines[3])
                .creativityQuestion(lines[4])
                .build();
    }

    // 답변 벡터화 & 업데이트 및 추천
    public ReviewAnswerResponse answerReview(ReviewAnswerRequest reviewAnswerRequest) {
        User user = userRepository.findByUserEmail(reviewAnswerRequest.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없음"));

        String prompt = """
                다음 5개의 질문과 답변을 바탕으로 사용자의 카테고리 별 취향 벡터를 -2~+2 범위로 산출하세요.
                형식:
                Society: 값
                Learning: 값
                Planning: 값
                Focus: 값
                Creatiity: 값
                
                Society 답: %s
                Learning 답: %s
                Planning 답: %s
                Focus 답: %s
                Creativity 답: %s
                """.formatted(
                        reviewAnswerRequest.getSocialAnswer(),
                        reviewAnswerRequest.getLearningAnswer(),
                        reviewAnswerRequest.getPlanningAnswer(),
                        reviewAnswerRequest.getFocusAnswer(),
                        reviewAnswerRequest.getCreativityAnswer()
        );
        String aiResult = openAiChatModel.call(prompt);

        // 파싱
        double s = extract(aiResult, "S:");
        double l = extract(aiResult, "L:");
        double p = extract(aiResult, "P:");
        double f = extract(aiResult, "F:");
        double c = extract(aiResult, "C:");

        // 기존 벡터 업데이트
        user.setSocialScore(user.getSocialScore() + s);
        user.setLearningScore(user.getLearningScore() + l);
        user.setPlanningScore(user.getPlanningScore() + p);
        user.setFocusScore(user.getFocusScore() + f);
        user.setCreativityScore(user.getCreativityScore() + c);
        userRepository.save(user);

        // 취미 추천
        double[] userScore = {
                user.getSocialScore(),
                user.getLearningScore(),
                user.getPlanningScore(),
                user.getFocusScore(),
                user.getCreativityScore()
        };

        UserAnswerRequest userAnswerRequest = UserAnswerRequest.builder()
                .socialScore(userScore[0])
                .learningScore(userScore[1])
                .planningScore(userScore[2])
                .focusScore(userScore[3])
                .creativityScore(userScore[4])
                .build();
        Map<List<HobbyScore>, List<Channel>> result =
                hobbyTestUseCase.findUserHobbies(userAnswerRequest);

        List<HobbyScore> hobbyScores = result.keySet().iterator().next();

        // 5) 응답
        return ReviewAnswerResponse.builder()
                .socialScore(user.getSocialScore())
                .learningScore(user.getLearningScore())
                .planningScore(user.getPlanningScore())
                .focusScore(user.getFocusScore())
                .creativityScore(user.getCreativityScore())
                .hobby1(hobbyScores.get(0).getHobby().getHobbyName())
                .hobby2(hobbyScores.get(1).getHobby().getHobbyName())
                .hobby3(hobbyScores.get(2).getHobby().getHobbyName())
                .build();
    }

    private double extract(String text, String key) {
        try {
            return Double.parseDouble(
                    text.split(key)[1]
                            .trim().split("\n")[0]
                            .replace("*","")
            );
        } catch (Exception e) {
            return 0.0;
        }
    }
}