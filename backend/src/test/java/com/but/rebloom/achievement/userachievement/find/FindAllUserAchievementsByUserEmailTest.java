package com.but.rebloom.achievement.userachievement.find;

import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.exception.UserAchievementNotFoundException;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class FindAllUserAchievementsByUserEmailTest {
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

    @Test
    @DisplayName("전체 유저 업적 조회(아이디) - 유저 업적 조회 실패로 인해 실패")
    public void findAllUserAchievementsByUserEmailFailByUserAchievementNotFoundTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("userEmail")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(UserAchievementNotFoundException.class,
                () -> defaultUserAchievementUseCase.finaAllUserAchievementsByUserEmail());
    }
}
