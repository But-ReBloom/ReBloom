package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageChannelRequest {
    @NotNull
    private Long channelId;

    @NotNull
    private boolean isAccepted;
}
