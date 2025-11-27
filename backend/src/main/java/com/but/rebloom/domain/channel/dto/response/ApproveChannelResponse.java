package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.auth.domain.TierName;
import com.but.rebloom.domain.channel.domain.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ApproveChannelResponse {
    @NotNull
    private Long channelId;
    @NotNull
    private String userId;
    @NotNull
    private TierName userTier;
    @NotNull
    private String channelTitle;
    @NotNull
    private String channelIntro;
    @NotNull
    private String channelDescription;
    @NotNull
    private Boolean isAccepted;
    @NotNull
    private LocalDateTime channelCreatedAt;
    @NotBlank
    private String linkedHobbyName1;
    private String linkedHobbyName2;
    private String linkedHobbyName3;

    public static ApproveChannelResponse from(Channel channel) {
        return ApproveChannelResponse.builder()
                .channelId(channel.getChannelId())
                .userId(channel.getUser().getUserId())
                .userTier(channel.getUser().getUserTier())
                .channelTitle(channel.getChannelTitle())
                .channelIntro(channel.getChannelIntro())
                .channelDescription(channel.getChannelDescription())
                .isAccepted(channel.getIsAccepted())
                .channelCreatedAt(channel.getChannelCreatedAt())
                .linkedHobbyName1(channel.getChannelLinkedActivity1().getHobby().getHobbyName())
                .linkedHobbyName2(channel.getChannelLinkedActivity2().getHobby().getHobbyName())
                .linkedHobbyName3(channel.getChannelLinkedActivity3().getHobby().getHobbyName())
                .build();
    }
}
