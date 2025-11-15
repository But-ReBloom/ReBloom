package com.but.rebloom.auth.usecase;

import com.but.rebloom.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.LoginRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.IllegalArgumentException;
import com.but.rebloom.auth.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 예외 이용
    private final AuthValidationUseCase authValidationUseCase;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // 업적 함수 호출
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 로그인
    @Transactional
    public User login(LoginRequest loginRequest) {
        // 기본 예외 확인
        authValidationUseCase.checkNull(loginRequest);

        String userEmail = loginRequest.getUserEmail();
        String userPassword = loginRequest.getUserPassword();

        authValidationUseCase.checkUserEmail(userEmail);
        authValidationUseCase.checkUserPassword(userPassword);

        if (!loginRequest.getUserProvider().equals(Provider.SELF)) {
            throw new IllegalArgumentException("잘못된 로그인 환경");
        }

        User user = userRepository.findByUserEmail(userEmail)
                .filter(u -> passwordEncoder.matches(userPassword, u.getUserPassword()))
                .orElseThrow(() -> new UserNotFoundException("유저 조회 실패"));

        // 최근 출석일 업데이트
        LocalDate recentLoginDate = user.getUserRecentDate();
        if (recentLoginDate.isBefore(LocalDate.now().minusDays(1))) {
            user.setUserStreak(user.getUserStreak() + 1);
        }

        user.setUserRecentDate(LocalDate.now());

        String streak2AchievementTitle = "계획적으로!";
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, streak2AchievementTitle, 100.0f / 2.0f);

        String streak5AchievementTitle = "5연속 접속!";
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, streak5AchievementTitle, 100.0f / 5.0f);

        String streak365AchievementTitle = "연속 접속의 신";
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, streak365AchievementTitle, 100.0f / 365.0f);

        return user;
    }
}