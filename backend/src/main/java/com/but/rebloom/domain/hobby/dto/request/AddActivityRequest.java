package com.but.rebloom.domain.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddActivityRequest {
    @NotNull
    private String activityName;
}
