package com.but.rebloom.achievement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAndTitleRequest {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String achievementTitle;
}
