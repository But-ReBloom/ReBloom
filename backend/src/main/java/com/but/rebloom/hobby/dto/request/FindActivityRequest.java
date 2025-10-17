package com.but.rebloom.hobby.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindActivityRequest {
    @NotNull
    private Long activityId;
}
