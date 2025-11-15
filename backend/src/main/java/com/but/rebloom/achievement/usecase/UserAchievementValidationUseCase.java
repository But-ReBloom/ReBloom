package com.but.rebloom.achievement.usecase;

import com.but.rebloom.common.usecase.ValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAchievementValidationUseCase {
    // DI
    private final ValidationUseCase validationUseCase;
    private final AchievementValidationUseCase achievementValidationUseCase;

    // 널 값 확인 - 한 요소
    public <T> void checkNull(T element) {
        validationUseCase.checkNull(element);
    }

    // 존재하는 유저인지 확인
    public void checkNotExistAccountByUserEmail(String userEmail) {
        validationUseCase.checkNotExistAccountByUserEmail(userEmail);
    }

    // 존재하는 업적인지 확인
    public void checkExistAchievementByAchievementId(Long achievementId) {
        achievementValidationUseCase.checkExistAchievementByAchievementId(achievementId);
    }

    // 존재하는 업적인지 확인
    public void checkExistAchievementByAchievementTitle(String achievementTitle) {
        achievementValidationUseCase.checkExistAchievementByAchievementTitle(achievementTitle);
    }
}
