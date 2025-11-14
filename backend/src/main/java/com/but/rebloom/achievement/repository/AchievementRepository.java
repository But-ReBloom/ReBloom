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
    // 업적 조회 - achievementId
    @Query("select a from Achievement a where a.achievementId = :achievementId")
    Optional<Achievement> findAchievementByAchievementId(@Param("achievementId") Long achievementId);

    // 업적 조회 - achievementTitle
    @Query("select a from Achievement a where a.achievementTitle = :achievementTitle")
    Optional<Achievement> findAchievementByAchievementTitle(
            @Param("achievementTitle") String achievementTitle
    );

    // 업적 조회 - 전체
    @Query("select a from Achievement a")
    Optional<List<Achievement>> findAllAchievements();

    // 존재 유무 확인 함수 - achievementId
    @Query("""
        select case when count(a) > 0 then true else false end
        from Achievement a
        where a.achievementId = :achievementId
    """)
    boolean existsByAchievementId(@Param("achievementId") Long achievementId);

    // 존재 유무 확인 함수 - achievementTitle
    @Query("""
        select case when count(a) > 0 then true else false end
        from Achievement a
        where a.achievementTitle = :achievementTitle
    """)
    boolean existsByAchievementTitle(@Param("achievementTitle") String achievementTitle);

    @Query("""
        select case when count(a) > 0 then true else false end
        from Achievement a
        where a.achievementId = :achievementId and trim(a.achievementTitle) = :achievementTitle
    """)
    boolean existsByAchievementIdAndAchievementTitle(
            @Param("achievementId") Long achievementId,
            @Param("achievementTitle") String achievementTitle
    );
}
