package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateChannelRequest {
    @NotNull
    private String channelTitle;

    @NotNull
    private String channelIntro;

    @NotNull
    private String channelDescription;

    @NotNull
    private String userId;
}
