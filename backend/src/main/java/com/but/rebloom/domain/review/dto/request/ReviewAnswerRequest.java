package com.but.rebloom.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewAnswerRequest {
    @NotNull
    private String userEmail;

    @NotNull
    private Long hobbyId;

    @NotNull
    private String socialAnswer;

    @NotNull
    private String learningAnswer;

    @NotNull
    private String PlanningAnswer;

    @NotNull
    private String focusAnswer;

    @NotNull
    private String creativityAnswer;
}
