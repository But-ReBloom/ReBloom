package com.but.rebloom.domain.reaction.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class GetHeartCountResponse {
    @NotBlank
    private String postTitle;
    @NotNull
    private Long heartCount;

    public static GetHeartCountResponse from(Map<String, Long> response) {
        Map.Entry<String, Long> entry = response.entrySet().iterator().next();
        String postTitle = entry.getKey();
        Long heartCount = entry.getValue();

        return GetHeartCountResponse.builder()
                .postTitle(postTitle)
                .heartCount(heartCount)
                .build();
    }
}
