package com.but.rebloom.auth.dto.response;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.Role;
import com.but.rebloom.auth.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindUserInfoResponse {
    @NotNull
    private String userEmail;
    @NotNull
    private String userId;
    @NotNull
    private String userName;
    @NotNull
    private Role userRole;
    @NotNull
    private Integer userTierPoint;
    @NotNull
    private Integer userPoint;
    @NotNull
    private Provider userProvider;

    public static FindUserInfoResponse from(User user) {
        return FindUserInfoResponse.builder()
                .userEmail(user.getUserEmail())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userRole(user.getUserRole())
                .userTierPoint(user.getUserTierPoint())
                .userPoint(user.getUserPoint())
                .userProvider(user.getUserProvider())
                .build();
    }
}
