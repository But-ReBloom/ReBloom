package com.but.rebloom.review.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UpdatedVectorResponse {
    private UserVectorResponse previousVector;
    private UserVectorResponse updatedVector;
    private List<HobbyRecommendationResponse> recommendedHobbies;
}
