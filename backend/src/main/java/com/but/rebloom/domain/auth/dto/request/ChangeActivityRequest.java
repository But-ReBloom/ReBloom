package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeActivityRequest {
    @NotNull
    private Long activityId;
}
