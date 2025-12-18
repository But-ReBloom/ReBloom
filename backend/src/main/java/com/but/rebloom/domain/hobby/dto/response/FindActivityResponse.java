package com.but.rebloom.domain.hobby.dto.response;

import com.but.rebloom.domain.hobby.domain.Activity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FindActivityResponse {
    @NotNull
    private Long activityId;
    @NotNull
    private String activityName;
    @NotNull
    private LocalDate activityStart;
    @NotNull
    private LocalDate activityRecent;
    @NotNull
    private Long linkedHobbyId;
    @NotBlank
    private String linkedHobbyName;

    public static FindActivityResponse from(Activity activity) {
        return FindActivityResponse.builder()
                .activityId(activity.getActivityId())
                .activityName(activity.getHobby().getHobbyName())
                .activityStart(activity.getActivityStart())
                .activityRecent(activity.getActivityRecent())
                .linkedHobbyId(activity.getHobby().getHobbyId())
                .linkedHobbyName(activity.getHobby().getHobbyName())
                .build();
    }
}
