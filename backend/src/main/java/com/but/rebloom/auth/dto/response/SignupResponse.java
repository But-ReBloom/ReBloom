package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@Setter
@Builder
public class SignupResponse {
    @NotNull
    private Boolean success;
    @NotNull
    private String userId;
    @NotNull
    private String userEmail;
    @NotNull
    private Provider provider;

    public static SignupResponse from(User user) {
        return SignupResponse.builder()
                .success(true)
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .provider(user.getProvider())
                .build();
    }
}
