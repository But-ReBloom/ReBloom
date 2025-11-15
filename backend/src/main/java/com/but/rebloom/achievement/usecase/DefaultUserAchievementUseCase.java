package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.achievement.exception.UserAchievementNotFoundException;
import com.but.rebloom.achievement.repository.AchievementRepository;
import com.but.rebloom.achievement.repository.UserAchievementRepository;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.exception.UserNotFoundException;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.auth.usecase.FindCurrentUserUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserAchievementUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;
    // 로그인 되어있는지 확인
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    // 전체 유저 업적 조회 - 유저 아이디
    public List<UserAchievement> finaAllUserAchievementsByUserId() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userId = currentUser.getUserId();

        return userAchievementRepository.findAllByUserId(userId);
    }

    // 전체 유저 업적 조회 - 유저 이메일
    public List<UserAchievement> finaAllUserAchievementsByUserEmail() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userEmail = currentUser.getUserEmail();

        return userAchievementRepository.findAllByUserEmail(userEmail);
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 아이디)
    public UserAchievement findUserAchievementByUserEmailAndAchievementId(Long achievementId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userEmail = currentUser.getUserEmail();

        return userAchievementRepository.findByUserEmailAndAchievementId(userEmail, achievementId)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 초기 유저 업적 생성
    @Transactional
    public void createDefaultUserAchievement(String userEmail, String userId) {
        List<Achievement> allAchievements = achievementRepository.findAll();

        List<UserAchievement> initialAchievements = allAchievements.stream()
                .map(a -> UserAchievement.builder()
                        .userEmail(userEmail)
                        .userId(userId)
                        .achievementId(a.getAchievementId())
                        .achievementTitle(a.getAchievementTitle())
                        .userAchievementProgress(0f)
                        .isSuccess(false)
                        .build())
                .toList();

        userAchievementRepository.saveAll(initialAchievements);
    }

    // 유저 업적 성공 처리 - (PK - Pk)
    @Transactional
    public void updateUserAchievementToSuccess(String userEmail, Long achievementId) {
        UserAchievement userAchievement = userAchievementRepository
                .findByUserEmailAndAchievementId(userEmail, achievementId)
                .orElseThrow(() -> new UserAchievementNotFoundException("유저 업적이 조회되지 않음"));

        userAchievement.setIsSuccess(true);
        userAchievement.setUserAchievementProgress(100.0f);

        Achievement achievement = achievementRepository.findByAchievementId(achievementId)
                .orElseThrow(() -> new AchievementNotFoundException("업적이 조회되지 않음"));
        int rewardPoint = achievement.getAchievementRewardPoint();
        int rewardTierPoint = achievement.getAchievementRewardTierPoint();

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("유저가 조회되지 않음"));
        user.setUserPoint(user.getUserPoint() + rewardPoint);
        user.setUserTierPoint(user.getUserTierPoint() + rewardTierPoint);
    }

    // 유저 업적 성공 처리 - (PK - Non-Pk)
    @Transactional
    public void updateUserAchievementToSuccess(String userEmail, String achievementTitle) {
        UserAchievement userAchievement = userAchievementRepository
                .findByUserEmailAndAchievementTitle(userEmail, achievementTitle)
                .orElseThrow(() -> new UserAchievementNotFoundException("유저 업적이 조회되지 않음"));

        userAchievement.setIsSuccess(true);
        userAchievement.setUserAchievementProgress(100.0f);

        Achievement achievement = achievementRepository.findByAchievementTitle(achievementTitle)
                .orElseThrow(() -> new AchievementNotFoundException("업적이 조회되지 않음"));
        int rewardPoint = achievement.getAchievementRewardPoint();
        int rewardTierPoint = achievement.getAchievementRewardTierPoint();

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("유저가 조회되지 않음"));
        user.setUserPoint(user.getUserPoint() + rewardPoint);
        user.setUserTierPoint(user.getUserTierPoint() + rewardTierPoint);
    }
}
