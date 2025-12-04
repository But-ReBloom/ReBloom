package com.but.rebloom.userachievement.update;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.TierName;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.DefaultTierUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserAchievementToSuccessTest {
    @Mock
    private AchievementRepository achievementRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserAchievementRepository userAchievementRepository;
    @Mock
    private DefaultTierUseCase defaultTierUseCase;
    @InjectMocks
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    @Test
    @DisplayName("유저 업데이트 성공 전환 테스트 - 성공(이미 업적을 깨둔경우)")
    public void updateUserAchievementToSuccessSuccessIfAlreadySucceedTest() {
        // Given
        String userEmail = "user@email.com";
        String achievementTitle = "achievementTitle";

        UserAchievement mockUserAchievement = UserAchievement.builder()
                .achievementId(1L)
                .userEmail(userEmail)
                .isSuccess(true)
                .build();

        when(userAchievementRepository.findByUserEmailAndAchievement_AchievementTitle(userEmail, achievementTitle))
                .thenReturn(Optional.of(mockUserAchievement));

        // When & Then
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(userEmail, achievementTitle);
    }

    @Test
    @DisplayName("유저 업데이트 성공 전환 테스트 - 성공(업적을 깨두지 않은 경우)")
    public void updateUserAchievementToSuccessSuccessIfNotSucceedInAdvanceTest() {
        // Given
        String userEmail = "user@email.com";
        String achievementTitle = "achievementTitle";

        Achievement mockAchievement = Achievement.builder()
                .achievementId(1L)
                .achievementTitle(achievementTitle)
                .achievementRewardPoint(100)
                .achievementRewardTierPoint(10)
                .build();

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .userTierPoint(0)
                .userTier(TierName.IRON)
                .build();

        UserAchievement mockUserAchievement = UserAchievement.builder()
                .achievement(mockAchievement)
                .user(mockUser)
                .isSuccess(false)
                .userAchievementProgress(0f)
                .build();

        when(userAchievementRepository.findByUserEmailAndAchievement_AchievementTitle(userEmail, achievementTitle))
                .thenReturn(Optional.of(mockUserAchievement));
        when(achievementRepository.findByAchievementTitle(achievementTitle))
                .thenReturn(Optional.of(mockAchievement));
        when(userRepository.findByUserEmail(userEmail))
                .thenReturn(Optional.of(mockUser));
        when(defaultTierUseCase.getTierEnumByPoint(anyInt()))
                .thenReturn(Optional.of(TierName.IRON));

        // When
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(userEmail, achievementTitle);

        // Then
        assertThat(mockUserAchievement.getUserAchievementProgress()).isEqualTo(100f);
        assertThat(mockUserAchievement.getIsSuccess()).isTrue();
    }
}
