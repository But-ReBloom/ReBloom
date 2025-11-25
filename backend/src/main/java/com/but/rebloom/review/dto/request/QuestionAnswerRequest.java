package com.but.rebloom.review.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class QuestionAnswerRequest {

    @NotNull
    private String question;

    @NotNull
    private String answer;

    @NotNull
    private String targetDimension; // social, learning, planning, focus, creativity
}