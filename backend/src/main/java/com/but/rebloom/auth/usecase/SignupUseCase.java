package com.but.rebloom.auth.usecase;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.repository.AchievementRepository;
import com.but.rebloom.achievement.repository.UserAchievementRepository;
import com.but.rebloom.achievement.usecase.DefaultAchievementUseCase;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignupUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // 예외 이용
    private final AuthValidationUseCase authValidationUseCase;
    // 업적 조회
    private final AchievementRepository achievementRepository;
    // 유저 업적 조회
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
                .userProvider(signupRequest.getUserProvider())
                .build();

        // 유저 등록
        User saveUser = userRepository.save(user);
        userRepository.flush();

        // 초기 유저 업적 생성
        List<Achievement> allAchievements = achievementRepository.findAll();

        List<UserAchievement> initialAchievements = allAchievements.stream()
                .map(a -> UserAchievement.builder()
                        .userEmail(userEmail)
                        .userId(userId)
                        .achievementId(a.getAchievementId())
                        .achievementTitle(a.getAchievementTitle())
                        .progress(0f)
                        .isSuccess(false)
                        .build())
                .toList();

        userAchievementRepository.saveAll(initialAchievements);

        // 업적 성공 처리
        String achievementTitle = "시작이 반이다.";
        userAchievementRepository.updateUserAchievementToSuccess(userEmail, achievementTitle);
        Achievement achievement = defaultAchievementUseCase.findAchievementByTitle(achievementTitle);
        userAchievementRepository.getPointFromUserAchievement(userEmail, achievement.getAchievementRewardPoint());
        userAchievementRepository.getPointFromUserAchievement(userEmail, achievement.getAchievementTierPoint());

        return saveUser;
    }
}
