package com.but.rebloom.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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