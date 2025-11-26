package com.but.rebloom.domain.hobby.dto.response;

import com.but.rebloom.domain.hobby.domain.HobbyScore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HobbyScoreResponse {
    @NotNull
    private String hobbyName;
    @NotNull
    private Double distance;

    public static HobbyScoreResponse from(HobbyScore hobbyScore) {
        return HobbyScoreResponse.builder()
                .hobbyName(hobbyScore.getHobbyWeight().getHobbyName())
                .distance(hobbyScore.getDistance())
                .build();
    }
}
