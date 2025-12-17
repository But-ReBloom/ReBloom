package com.but.rebloom.review;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import com.but.rebloom.domain.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import com.but.rebloom.domain.review.domain.ActivityReview;
import com.but.rebloom.domain.review.domain.ActivityReviewResult;
import com.but.rebloom.domain.review.dto.request.ReviewAnswerRequest;
import com.but.rebloom.domain.review.exception.ReviewNotFoundException;
import com.but.rebloom.domain.review.repository.ActivityReviewRepository;
import com.but.rebloom.domain.review.usecase.ActivityReviewUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AnswerReviewTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private OpenAiChatModel openAiChatModel;
    @Mock
    private HobbyTestUseCase hobbyTestUseCase;
    @Mock
    private HobbyRepository hobbyRepository;
    @Mock
    private ActivityReviewRepository activityReviewRepository;
    @Mock
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;
    @InjectMocks
    private ActivityReviewUseCase activityReviewUseCase;

    @Test
    @DisplayName("활동 리뷰 테스트 - 성공")
    public void answerReviewSuccessTest() {
        // Given
        ReviewAnswerRequest reviewAnswerRequest = new ReviewAnswerRequest(
                1L,
                "useremail@email.com",
                1L,
                "socialanswer",
                "learninganswer",
                "planninganswer",
                "focusanswer",
                "creativityanswer"
        );

        User mockUser = User.builder()
                .userEmail("useremail@email.com")
                .userSocialScore(0d)
                .userPlanningScore(0d)
                .userFocusScore(0d)
                .userCreativityScore(0d)
                .userLearningScore(0d)
                .build();

        String mockAiResult = "mockAiResult";

        HobbyScore mockHobbyScore = HobbyScore.builder().build();
        List<HobbyScore> mockHobbyScores = List.of(mockHobbyScore);

        Channel mockChannel = Channel.builder().build();
        List<Channel> mockChannels = List.of(mockChannel);

        Map<List<HobbyScore>, List<Channel>> mockResult = Map.of(mockHobbyScores, mockChannels);

        Hobby mockHobby = Hobby.builder().build();

        ActivityReview mockActivityReview = ActivityReview.builder().build();

        when(userRepository.findByUserEmail(reviewAnswerRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(openAiChatModel.call(anyString()))
                .thenReturn(mockAiResult);
        when(userRepository.save(any(User.class)))
                .thenReturn(mockUser);
        when(hobbyTestUseCase.findUserHobbies(any(UserAnswerRequest.class)))
                .thenReturn(mockResult);
        doNothing().when(defaultUserAchievementUseCase).updateUserAchievementToSuccess(anyString(), anyString());
        doNothing().when(defaultUserAchievementUseCase).updateUserAchievementProgress(anyString(), anyString(), anyFloat());
        when(activityReviewRepository.findByReviewIdAndUser_UserEmail(anyLong(), anyString()))
                .thenReturn(Optional.of(mockActivityReview));
        when(activityReviewRepository.save(any(ActivityReview.class)))
                .thenReturn(mockActivityReview);

        // When
        ActivityReviewResult activityReviewResult = activityReviewUseCase.answerReview(reviewAnswerRequest);

        // Then
        assertThat(activityReviewResult).isNotNull();
        assertThat(activityReviewResult.getUser()).isEqualTo(mockUser);
    }

    @Test
    @DisplayName("활동 리뷰 테스트 - 유저 조회 실패로 인한 실패")
    public void answerReviewFailByUserNotFoundTest() {
        // Given
        ReviewAnswerRequest reviewAnswerRequest = new ReviewAnswerRequest(
                1L,
                "useremail@email.com",
                1L,
                "socialanswer",
                "learninganswer",
                "planninganswer",
                "focusanswer",
                "creativityanswer"
        );

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> activityReviewUseCase.answerReview(reviewAnswerRequest));
    }

    @Test
    @DisplayName("활동 리뷰 테스트 - 취미 조회 실패로 인한 실패")
    public void answerReviewFailByHobbyNotFoundTest() {
        // Given
        ReviewAnswerRequest reviewAnswerRequest = new ReviewAnswerRequest(
                1L,
                "useremail@email.com",
                1L,
                "socialanswer",
                "learninganswer",
                "planninganswer",
                "focusanswer",
                "creativityanswer"
        );

        User mockUser = User.builder()
                .userSocialScore(0d)
                .userPlanningScore(0d)
                .userFocusScore(0d)
                .userCreativityScore(0d)
                .userLearningScore(0d)
                .build();

        String mockAiResult = "mockAiResult";

        HobbyScore mockHobbyScore = HobbyScore.builder().build();
        List<HobbyScore> mockHobbyScores = List.of(mockHobbyScore);

        Channel mockChannel = Channel.builder().build();
        List<Channel> mockChannels = List.of(mockChannel);

        Map<List<HobbyScore>, List<Channel>> mockResult = Map.of(mockHobbyScores, mockChannels);

        when(userRepository.findByUserEmail(reviewAnswerRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(openAiChatModel.call(anyString()))
                .thenReturn(mockAiResult);
        when(userRepository.save(any(User.class)))
                .thenReturn(mockUser);
        when(hobbyTestUseCase.findUserHobbies(any(UserAnswerRequest.class)))
                .thenReturn(mockResult);

        // When & Then
        assertThrows(ReviewNotFoundException.class,
                () -> activityReviewUseCase.answerReview(reviewAnswerRequest));
    }
}
