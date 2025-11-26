package com.but.rebloom.domain.auth.dto.response;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    @NotNull
    private String userEmail;
    @NotNull
    private Provider userProvider;
    @NotNull
    private String token;

    public static LoginResponse from(User user, String jwtToken) {
        return LoginResponse.builder()
                .userEmail(user.getUserEmail())
                .userProvider(user.getUserProvider())
                .token(jwtToken)
                .build();
    }
}