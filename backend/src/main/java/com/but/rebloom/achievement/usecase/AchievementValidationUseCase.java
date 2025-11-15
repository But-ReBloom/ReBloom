package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.achievement.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementValidationUseCase {
    // 디비 접근
    private final AchievementRepository achievementRepository;

    // 존재하는지 확인 - 업적 아이디
    public void checkExistAchievementByAchievementId(Long achievementId) {
        if (!achievementRepository.existsByAchievementId(achievementId)) {
            throw new AchievementNotFoundException("존재하지 않는 업적");
        }
    }

    // 존재하는지 확인 - 업적 제목
    public void checkExistAchievementByAchievementTitle(String achievementTitle) {
        if (!achievementRepository.existsByAchievementTitle(achievementTitle)) {
            throw new AchievementNotFoundException("존재하지 않는 업적");
        }
    }
}
