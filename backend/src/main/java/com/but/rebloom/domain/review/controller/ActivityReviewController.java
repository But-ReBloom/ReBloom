package com.but.rebloom.domain.review.controller;

import com.but.rebloom.domain.review.dto.request.CreateReviewQuestionRequest;
import com.but.rebloom.domain.review.dto.request.ReviewAnswerRequest;
import com.but.rebloom.domain.review.dto.response.CreateReviewQuestionResponse;
import com.but.rebloom.domain.review.dto.response.ReviewAnswerResponse;
import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.review.usecase.ActivityReviewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity-review")
@RequiredArgsConstructor
public class ActivityReviewController {
    private final ActivityReviewUseCase activityReviewUseCase;

    // 질문 생성
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateReviewQuestionResponse>> createReviewQuestion(@RequestBody CreateReviewQuestionRequest request) {
        CreateReviewQuestionResponse response = activityReviewUseCase.createReviewQuestion(request.getHobbyId());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 활동리뷰 답변 시 점수 업데이트 및 취미 추천
    @PostMapping("/answer")
    public ResponseEntity<ApiResponse<ReviewAnswerResponse>> answerReview(@RequestBody ReviewAnswerRequest reviewAnswerRequest) {
        ReviewAnswerResponse response = activityReviewUseCase.answerReview(reviewAnswerRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}