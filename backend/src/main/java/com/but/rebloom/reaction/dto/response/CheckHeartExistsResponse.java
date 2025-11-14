package com.but.rebloom.reaction.dto.response;

import com.but.rebloom.reaction.dto.request.CheckIsHeartExistsRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class CheckHeartExistsResponse {
    @NotBlank
    private String postTitle;
    @NotNull
    private Boolean isExists;

    public static CheckHeartExistsResponse from(Map<String, Boolean> response) {
        Map.Entry<String, Boolean> entry = response.entrySet().iterator().next();
        String postTitle = entry.getKey();
        Boolean isExists = entry.getValue();

        return CheckHeartExistsResponse.builder()
                .postTitle(postTitle)
                .isExists(isExists)
                .build();
    }
}
