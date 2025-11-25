package com.but.rebloom.reaction.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CheckHeartExistsRequest {
    @NotBlank
    private String userId;
    @NotNull
    private Long postId;
}
