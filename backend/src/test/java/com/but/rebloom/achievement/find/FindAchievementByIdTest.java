package com.but.rebloom.achievement.find;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultAchievementUseCase;
import org.junit.jupiter.api.Assertions;
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
public class FindAchievementByIdTest {
    @Mock
    private AchievementRepository achievementRepository;
    @InjectMocks
    private DefaultAchievementUseCase defaultAchievementUseCase;

    @Test
    @DisplayName("업적 조회(아이디) 테스트 - 성공")
    public void findAchievementByIdSuccessTest() {
        // Given
        Long achievementId = 1000L;

        Achievement mockAchievement = Achievement.builder()
                .achievementId(achievementId)
                .achievementTitle("achievementTitle")
                .build();

        when(achievementRepository.findByAchievementId(achievementId))
                .thenReturn(Optional.of(mockAchievement));

        // When
        Achievement achievement = defaultAchievementUseCase.findAchievementById(achievementId);

        // Then
        assertThat(achievement).isEqualTo(mockAchievement);
    }

    @Test
    @DisplayName("업적 조회(아이디) 테스트 - 성공")
    public void findAchievementByIdFailByAchievementNotFoundTest() {
        // Given
        Long achievementId = 1000L;

        // When & Then
        Assertions.assertThrows(AchievementNotFoundException.class,
                () -> defaultAchievementUseCase.findAchievementById(achievementId));
    }
}
