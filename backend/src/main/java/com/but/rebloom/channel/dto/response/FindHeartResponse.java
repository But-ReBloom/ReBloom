package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Heart;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindHeartResponse {
    @NotNull
    private List<CreateHeartResponse> hearts;

    @NotNull
    private int totalCount;

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
