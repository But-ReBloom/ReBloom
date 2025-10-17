package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.Activity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findActivityNameByActivityId(Long activityId);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO Activity (activityName, activityStart, activityRecent) " +
                    "VALUES (:newActivityNamed, :newActivityStart, :newActivityRecent)",
            nativeQuery = true
    )
    void insertActivity(
            @Param("newActivityName") String newActivityName,
            @Param("newActivityStart") LocalDate newActivityStart,
            @Param("newActivityRecent") LocalDate newActivityRecent
    );

    @Modifying
    @Transactional
    @Query("UPDATE Activity activity SET activity.activityName = :newActivityName WHERE activity.activityId = :currentActivityId")
    void updateActivityNameByActivityId(
            @Param("currentActivityId") Long currentActivityId,
            @Param("newActivityName") String activityName
    );

    @Modifying
    @Transactional
    @Query("UPDATE Activity activity SET activity.activityRecent = :newActivityRecent WHERE activity.activityId = :currentActivityId")
    void updateActivityRecentByActivityId(
            @Param("currentActivityId") Long currentActivityId,
            @Param("newActivityRecent") String activityRecent
    );
}
