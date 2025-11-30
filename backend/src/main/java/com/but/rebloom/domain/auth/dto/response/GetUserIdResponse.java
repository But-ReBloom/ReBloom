package com.but.rebloom.domain.auth.dto.response;

import com.but.rebloom.domain.auth.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserIdResponse {
    @NotBlank
    private String userId;

    public static GetUserIdResponse from(User user) {
        return GetUserIdResponse.builder()
                .userId(user.getUserId())
                .build();
    }
}
