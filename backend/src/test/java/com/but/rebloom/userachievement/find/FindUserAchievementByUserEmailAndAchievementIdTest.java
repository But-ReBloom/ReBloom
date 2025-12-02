package com.but.rebloom.userachievement.find;

import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserAchievementByUserEmailAndAchievementIdTest {
    @Mock
    private UserAchievementRepository userAchievementRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    @Test
    @DisplayName("유저 업적 조회(이메일, 아이디) - 성공")
    public void findUserAchievementByUserEmailAndAchievementIdSuccessTest() {
        // Given
        Long achievementId = 1000L;

        User mockUser = User.builder()
                .userEmail("userEmail")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        UserAchievement mockUserAchievement = UserAchievement.builder()
                        .achievementId(1L)
                        .user(mockUser)
                        .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(userAchievementRepository.findByUserEmailAndAchievementId(mockUser.getUserEmail(), achievementId))
                .thenReturn(Optional.of(mockUserAchievement));

        // When
        UserAchievement userAchievement = defaultUserAchievementUseCase.findUserAchievementByUserEmailAndAchievementId(achievementId);

        // Then
        assertThat(userAchievement).isEqualTo(mockUserAchievement);
    }
}
