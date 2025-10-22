package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.Activity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // ActivityId로 객체 조회
    Optional<Activity> findActivityByActivityId(Long activityId);
    // (ActivityName + UserEmail)으로 객체 조회
    Optional<Activity> findActivityByActivityNameAndUserEmail(String activityName, String userEmail);
    // UserEmail로 객체 조회
    Optional<List<Activity>> findActivityByUserEmail(String userEmail);
    // ActivityRecent를 기준으로 오름차순 정렬하여 조회
    @Query("SELECT activity FROM Activity activity WHERE activity.userEmail = :userEmail " +
            "ORDER BY activity.activityRecent ASC")
    Optional<List<Activity>> findActivityOrderByActivityRecentAsc(
            @Param("userEmail") String userEmail
    );
    // ActivityRecent를 기준으로 내림차순 정렬하여 조회
    @Query("SELECT activity FROM Activity activity WHERE activity.userEmail = :userEmail " +
            "ORDER BY activity.activityRecent DESC")
    Optional<List<Activity>> findActivityOrderByActivityRecentDesc(
            @Param("userEmail") String userEmail
    );

    // 활동 이름 변경
    @Modifying
    @Transactional
    @Query("UPDATE Activity activity SET activity.activityName = :newActivityName WHERE activity.activityId = :currentActivityId")
    void updateActivityNameByActivityId(
            @Param("currentActivityId") Long currentActivityId,
            @Param("newActivityName") String activityName
    );

    // 최근 활동일 변경
    @Modifying
    @Transactional
    @Query("UPDATE Activity activity SET activity.activityRecent = :newActivityRecent WHERE activity.activityId = :currentActivityId")
    void updateActivityRecentByActivityId(
            @Param("currentActivityId") Long currentActivityId,
            @Param("newActivityRecent") String activityRecent
    );
}

