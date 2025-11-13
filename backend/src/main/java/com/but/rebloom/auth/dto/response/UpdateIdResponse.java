package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
