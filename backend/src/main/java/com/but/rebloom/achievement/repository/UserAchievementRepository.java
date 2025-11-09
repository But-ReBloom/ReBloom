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
    @Query("select a from UserAchievement a where a.userEmail = :userEmail and a.achievementId = :achievementId")
    Optional<UserAchievement> findUserAchievementByUserEmailAndAchievementId(
            @Param("userEmail") String userEmail,
            @Param("achievementId") Long achievementId
    );

    // 유저 업적 조회 - userEmail + achievementTitle
    @Query("select a from UserAchievement a where a.userEmail = :userEmail and a.achievementTitle = :achievementTitle")
    Optional<UserAchievement> findUserAchievementByUserEmailAndAchievementTitle(
            @Param("userEmail") String userEmail,
            @Param("achievementTitle") String achievementTitle
    );

    // 유저 업적 조회 - userId + achievementId
    @Query("select a from UserAchievement a where a.userId = :userId and a.achievementId = :achievementId")
    Optional<UserAchievement> findUserAchievementByUserIdAndAchievementId(
            @Param("userId") String userId,
            @Param("achievementId") Long achievementId
    );

    // 유저 업적 조회 - userId + achievementTitle
    @Query("select a from UserAchievement a where a.userId = :userId and a.achievementTitle = :achievementTitle")
    Optional<UserAchievement> findUserAchievementByUserIdAndAchievementTitle(
            @Param("userId") String userId,
            @Param("achievementTitle") String achievementTitle
    );

    // 유저 업적 전체 조회 - userId
    @Query("select a from UserAchievement a where a.userId = :userId")
    Optional<List<UserAchievement>> findAllUserAchievementsByUserId(@Param("userId") String userId);

    // 유저 업적 전체 조회 - userEmail
    @Query("select a from UserAchievement a where a.userEmail = :userEmail")
    Optional<List<UserAchievement>> findAllUserAchievementsByUserEmail(@Param("userEmail") String userEmail);
}
