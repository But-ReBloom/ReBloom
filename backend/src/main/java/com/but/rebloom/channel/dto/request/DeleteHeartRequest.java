package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteHeartRequest {
    @NotNull
    private String userId;

    @NotNull
    private long postId;
}
