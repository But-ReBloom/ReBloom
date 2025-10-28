package com.but.rebloom.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAnswerRequest {
    @NotNull
    private int setNo;
    @NotNull
    private String category;
    @NotNull
    private double answerValue;
}