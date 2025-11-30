package com.but.rebloom.domain.hobby.dto.response;

import com.but.rebloom.domain.hobby.domain.Hobby;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class GetAllHobbyResponse {
    @NotNull
    private List<GetHobbyResponse> hobbies;

    public static GetAllHobbyResponse from(List<Hobby> hobbies) {
        List<GetHobbyResponse> getHobbyResponses = hobbies.stream()
                .map(GetHobbyResponse::from)
                .collect(Collectors.toList());

        return GetAllHobbyResponse.builder()
                .hobbies(getHobbyResponses)
                .build();
    }
}
