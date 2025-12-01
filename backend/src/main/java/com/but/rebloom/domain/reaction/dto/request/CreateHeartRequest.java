package com.but.rebloom.domain.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateHeartRequest {
    @NotNull
    private String userId;

    @NotNull
    private long postId;
}
