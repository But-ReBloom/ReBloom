package com.but.rebloom.userachievement.find;

import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FinaAllUserAchievementsByUserEmailTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private UserAchievementRepository userAchievementRepository;
    @InjectMocks
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    @Test
    @DisplayName("전체 유저 업적 조회(아이디) - 성공")
    public void findAllUserAchievementsByUserEmailSuccessTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("userEmail")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        UserAchievement mockUserAchievement = UserAchievement.builder()
                .achievementId(1L)
                .user(mockUser)
                .build();

        List<UserAchievement> mockUserAchievements = List.of(mockUserAchievement);

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(userAchievementRepository.findAllByUserEmail(mockUser.getUserEmail()))
                .thenReturn(mockUserAchievements);

        // When
        List<UserAchievement> userAchievements = defaultUserAchievementUseCase.finaAllUserAchievementsByUserEmail();

        // Then
        assertThat(userAchievements).isNotEmpty();
    }
}
