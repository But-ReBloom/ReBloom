package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    @NotNull
    private Boolean success;
    @NotNull
    private String userEmail;
    @NotNull
    private Provider provider;
    @NotNull
    private String token;

    public static LoginResponse from(User user, String jwtToken) {
        return LoginResponse.builder()
                .success(true)
                .userEmail(user.getUserEmail())
                .provider(user.getProvider())
                .token(jwtToken)
                .build();
    }
}