package com.but.rebloom.domain.review.repository;

import com.but.rebloom.domain.review.domain.ActivityReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepository extends JpaRepository<ActivityReview, Long> {


}
