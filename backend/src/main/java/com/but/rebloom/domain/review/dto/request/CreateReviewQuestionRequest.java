package com.but.rebloom.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateReviewQuestionRequest {
    @NotNull
    private Long hobbyId;
}
