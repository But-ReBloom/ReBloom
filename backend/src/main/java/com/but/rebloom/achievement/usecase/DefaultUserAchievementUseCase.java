package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.exception.UserAchievementNotFoundException;
import com.but.rebloom.achievement.repository.UserAchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserAchievementUseCase {
    // 디비 접근
    private final UserAchievementRepository userAchievementRepository;

    // 전체 유저 업적 조회 - 유저 아이디
    public List<UserAchievement> finaAllUserAchievementsByUserId(String userId) {
        return userAchievementRepository.findAllUserAchievementsByUserId(userId)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 전체 유저 업적 조회 - 유저 이메일
    public List<UserAchievement> finaAllUserAchievementsByUserEmail(String userEmail) {
        return userAchievementRepository.findAllUserAchievementsByUserEmail(userEmail)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }
}
