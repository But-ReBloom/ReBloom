package com.but.rebloom.domain.review.usecase;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import com.but.rebloom.domain.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import com.but.rebloom.domain.review.domain.ActivityReview;
import com.but.rebloom.domain.review.domain.ActivityReviewResult;
import com.but.rebloom.domain.review.dto.request.ReviewAnswerRequest;
import com.but.rebloom.domain.review.repository.ActivityReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActivityReviewUseCase {

    private final ActivityReviewRepository activityReviewRepository;
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final HobbyTestUseCase hobbyTestUseCase;
    private final OpenAiChatModel openAiChatModel;
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 질문 5개 생성
    public ActivityReview createReviewQuestion(Long hobbyId) {
        Hobby hobby = hobbyRepository.findByHobbyId(hobbyId)
                .orElseThrow(() -> new HobbyNotFoundException("취미를 찾을 수 없음"));

        User currentUser = findCurrentUserUseCase.getCurrentUser();

        String prompt = """
                당신은 활동 리뷰 질문 생성기입니다.
                다음 취미 활동: "%s" 를 기반으로 사용자의 취향 카테고리 별로 평가할 질문을 생성하세요.

                취향 카테고리:
                - Society
                - Learning
                - Planning
                - Focus
                - Creativity

                각각의 카테고리에 대해 한 문장짜리 질문을 만들어주세요.

                출력 형식:
                Society: 질문
                Learning: 질문
                Planning: 질문
                Focus: 질문
                Creativity: 질문
                """.formatted(hobby.getHobbyName());

        String response = openAiChatModel.call(prompt);

        String mergedQuestions = """
                Society: %s
                Learning: %s
                Planning: %s
                Focus: %s
                Creativity: %s
                """
                .formatted(
                        extractLine(response, "Society:"),
                        extractLine(response, "Learning:"),
                        extractLine(response, "Planning:"),
                        extractLine(response, "Focus:"),
                        extractLine(response, "Creativity:")
                );

        ActivityReview activityReview = ActivityReview.builder()
                .hobby(hobby)
                .reviewQuestion(mergedQuestions)
                .createdAt(LocalDateTime.now())
                .build();

        return activityReviewRepository.save(activityReview);
    }


    // 답변 벡터화 & 저장 & 추천
    public ActivityReviewResult answerReview(ReviewAnswerRequest reviewAnswerRequest) {

        User user = userRepository.findByUserEmail(reviewAnswerRequest.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없음"));

        String prompt = """
                다음 5개의 질문 답변을 바탕으로 사용자의 카테고리 별 취향 점수를 -2에서 +2 사이 값으로 산출하세요.
                반드시 아래 형식 그대로 출력하세요:

                Society: 값
                Learning: 값
                Planning: 값
                Focus: 값
                Creativity: 값

                Society 답변: %s
                Learning 답변: %s
                Planning 답변: %s
                Focus 답변: %s
                Creativity 답변: %s
                """.formatted(
                reviewAnswerRequest.getSocialAnswer(),
                reviewAnswerRequest.getLearningAnswer(),
                reviewAnswerRequest.getPlanningAnswer(),
                reviewAnswerRequest.getFocusAnswer(),
                reviewAnswerRequest.getCreativityAnswer()
        );

        String aiResult = openAiChatModel.call(prompt);

        double s = extract(aiResult, "Society:");
        double l = extract(aiResult, "Learning:");
        double p = extract(aiResult, "Planning:");
        double f = extract(aiResult, "Focus:");
        double c = extract(aiResult, "Creativity:");

        // 벡터 누적
        user.setUserSocialScore(user.getUserSocialScore() + s);
        user.setUserLearningScore(user.getUserLearningScore() + l);
        user.setUserPlanningScore(user.getUserPlanningScore() + p);
        user.setUserFocusScore(user.getUserFocusScore() + f);
        user.setUserCreativityScore(user.getUserCreativityScore() + c);

        userRepository.save(user);

        // 취미 추천
        UserAnswerRequest answerScore = UserAnswerRequest.builder()
                .socialScore(user.getUserSocialScore())
                .learningScore(user.getUserLearningScore())
                .planningScore(user.getUserPlanningScore())
                .focusScore(user.getUserFocusScore())
                .creativityScore(user.getUserCreativityScore())
                .build();

        Map<List<HobbyScore>, List<Channel>> result = hobbyTestUseCase.findUserHobbies(answerScore);

        List<HobbyScore> hobbyScores = result.keySet().iterator().next();
        List<Hobby> hobbies = hobbyScores.stream().map(HobbyScore::getHobby).toList();

        Hobby hobby = hobbyRepository.findByHobbyId(reviewAnswerRequest.getHobbyId())
                .orElseThrow(() -> new HobbyNotFoundException("취미를 찾을 수 없음"));

        ActivityReview review = ActivityReview.builder()
                .user(user)
                .hobby(hobby)
                .reviewAnswer("""
                        Society: %s
                        Learning: %s
                        Planning: %s
                        Focus: %s
                        Creativity: %s
                        """.formatted(
                        reviewAnswerRequest.getSocialAnswer(),
                        reviewAnswerRequest.getLearningAnswer(),
                        reviewAnswerRequest.getPlanningAnswer(),
                        reviewAnswerRequest.getFocusAnswer(),
                        reviewAnswerRequest.getCreativityAnswer()
                ))
                .createdAt(LocalDateTime.now())
                .build();

        activityReviewRepository.save(review);

        String review1 = "첫 리뷰";
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(user.getUserEmail(), review1);
        String review5 = "리뷰의 달인";
        defaultUserAchievementUseCase.updateUserAchievementProgress(user.getUserEmail(), review5, 100f / 5f);

        String survey1 = "첫 설문조사!";
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(user.getUserEmail(), survey1);
        String survey10 = "설문 조사의 신!";
        defaultUserAchievementUseCase.updateUserAchievementProgress(user.getUserEmail(), survey10, 100f / 10f);

        return new ActivityReviewResult(review, user, hobbies);
    }


    // 숫자 파싱
    private double extract(String text, String key) {
        try {
            return Double.parseDouble(
                    text.split(key)[1]
                            .trim()
                            .split("\\n")[0]
                            .replaceAll("[^0-9.\\-]", "")
            );
        } catch (Exception e) {
            return 0.0;
        }
    }

    // 라인 파싱
    private String extractLine(String text, String key) {
        try {
            return text.split(key)[1]
                    .trim()
                    .split("\n")[0]
                    .replaceAll("^[\\-: ]+", "")
                    .trim();
        } catch (Exception e) {
            return "";
        }
    }
}
