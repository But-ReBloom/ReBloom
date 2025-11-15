package com.but.rebloom.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddActivityRequest {
    @NotNull
    private String activityName;
    @NotNull
    private LocalDate activityStart;
    @NotNull
    private LocalDate activityRecent;
    @NotNull
    private String userEmail;
}
