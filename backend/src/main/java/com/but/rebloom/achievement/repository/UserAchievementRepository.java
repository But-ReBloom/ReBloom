package com.but.rebloom.achievement.repository;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.domain.UserAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, UserAchievementId> {
    // 유저 업적 조회 - userEmail + achieveId
    Optional<UserAchievement> findUserAchievementByUserEmailAndAchieveId(String userEmail, Long achieveId);
    // 유저 업적 조회 - userEmail + achieveTitle
    Optional<UserAchievement> findUserAchievementByUserEmailAndAchieveTitle(String userEmail, String achieveTitle);
    // 유저 업적 조회 - userId + achieveId
    Optional<UserAchievement> findUserAchievementByUserIdAndAchieveId(String userId, Long achieveId);
    // 유저 업적 조회 - userId + achieveTitle
    Optional<UserAchievement> findUserAchievementByUserIdAndAchieveTitle(String userId, String achieveTitle);
    // 유저 업적 전체 조회
    @Query("SELECT a FROM UserAchievement a")
    Optional<List<UserAchievement>> findAllUserAchievements();
}
