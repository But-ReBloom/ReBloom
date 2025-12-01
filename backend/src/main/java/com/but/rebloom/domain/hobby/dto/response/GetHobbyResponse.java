package com.but.rebloom.domain.hobby.dto.response;

import com.but.rebloom.domain.hobby.domain.Hobby;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetHobbyResponse {
    @NotNull
    private Long hobbyId;
    @NotBlank
    private String hobbyName;
    @NotNull
    private Double hobbyWeightSocial;
    @NotNull
    private Double hobbyWeightLearning;
    @NotNull
    private Double hobbyWeightPlanning;
    @NotNull
    private Double hobbyWeightFocus;
    @NotNull
    private Double hobbyWeightCreativity;

    public static GetHobbyResponse from(Hobby hobby) {
        return GetHobbyResponse.builder()
                .hobbyId(hobby.getHobbyId())
                .hobbyName(hobby.getHobbyName())
                .hobbyWeightSocial(hobby.getHobbyWeightSocial())
                .hobbyWeightLearning(hobby.getHobbyWeightLearning())
                .hobbyWeightPlanning(hobby.getHobbyWeightPlanning())
                .hobbyWeightFocus(hobby.getHobbyWeightFocus())
                .hobbyWeightCreativity(hobby.getHobbyWeightCreativity())
                .build();
    }
}
