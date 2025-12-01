package com.but.rebloom.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewQuestionDto {
    private String question;
    private String targetDimension;
}
