package com.but.rebloom.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHeartRequest {
    @NotNull
    private String userId;

    @NotNull
    private long postId;
}
