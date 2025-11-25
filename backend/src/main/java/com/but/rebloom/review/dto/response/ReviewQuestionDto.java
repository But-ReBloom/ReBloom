package com.but.rebloom.review.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewQuestionDto {
    private String question;
    private String targetDimension;
}
