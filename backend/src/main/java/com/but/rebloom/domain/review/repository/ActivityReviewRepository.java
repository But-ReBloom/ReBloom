package com.but.rebloom.domain.review.repository;

import com.but.rebloom.domain.review.domain.ActivityReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityReviewRepository extends JpaRepository<ActivityReview, Long> {
}
