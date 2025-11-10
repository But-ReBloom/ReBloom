package com.but.rebloom.achievement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAndIdRequest {
    @NotBlank
    private String userEmail;
    @NotNull
    private Long achievementId;
}
