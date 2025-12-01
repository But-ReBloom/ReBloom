package com.but.rebloom.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewQuestionResponse {
    private Long hobbyId;
    private String hobbyName;
    private List<ReviewQuestionDto> questions;
}
