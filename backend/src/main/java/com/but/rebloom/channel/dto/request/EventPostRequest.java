package com.but.rebloom.channel.dto.request;

import com.but.rebloom.channel.domain.Status;
import jakarta.validation.constraints.NotNull;

public class EventPostRequest {
    @NotNull
    private Long postId;

    private Status postStatus;
}
