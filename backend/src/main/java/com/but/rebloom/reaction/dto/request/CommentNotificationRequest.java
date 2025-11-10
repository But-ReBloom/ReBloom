package com.but.rebloom.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentNotificationRequest {
    @NotNull
    private String ownerUserId;

    @NotNull
    private String ownerEmail;

    @NotNull
    private String commenterUserId;

    @NotNull
    private String commentContent;

    @NotNull
    private Long CommentId;

    @NotNull
    private Long postId;
}
