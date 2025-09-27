package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

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

    public static GoogleUserInfoResponse from(Map<User, Object> response) {
        Map.Entry<User, Object> entry = response.entrySet().iterator().next();
        User user = entry.getKey();
        GoogleUserInfoResponse googleUser = (GoogleUserInfoResponse) entry.getValue();

        return GoogleUserInfoResponse.builder()
                .id(googleUser.getId())
                .email(googleUser.getEmail())
                .name(googleUser.getName())
                .accessToken(googleUser.getAccessToken())
                .provider(user.getProvider())
                .build();
    }
}