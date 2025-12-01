package com.but.rebloom.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVectorResponse {
    private Double social;
    private Double learning;
    private Double planning;
    private Double focus;
    private Double creativity;
}
