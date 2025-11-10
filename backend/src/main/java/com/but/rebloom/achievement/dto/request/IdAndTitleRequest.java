package com.but.rebloom.achievement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdAndTitleRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String achievementTitle;
}
