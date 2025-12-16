package com.but.rebloom.domain.review.repository;

import com.but.rebloom.domain.review.domain.ActivityReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityReviewRepository extends JpaRepository<ActivityReview, Long> {
    Optional<ActivityReview> findByReviewIdAndUser_UserEmail(Long reviewId, String userEmail);
}
