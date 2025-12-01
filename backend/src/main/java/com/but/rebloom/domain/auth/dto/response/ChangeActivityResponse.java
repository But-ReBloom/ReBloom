package com.but.rebloom.domain.auth.dto.response;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.ChangeActivityRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeActivityResponse {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotNull
    private Long activityId;
    @NotBlank
    private String activityName;

    public static ChangeActivityResponse from(User user) {
        return ChangeActivityResponse.builder()
                .userEmail(user.getUserEmail())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .activityId(user.getUserCurrentActivity().getActivityId())
                .activityName(user.getUserCurrentActivity().getHobby().getHobbyName())
                .build();
    }
}
