package com.but.rebloom.domain.review.domain;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.hobby.domain.Hobby;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ActivityReviewResult {
    private ActivityReview activityReview;
    private User user;
    private List<Hobby> hobbies;
}
