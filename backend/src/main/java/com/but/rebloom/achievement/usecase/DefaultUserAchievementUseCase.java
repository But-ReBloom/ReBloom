package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.exception.UserAchievementNotFoundException;
import com.but.rebloom.achievement.repository.UserAchievementRepository;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.usecase.FindCurrentUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserAchievementUseCase {
    // 디비 접근
    private final UserAchievementRepository userAchievementRepository;
    // 로그인 되어있는지 확인
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    // 전체 유저 업적 조회 - 유저 아이디
    public List<UserAchievement> finaAllUserAchievementsByUserId() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userId = currentUser.getUserId();

        return userAchievementRepository.findAllUserAchievementsByUserId(userId)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 전체 유저 업적 조회 - 유저 이메일
    public List<UserAchievement> finaAllUserAchievementsByUserEmail() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userEmail = currentUser.getUserEmail();

        return userAchievementRepository.findAllUserAchievementsByUserEmail(userEmail)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 아이디)
    public UserAchievement finaUserAchievementByUserEmailAndAchievementId(Long achievementId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userEmail = currentUser.getUserEmail();

        return userAchievementRepository.findUserAchievementByUserEmailAndAchievementId(userEmail, achievementId)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 제목)
    public UserAchievement finaUserAchievementByUserEmailAndAchievementTitle(String achievementTitle) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();
        String userEmail = currentUser.getUserEmail();

        return userAchievementRepository.findUserAchievementByUserEmailAndAchievementTitle(userEmail, achievementTitle)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }
}
