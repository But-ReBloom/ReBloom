package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactPostRequest {
    @NotNull
    private Long postId;

    @NotNull
    private String userId;

    @NotNull
    private String commentContent;
}
