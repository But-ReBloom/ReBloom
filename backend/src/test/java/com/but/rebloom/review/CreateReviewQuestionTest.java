package com.but.rebloom.review;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.review.domain.ActivityReview;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CreateReviewQuestionTest {
    @Mock
    private HobbyRepository hobbyRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private OpenAiChatModel openAiChatModel;
    @Mock
    private ActivityReviewRepository activityReviewRepository;
    @InjectMocks
    private ActivityReviewUseCase activityReviewUseCase;

    @Test
    @DisplayName("활동 리뷰 질문 생성 테스트 - 성공")
    public void createReviewQuestionSuccessTest() {
        // Given
        Long hobbyId = 1L;

        Hobby mockHobby = Hobby.builder()
                .hobbyId(hobbyId)
                .hobbyName("코딩")
                .build();

        User mockUser = User.builder().build();

        String mockResponse = "mockResponse";

        ActivityReview mockActivityReview = ActivityReview.builder().build();

        when(hobbyRepository.findByHobbyId(hobbyId))
                .thenReturn(Optional.of(mockHobby));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(openAiChatModel.call(anyString()))
                .thenReturn(mockResponse);
        when(activityReviewRepository.save(any(ActivityReview.class)))
                .thenReturn(mockActivityReview);

        // When
        ActivityReview activityReview = activityReviewUseCase.createReviewQuestion(hobbyId);

        // Then
        assertThat(activityReview).isEqualTo(mockActivityReview);
    }

    @Test
    @DisplayName("활동 리뷰 질문 생성 테스트 - 취미 조회 실패로 인한 실패")
    public void createReviewQuestionFailByHobbyNotFoundTest() {
        // Given
        Long hobbyId = 1L;

        // When & Then
        assertThrows(HobbyNotFoundException.class,
                () -> activityReviewUseCase.createReviewQuestion(hobbyId));
    }
}
