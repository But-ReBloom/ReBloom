package com.but.rebloom.domain.auth.dto.response;

import com.but.rebloom.domain.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateIdResponse {
    @NotNull
    private String userEmail;
    @NotNull
    private String userNewId;

    public static UpdateIdResponse from(User user) {
        return UpdateIdResponse.builder()
                .userEmail(user.getUserEmail())
                .userNewId(user.getUserId())
                .build();
    }
}
