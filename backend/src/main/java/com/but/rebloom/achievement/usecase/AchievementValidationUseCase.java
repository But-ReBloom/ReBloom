package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.achievement.repository.AchievementRepository;
import com.but.rebloom.common.usecase.ValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementValidationUseCase {
    // DI
    private final ValidationUseCase validationUseCase;
    // 디비 접근
    private final AchievementRepository achievementRepository;

    // 널 값 확인 - 한 요소
    public <T> void checkNull(T element) {
        validationUseCase.checkNull(element);
    }

    // 존재하는지 확인 - 업적 아이디
    public void checkExistAchievement(Long achievementId) {
        if (!achievementRepository.existsByAchievementId(achievementId)) {
            throw new AchievementNotFoundException("존재하지 않는 업적");
        }
    }

    // 존재하는지 확인 - 업적 제목
    public void checkExistAchievement(String achievementTitle) {
        if (!achievementRepository.existsByAchievementTitle(achievementTitle)) {
            throw new AchievementNotFoundException("존재하지 않는 업적");
        }
    }
}
