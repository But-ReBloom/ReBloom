package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartRequest {
    @NotNull
    private Long postId;

    @NotNull
    private String userId;
}
