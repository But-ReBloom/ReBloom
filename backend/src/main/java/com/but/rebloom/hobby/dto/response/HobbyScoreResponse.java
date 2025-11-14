package com.but.rebloom.hobby.dto.response;

import com.but.rebloom.hobby.domain.HobbyWeight;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HobbyScoreResponse {
    @NotNull
    private String hobbyName;
    @NotNull
    private Double distance;

    public static HobbyScoreResponse from(HobbyWeight hobbyWeight, Double distance) {
        return HobbyScoreResponse.builder()
                .hobbyName(hobbyWeight.getHobbyName())
                .distance(distance)
                .build();
    }
}