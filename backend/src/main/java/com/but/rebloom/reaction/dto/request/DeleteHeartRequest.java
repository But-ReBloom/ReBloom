package com.but.rebloom.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteHeartRequest {
    @NotNull
    private String userId;

    @NotNull
    private long postId;
}
