package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelResponse {
    private Long channelId;
    private String userId;
    private String channelTitle;
    private String channelIntro;
    private String channelDescription;
    private Boolean isAccepted;
    private LocalDateTime channelCreatedAt;

    public static CreateChannelResponse from(Channel channel) {
        return CreateChannelResponse.builder()
                .channelId(channel.getChannelId())
                .userId(channel.getUser().getUserId())
                .channelTitle(channel.getChannelTitle())
                .channelIntro(channel.getChannelIntro())
                .channelDescription(channel.getChannelDescription())
                .isAccepted(channel.getIsAccepted())
                .channelCreatedAt(channel.getChannelCreatedAt())
                .build();
    }
}
