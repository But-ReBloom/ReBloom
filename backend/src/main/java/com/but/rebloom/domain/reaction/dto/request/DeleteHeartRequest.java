package com.but.rebloom.domain.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteHeartRequest {
    @NotNull
    private String userId;

    @NotNull
    private Long postId;
}
