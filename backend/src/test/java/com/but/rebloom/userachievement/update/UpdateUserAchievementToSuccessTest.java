package com.but.rebloom.userachievement.update;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.DefaultTierUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
}
