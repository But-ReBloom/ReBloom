package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ChangeActivityRequest {
    @NotNull
    private Long activityId;
}
