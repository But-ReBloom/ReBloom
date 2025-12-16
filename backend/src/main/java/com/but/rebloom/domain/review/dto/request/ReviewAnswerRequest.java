package com.but.rebloom.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewAnswerRequest {
    @NotNull
    private Long activityReviewId;

    @NotBlank
    private String userEmail;

    @NotNull
    private Long hobbyId;

    @NotBlank
    private String socialAnswer;

    @NotBlank
    private String learningAnswer;

    @NotBlank
    private String planningAnswer;

    @NotBlank
    private String focusAnswer;

    @NotBlank
    private String creativityAnswer;
}