package com.but.rebloom.hobby.dto.response;

import com.but.rebloom.hobby.domain.Activity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AddActivityResponse {
    @NotNull
    private Boolean success;
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
                .success(true)
                .activityName(activity.getActivityName())
                .activityStart(activity.getActivityStart())
                .activityRecent(activity.getActivityRecent())
                .userEmail(activity.getUserEmail())
                .build();
    }
}
