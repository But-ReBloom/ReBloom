package com.but.rebloom.achievement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdAndIdRequest {
    @NotBlank
    private String userId;
    @NotNull
    private Long achievementId;
}
