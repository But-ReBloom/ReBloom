package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserIdResponse {
    @NotNull
    private String userId;

    public static GetUserIdResponse from(User user) {
        return GetUserIdResponse.builder()
                .userId(user.getUserId())
                .build();
    }
}
