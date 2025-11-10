package com.but.rebloom.reaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeartNotificationRequest {
    @NotNull
    private String ownerUserId;

    @NotNull
    private String ownerEmail;

    @NotNull
    private String likerUserId;

    @NotNull
    private Long heartId;

    @NotNull
    private Long postId;
}
