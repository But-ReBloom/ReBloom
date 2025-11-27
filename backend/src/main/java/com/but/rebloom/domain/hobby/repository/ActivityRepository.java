package com.but.rebloom.domain.hobby.repository;

import com.but.rebloom.domain.hobby.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // activityId로 객체 조회
    Optional<Activity> findByActivityId(Long activityId);

    // (activityEmail + userName)으로 객체 조회
    Optional<Activity> findByUser_UserEmailAndActivityId(String userEmail, Long activityId);

    // (activityEmail + userName)으로 객체 조회
    Optional<Activity> findByUser_UserEmailAndActivityName(String userEmail, String activityName);

    // userEmail로 객체 조회
    List<Activity> findByUser_UserEmail(String userEmail);

    // activityRecent를 기준으로 오름차순 정렬하여 조회
    List<Activity> findByUser_UserEmailOrderByActivityRecentAsc(String userEmail);

    // activityRecent를 기준으로 내림차순 정렬하여 조회
    List<Activity> findByUser_UserEmailOrderByActivityRecentDesc(String userEmail);
}