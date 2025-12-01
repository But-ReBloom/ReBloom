package com.but.rebloom.domain.review.controller;

import com.but.rebloom.common.dto.ApiResponse;
import com.but.rebloom.domain.review.dto.response.ReviewQuestionResponse;
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

    // AI 생성 질문 조회
    @GetMapping("/questions/{hobbyId}")
    public ResponseEntity<ApiResponse<ReviewQuestionResponse>> getReviewQuestion(@PathVariable Long hobbyId) {
        ReviewQuestionResponse reviewQuestionResponse = activityReviewUseCase.generateReviewQuestions(hobbyId);
    }
}
