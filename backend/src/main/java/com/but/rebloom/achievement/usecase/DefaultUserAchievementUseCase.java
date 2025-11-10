package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.dto.request.EmailAndIdRequest;
import com.but.rebloom.achievement.dto.request.EmailAndTitleRequest;
import com.but.rebloom.achievement.dto.request.IdAndIdRequest;
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

    // 유저 업적 조회 - (유저 이메일 + 업적 아이디)
    public UserAchievement finaUserAchievementByUserEmailAndAchievementId(EmailAndIdRequest request) {
        String userEmail = request.getUserEmail();
        Long achievementId = request.getAchievementId();

        return userAchievementRepository.findUserAchievementByUserEmailAndAchievementId(userEmail, achievementId)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 제목)
    public UserAchievement finaUserAchievementByUserEmailAndAchievementTitle(EmailAndTitleRequest request) {
        String userEmail = request.getUserEmail();
        String achievementTitle = request.getAchievementTitle();

        return userAchievementRepository.findUserAchievementByUserEmailAndAchievementTitle(userEmail, achievementTitle)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 유저 업적 조회 - (유저 아이디 + 업적 아이디)
    public UserAchievement finaUserAchievementByUserIdAndAchievementId(IdAndIdRequest request) {
        String userId = request.getUserId();
        Long achievementId = request.getAchievementId();

        return userAchievementRepository.findUserAchievementByUserIdAndAchievementId(userId, achievementId)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }

    // 유저 업적 조회 - (유저 아이디 + 업적 제목)
    public UserAchievement finaUserAchievementByUserIdAndAchievementTitle(EmailAndTitleRequest request) {
        String userEmail = request.getUserEmail();
        String achievementTitle = request.getAchievementTitle();

        return userAchievementRepository.findUserAchievementByUserIdAndAchievementTitle(userEmail, achievementTitle)
                .orElseThrow(() -> new UserAchievementNotFoundException("존재하지 않는 유저 업적"));
    }
}
