package com.but.rebloom.auth.usecase;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.repository.UserAchievementRepository;
import com.but.rebloom.achievement.usecase.DefaultAchievementUseCase;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.SignupRequest;
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
    // 업적 조정
    private final UserAchievementRepository userAchievementRepository;
    // 업적 관련 함수 이용
    private final DefaultAchievementUseCase defaultAchievementUseCase;

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

        authValidationUseCase.checkExistAccount(userEmail, userId);

        // 유저 생성
        User user = User.builder()
                .userEmail(userEmail)
                .userId(userId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userName(userName)
                .provider(signupRequest.getProvider())
                .build();

        // 업적 성공 처리
        String achievementTitle = "시작이 반이다.";
        userAchievementRepository.updateUserAchievementToSuccess(userEmail, achievementTitle);
        Achievement achievement = defaultAchievementUseCase.findAchievementByTitle(achievementTitle);
        userAchievementRepository.getPointFromUserAchievement(userEmail, achievementTitle, achievement.getAchievementRewardPoint());
        userAchievementRepository.getPointFromUserAchievement(userEmail, achievementTitle, achievement.getAchievementTierPoint());

        // 유저 등록
        return userRepository.save(user);
    }
}
