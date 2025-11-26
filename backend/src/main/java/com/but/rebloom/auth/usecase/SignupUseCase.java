package com.but.rebloom.auth.usecase;

import com.but.rebloom.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // 예외 이용
    private final AuthValidationUseCase authValidationUseCase;
    // 유저 업적 설정
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 회원가입
    @Transactional
    public User signup(SignupRequest signupRequest) {
        // 기본 예외 처리
        authValidationUseCase.checkNull(signupRequest);

        String userEmail = signupRequest.getUserEmail();
        String userId = signupRequest.getUserId();
        String userPassword = signupRequest.getUserPassword();
        String userName = signupRequest.getUserName();

        authValidationUseCase.checkUserEmail(userEmail);
        authValidationUseCase.checkUserId(userId);
        authValidationUseCase.checkUserPassword(userPassword);
        authValidationUseCase.checkUserName(userName);

        if (userRepository.existsByUserEmail(userEmail))
            throw new AlreadyUsingUserException("이미 사용중인 이메일");

        if (userRepository.existsByUserId(userId))
            throw new AlreadyUsingUserException("이미 사용중인 아이디");

        // 유저 생성
        User user = User.builder()
                .userEmail(userEmail)
                .userId(userId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userName(userName)
                .userProvider(signupRequest.getUserProvider())
                .userCurrentActivity(null)
                .build();

        // 유저 등록
        User saveUser = userRepository.save(user);
        userRepository.flush();

        // 초기 유저 업적 생성
        defaultUserAchievementUseCase.createDefaultUserAchievement(userEmail);

        // 업적 성공 처리
        String signupAchievementTitle = "시작이 반이다.";
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(userEmail, signupAchievementTitle);

        String streak2AchievementTitle = "계획적으로!";
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, streak2AchievementTitle, 100.0f / 2.0f);

        String streak5AchievementTitle = "5연속 접속!";
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, streak5AchievementTitle, 100.0f / 5.0f);

        String streak365AchievementTitle = "연속 접속의 신";
        defaultUserAchievementUseCase.updateUserAchievementProgress(userEmail, streak365AchievementTitle, 100.0f / 365.0f);

        return saveUser;
    }
}