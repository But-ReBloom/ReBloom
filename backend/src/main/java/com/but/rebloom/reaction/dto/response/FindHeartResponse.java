package com.but.rebloom.reaction.dto.response;

import com.but.rebloom.reaction.domain.Heart;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FindHeartResponse {
    @NotNull
    private List<CreateHeartResponse> hearts;
    @NotNull
    private Integer totalCount;

    public static FindHeartResponse from(List<Heart> hearts) {
        List<CreateHeartResponse> heartResponses = hearts.stream()
                .map(CreateHeartResponse::from)
                .collect(Collectors.toList());

        return FindHeartResponse.builder()
                .hearts(heartResponses)
                .totalCount(heartResponses.size())
                .build();
    }
}
