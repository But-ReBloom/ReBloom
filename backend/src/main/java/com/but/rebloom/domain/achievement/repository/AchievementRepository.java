package com.but.rebloom.domain.achievement.repository;

import com.but.rebloom.domain.achievement.domain.Achievement;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    // 업적 조회 - achievementId
    Optional<Achievement> findByAchievementId(Long achievementId);

    // 업적 조회 - achievementTitle
    Optional<Achievement> findByAchievementTitle(String achievementTitle);

    // 업적 조회 - 전체
    @NonNull
    @Override
    List<Achievement> findAll();
}