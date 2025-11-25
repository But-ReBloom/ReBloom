package com.but.rebloom.hobby.dto.response;

import com.but.rebloom.channel.domain.Channel;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChannelResponse {
    @NotBlank
    private String channelName;

    public static ChannelResponse from(Channel channel) {
        return ChannelResponse.builder()
                .channelName(channel.getChannelTitle())
                .build();
    }
}
