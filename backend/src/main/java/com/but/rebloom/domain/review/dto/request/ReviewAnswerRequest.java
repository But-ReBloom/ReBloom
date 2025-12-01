package com.but.rebloom.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewAnswerRequest {

    @NotNull
    private String userEmail;

    @NotNull
    private Long hobbyId;

    @NotNull
    private List<QuestionAnswerRequest> answers;
}
