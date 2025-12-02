package com.but.rebloom.achievement.find;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultAchievementUseCase;
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
public class FindAllAchievementsTest {
    @Mock
    private AchievementRepository achievementRepository;
    @InjectMocks
    private DefaultAchievementUseCase defaultAchievementUseCase;

    @Test
    @DisplayName("전체 업적 조회 - 성공")
    public void findAllAchievementsSuccessTest() {
        // Given
        Achievement mockAchievement = Achievement.builder()
                .achievementId(1L)
                .achievementTitle("achievementTitle")
                .build();

        List<Achievement> mockAchievements = List.of(mockAchievement);

        when(achievementRepository.findAll())
                .thenReturn(mockAchievements);

        // When
        List<Achievement> achievements = defaultAchievementUseCase.findAllAchievements();

        // Then
        assertThat(achievements).isNotEmpty();
    }
}
