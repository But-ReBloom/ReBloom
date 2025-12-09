package com.but.rebloom.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewAnswerRequest {
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
