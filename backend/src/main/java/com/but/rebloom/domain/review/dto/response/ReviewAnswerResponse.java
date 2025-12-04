package com.but.rebloom.domain.review.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewAnswerResponse {
    @NotNull
    private Double socialScore;

    @NotNull
    private Double learningScore;

    @NotNull
    private Double planningScore;

    @NotNull
    private Double focusScore;

    @NotNull
    private Double creativityScore;

    @NotNull
    private String hobby1;

    @NotNull
    private String hobby2;

    @NotNull
    private String hobby3;
}
