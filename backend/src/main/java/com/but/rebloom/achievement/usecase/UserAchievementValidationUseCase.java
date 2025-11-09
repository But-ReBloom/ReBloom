package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.repository.UserAchievementRepository;
import com.but.rebloom.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.common.usecase.ValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAchievementValidationUseCase {
    // DI
    private final ValidationUseCase validationUseCase;
    // 디비 접근
    private final UserAchievementRepository userAchievementRepository;

    // 널 값 확인 - 한 요소
    public <T> void checkNull(T element) {
        validationUseCase.checkNull(element);
    }

    // 존재하는 유저인지 확인
    public void checkNotExistAccountByUserEmail(String userEmail) {
        validationUseCase.checkNotExistAccountByUserEmail(userEmail);
    }
}
