package com.but.rebloom.domain.auth.dto.response;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GoogleUserInfoResponse {
    @NotNull
    private String id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String accessToken;
    @NotNull
    private Provider provider;

    public static GoogleUserInfoResponse from(User user, String jwt) {
        return GoogleUserInfoResponse.builder()
                .id(user.getUserId())
                .email(user.getUserEmail())
                .name(user.getUserName())
                .accessToken(jwt)
                .provider(user.getUserProvider())
                .build();
    }
}