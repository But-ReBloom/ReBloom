package com.but.rebloom.domain.achievement.usecase;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.domain.achievement.exception.UserAchievementNotFoundException;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import com.but.rebloom.domain.achievement.repository.UserAchievementRepository;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.TierNotFoundException;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.DefaultTierUseCase;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
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
    // 함수 홏출
    private final DefaultTierUseCase defaultTierUseCase;

    // 전체 유저 업적 조회 - 유저 이메일
    public List<UserAchievement> finaAllUserAchievementsByUserEmail() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userEmail = currentUser.getUserEmail();

        List<UserAchievement> userAchievements = userAchievementRepository.findAllByUserEmail(userEmail);

        // 빈 리스트도 정상 응답으로 처리 (신규 유저 등)
        return userAchievements;
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
    public void createDefaultUserAchievement(String userEmail) {
        List<Achievement> allAchievements = achievementRepository.findAll();

        List<UserAchievement> initialAchievements = allAchievements.stream()
                .map(a -> UserAchievement.builder()
                        .userEmail(userEmail)
                        .achievementId(a.getAchievementId())
                        .userAchievementProgress(0f)
                        .isSuccess(false)
                        .build())
                .toList();

        userAchievementRepository.saveAll(initialAchievements);
    }

    // 유저 업적 성공 처리 - (PK - Non-Pk)
    @Transactional
    public void updateUserAchievementToSuccess(String userEmail, String achievementTitle) {
        UserAchievement userAchievement = userAchievementRepository
                .findByUserEmailAndAchievement_AchievementTitle(userEmail, achievementTitle)
                .orElseThrow(() -> new UserAchievementNotFoundException("유저 업적이 조회되지 않음"));

        // 이미 성공했으면 패스
        if (userAchievement.getIsSuccess().equals(true))
            return;

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

        user.setUserTier(defaultTierUseCase.getTierEnumByPoint(user.getUserTierPoint())
                .orElseThrow(() -> new TierNotFoundException("티어가 조회되지 않음"))
        );
    }

    @Transactional
    public void updateUserAchievementProgress(String userEmail, String achievementTitle, float progress) {
        UserAchievement userAchievement = userAchievementRepository
                .findByUserEmailAndAchievement_AchievementTitle(userEmail, achievementTitle)
                .orElseThrow(() -> new UserAchievementNotFoundException("유저 업적이 조회되지 않음"));

        // 이미 성공했으면 패스
        if (userAchievement.getIsSuccess().equals(true))
            return;

        userAchievement.setUserAchievementProgress(userAchievement.getUserAchievementProgress() + (float) Math.ceil(progress));

        if (Math.ceil(userAchievement.getUserAchievementProgress()) >= 100.0f)
            updateUserAchievementToSuccess(userEmail, achievementTitle);
    }
}