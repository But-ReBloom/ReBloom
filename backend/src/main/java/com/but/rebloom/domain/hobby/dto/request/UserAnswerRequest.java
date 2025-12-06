package com.but.rebloom.domain.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAnswerRequest {
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
}