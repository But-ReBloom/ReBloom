package com.but.rebloom.hobby.dto.response;

import com.but.rebloom.hobby.domain.Activity;
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
                .activityName(activity.getActivityName())
                .activityStart(activity.getActivityStart())
                .activityRecent(activity.getActivityRecent())
                .userEmail(activity.getUser().getUserEmail())
                .build();
    }
}
