package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdatePwResponse {
    @NotNull
    private Boolean success;
    @NotNull
    private String userEmail;

    public static UpdatePwResponse from(User user) {
        return UpdatePwResponse.builder()
                .success(true)
                .userEmail(user.getUserEmail())
                .build();
    }
}
