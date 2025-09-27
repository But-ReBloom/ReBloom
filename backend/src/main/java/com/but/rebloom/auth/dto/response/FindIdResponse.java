package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindIdResponse {
    @NotNull
    private String userId;

    public static FindIdResponse from(User user) {
        return FindIdResponse.builder()
                .userId(user.getUserId())
                .build();
    }
}
