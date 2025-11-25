package com.but.rebloom.review.dto.response;

import com.but.rebloom.hobby.domain.HobbyScore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HobbyRecommendationResponse {

    private Long hobbyId;
    private String hobbyName;
    private Double distance;

    public static HobbyRecommendationResponse from(HobbyScore hobbyScore) {
        return HobbyRecommendationResponse.builder()
                .hobbyId(hobbyScore.getHobbyWeight().getHobbyId())
                .hobbyName(hobbyScore.getHobbyWeight().getHobbyName())
                .distance(hobbyScore.getDistance())
                .build();
    }

}
