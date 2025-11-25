package com.but.rebloom.channel.dto.response;

import com.but.rebloom.auth.domain.TierName;
import com.but.rebloom.channel.domain.Channel;
import com.but.rebloom.hobby.repository.ActivityRepository;
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
    @NotBlank
    private String linkedHobbyName;

    public static FindOneChannelResponse from(Map<Channel, String> response) {
        Map.Entry<Channel, String> entry = response.entrySet().iterator().next();
        Channel channel = entry.getKey();
        String linkedHobbyName = entry.getValue();

        return FindOneChannelResponse.builder()
                .channelName(channel.getChannelTitle())
                .userId(channel.getUser().getUserId())
                .userTier(channel.getUser().getUserTier())
                .channelIntro(channel.getChannelIntro())
                .linkedHobbyName(linkedHobbyName)
                .build();
    }
}
