package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.auth.domain.TierName;
import com.but.rebloom.domain.channel.domain.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class FindOneChannelResponse {
    @NotBlank
    private String channelName;
    @NotBlank
    private String userId;
    @NotNull
    private TierName userTier;
    @NotBlank
    private String channelIntro;
    @NotNull
    private Long linkedHobbyId1;
    @NotBlank
    private String linkedHobbyName1;
    private Long linkedHobbyId2;
    private String linkedHobbyName2;
    private Long linkedHobbyId3;
    private String linkedHobbyName3;

    public static FindOneChannelResponse from(Map<Channel, String> response) {
        Map.Entry<Channel, String> entry = response.entrySet().iterator().next();
        Channel channel = entry.getKey();
        String linkedHobbyName = entry.getValue();

        return FindOneChannelResponse.builder()
                .channelName(channel.getChannelTitle())
                .userId(channel.getUser().getUserId())
                .userTier(channel.getUser().getUserTier())
                .channelIntro(channel.getChannelIntro())
                .linkedHobbyId1(channel.getChannelLinkedHobby1().getHobbyId())
                .linkedHobbyName1(channel.getChannelLinkedHobby1().getHobbyName())
                .linkedHobbyId2(channel.getChannelLinkedHobby2().getHobbyId())
                .linkedHobbyName2(channel.getChannelLinkedHobby2().getHobbyName())
                .linkedHobbyId3(channel.getChannelLinkedHobby3().getHobbyId())
                .linkedHobbyName3(channel.getChannelLinkedHobby3().getHobbyName())
                .build();
    }
}
