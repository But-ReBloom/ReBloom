package com.but.rebloom.domain.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateChannelRequest {
    @NotNull
    private String channelTitle;

    @NotNull
    private String channelIntro;

    @NotNull
    private String channelDescription;

    @NotNull
    private String userEmail;

    @NotNull
    private Long channelLinkedHobby1;

    private Long channelLinkedHobby2;

    private Long channelLinkedHobby3;
}
