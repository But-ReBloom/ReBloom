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
    @NotNull
    private Long linkedHobbyId1;
    @NotBlank
    private String linkedHobbyName1;
    private Long linkedHobbyId2;
    private String linkedHobbyName2;
    private Long linkedHobbyId3;
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
                .linkedHobbyId1(channel.getChannelLinkedHobby1().getHobbyId())
                .linkedHobbyName1(channel.getChannelLinkedHobby1().getHobbyName())
                .linkedHobbyId2(channel.getChannelLinkedHobby2().getHobbyId())
                .linkedHobbyName2(channel.getChannelLinkedHobby2().getHobbyName())
                .linkedHobbyId3(channel.getChannelLinkedHobby3().getHobbyId())
                .linkedHobbyName3(channel.getChannelLinkedHobby3().getHobbyName())
                .build();
    }
}
