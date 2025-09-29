package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindEmailResponse {
    @NotNull
    private String userEmail;

    public static FindEmailResponse from(User user) {
        return FindEmailResponse.builder()
                .userEmail(user.getUserEmail())
                .build();
    }
}
