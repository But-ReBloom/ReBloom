package com.but.rebloom.domain.auth.dto.response;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupResponse {
    @NotNull
    private String userId;
    @NotNull
    private String userEmail;
    @NotNull
    private Provider userProvider;

    public static SignupResponse from(User user) {
        return SignupResponse.builder()
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .userProvider(user.getUserProvider())
                .build();
    }
}
