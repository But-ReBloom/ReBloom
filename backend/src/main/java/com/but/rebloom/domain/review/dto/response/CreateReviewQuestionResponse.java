package com.but.rebloom.domain.review.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewQuestionResponse {
    @NotBlank
    private String socialQuestion;

    @NotBlank
    private String learningQuestion;

    @NotBlank
    private String planningQuestion;

    @NotBlank
    private String focusQuestion;

    @NotBlank
    private String creativityQuestion;
}
