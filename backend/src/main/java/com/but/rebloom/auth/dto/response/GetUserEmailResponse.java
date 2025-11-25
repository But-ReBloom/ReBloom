package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserEmailResponse {
    @NotBlank
    private String userEmail;

    public static GetUserEmailResponse from(User user) {
        return GetUserEmailResponse.builder()
                .userEmail(user.getUserEmail())
                .build();
    }
}
