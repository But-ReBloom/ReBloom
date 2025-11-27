package com.but.rebloom.domain.hobby.dto.response;

import com.but.rebloom.domain.hobby.domain.Activity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AddActivityResponse {
    @NotNull
    private String activityName;
    @NotNull
    private LocalDate activityStart;
    @NotNull
    private LocalDate activityRecent;
    @NotNull
    private String userEmail;

    public static AddActivityResponse from(Activity activity) {
        return AddActivityResponse.builder()
                .activityName(activity.getHobby().getHobbyName())
                .activityStart(activity.getActivityStart())
                .activityRecent(activity.getActivityRecent())
                .userEmail(activity.getUser().getUserEmail())
                .build();
    }
}
