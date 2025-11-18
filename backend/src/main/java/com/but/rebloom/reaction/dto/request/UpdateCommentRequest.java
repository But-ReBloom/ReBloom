package com.but.rebloom.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @NotNull
    private Long commentId;
    @NotNull
    private String commentContent;
}
