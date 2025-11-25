package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Channel;
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

    public static ApproveChannelResponse from(Map<Channel, List<String>> response) {
        Map.Entry<Channel, List<String>> entry = response.entrySet().iterator().next();
        Channel channel = entry.getKey();
        List<String> linkedHobbyNames = entry.getValue();

        return ApproveChannelResponse.builder()
                .channelId(channel.getChannelId())
                .userId(channel.getUser().getUserId())
                .channelTitle(channel.getChannelTitle())
                .channelIntro(channel.getChannelIntro())
                .channelDescription(channel.getChannelDescription())
                .isAccepted(channel.getIsAccepted())
                .channelCreatedAt(channel.getChannelCreatedAt())
                .linkedHobbyName1(linkedHobbyNames.get(0))
                .linkedHobbyName2(linkedHobbyNames.get(1))
                .linkedHobbyName3(linkedHobbyNames.get(2))
                .build();
    }
}
