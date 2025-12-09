package com.but.rebloom.domain.review.dto.response;

import com.but.rebloom.domain.review.domain.ActivityReview;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewQuestionResponse {
    @NotBlank
    private String reviewQuestion;

    public static CreateReviewQuestionResponse from(ActivityReview activityReview) {
        return CreateReviewQuestionResponse.builder()
                .reviewQuestion(activityReview.getReviewQuestion())
                .build();
    }
}
