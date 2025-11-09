package com.but.rebloom.achievement.repository;

import com.but.rebloom.achievement.domain.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    // 업적 조회 함수
    Optional<Achievement> findAchievementByAchievementId(Long achievementId);
    Optional<Achievement> findAchievementByAchievementTitle(String achievementTitle);
    @Query("SELECT a FROM Achievement a")
    Optional<List<Achievement>> findAllAchievements();

    // 존재 유무 확인 함수
    Boolean existsByAchievementId(Long achievementId);
    Boolean existsByAchievementTitle(String achievementTitle);
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Achievement a WHERE a.achievementId = :achievementId AND a.achievementTitle = :achievementTitle")
    Boolean existsByAchievementIdAndAchievementTitle(
            @Param("achievementId") Long achievementId,
            @Param("achievementTitle") String achievementTitle
    );
}
