package com.but.rebloom.domain.review.controller;

import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.review.usecase.ActivityReviewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity-review")
@RequiredArgsConstructor
public class ActivityReviewController {
    private final ActivityReviewUseCase activityReviewUseCase;
}