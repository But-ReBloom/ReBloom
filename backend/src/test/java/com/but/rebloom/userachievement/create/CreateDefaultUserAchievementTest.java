package com.but.rebloom.userachievement.create;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateDefaultUserAchievementTest {
    @Mock
    private AchievementRepository achievementRepository;
    @Mock
    private UserAchievementRepository userAchievementRepository;
    @InjectMocks
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    @Test
    @DisplayName("유저 업적 생성 테스트 - 성공")
    public void createDefaultUserAchievementSuccessTest() {
        // Given
        String userEmail = "test@test.com";

        Achievement mockAchievement = Achievement.builder()
                .achievementId(1L)
                .achievementTitle("test achievement title")
                .build();

        List<Achievement> mockAchievements = List.of(mockAchievement);

        when(achievementRepository.findAll())
                .thenReturn(mockAchievements);
        when(userAchievementRepository.save(any(UserAchievement.class)))
                .thenReturn(null); // 어짜피 저장은 안해서 null로 해둠

        // When & Then
        defaultUserAchievementUseCase.createDefaultUserAchievement(userEmail);
    }
}
