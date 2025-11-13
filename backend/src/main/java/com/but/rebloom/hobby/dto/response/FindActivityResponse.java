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
public class FindActivityResponse {
    @NotNull
    private String activityName;
    @NotNull
    private LocalDate activityStart;
    @NotNull
    private LocalDate activityRecent;

    public static FindActivityResponse from(Activity activity) {
        return FindActivityResponse.builder()
                .activityName(activity.getActivityName())
                .activityStart(activity.getActivityStart())
                .activityRecent(activity.getActivityRecent())
                .build();
    }
}
