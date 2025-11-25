package com.but.rebloom.achievement.repository;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.domain.UserAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, UserAchievementId> {
    // 유저 업적 조회 - userEmail + achievementId
    Optional<UserAchievement> findByUserEmailAndAchievementId(String userEmail, Long achievementId);

    // 유저 업적 조회 - userEmail + achievementTitle
    Optional<UserAchievement> findByUserEmailAndAchievement_AchievementTitle(
            String userEmail, String achievementTitle);

    // 유저 업적 전체 조회 - userId
    List<UserAchievement> findAllByUser_UserId(String userId);

    // 유저 업적 전체 조회 - userEmail
    List<UserAchievement> findAllByUserEmail(String userEmail);
}