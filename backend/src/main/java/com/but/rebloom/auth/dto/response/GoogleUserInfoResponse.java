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
                .id(user.getUserId().toString())
                .email(user.getUserEmail())
                .name(user.getUserName())
                .accessToken(jwt)
                .provider(user.getProvider())
                .build();
    }
}