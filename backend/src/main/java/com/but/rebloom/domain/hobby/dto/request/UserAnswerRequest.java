package com.but.rebloom.domain.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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