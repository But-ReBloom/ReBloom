package com.but.rebloom.review.repository;

import com.but.rebloom.review.domain.ActivityReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepository extends JpaRepository<ActivityReview, Long> {


}
