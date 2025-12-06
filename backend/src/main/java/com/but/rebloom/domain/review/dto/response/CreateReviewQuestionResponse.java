package com.but.rebloom.domain.review.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewQuestionResponse {
    @NotNull
    private String socialQuestion;

    @NotNull
    private String learningQuestion;

    @NotNull
    private String planningQuestion;

    @NotNull
    private String focusQuestion;

    @NotNull
    private String creativityQuestion;
}
