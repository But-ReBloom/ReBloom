package com.but.rebloom.domain.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteHeartRequest {
    @NotNull
    private String userId;

    @NotNull
    private Long postId;
}
