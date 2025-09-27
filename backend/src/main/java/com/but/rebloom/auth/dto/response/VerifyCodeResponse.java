package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class VerifyCodeResponse {
    @NotNull
    private Boolean success;
    @NotNull
    private String userEmail;

    public static VerifyCodeResponse from(Map<User, String> response) {
        Map.Entry<User, String> entry = response.entrySet().iterator().next();
        User user = entry.getKey();
        String code = entry.getValue();

        return VerifyCodeResponse.builder()
                .success(true)
                .userEmail(user.getUserEmail())
                .build();
    }
}
