package com.but.rebloom.userachievement.update;

import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.exception.UserAchievementNotFoundException;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UpdateUserAchievementProgressTest {
    @Mock
    private UserAchievementRepository userAchievementRepository;
    @InjectMocks
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    @Test
    @DisplayName("유저 업적 진행도 갱신 테스트 - 성공(이미 성공해둔 경우)")
    public void updateUserAchievementProgressSuccessIfAlreadySucceedTest() {
        // Given
        String userEmail = "test@test.com";
        String achievementTitle = "achievementTitle";
        float progress = 100f;

        UserAchievement mockUserAchievement = UserAchievement.builder()
                .isSuccess(true)
                .build();

        when(userAchievementRepository.findByUserEmailAndAchievement_AchievementTitle(userEmail, achievementTitle))
                .thenReturn(Optional.of(mockUserAchievement));

        // When & Then
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, achievementTitle, progress);
    }

    @Test
    @DisplayName("유저 업적 진행도 갱신 테스트 - 성공(성공해두지 않은 경우)")
    public void updateUserAchievementProgressSuccessIfNotSucceedInAdvanceTest() {
        // Given
        String userEmail = "test@test.com";
        String achievementTitle = "achievementTitle";
        float progress = 10f;

        UserAchievement mockUserAchievement = UserAchievement.builder()
                .isSuccess(false)
                .userAchievementProgress(5f)
                .build();

        when(userAchievementRepository.findByUserEmailAndAchievement_AchievementTitle(userEmail, achievementTitle))
                .thenReturn(Optional.of(mockUserAchievement));

        // When
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, achievementTitle, progress);

        // Then
        assertThat(mockUserAchievement.getUserAchievementProgress()).isEqualTo(15f);
    }

    @Test
    @DisplayName("유저 업적 진행도 갱신 테스트 - 유저 업적 조회 실패로 인한 실패")
    public void updateUserAchievementProgressFailByUserAchievementNotFoundTest() {
        // Given
        String userEmail = "test@test.com";
        String achievementTitle = "achievementTitle";
        float progress = 10f;

        // When & Then
        assertThrows(UserAchievementNotFoundException.class,
                () -> defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, achievementTitle, progress));
    }
}
