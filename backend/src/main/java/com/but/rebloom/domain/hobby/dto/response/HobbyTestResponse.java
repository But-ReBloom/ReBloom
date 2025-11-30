package com.but.rebloom.domain.hobby.dto.response;

import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class HobbyTestResponse {
    @NotNull
    private List<HobbyScoreResponse> hobbyScores;
    @NotNull
    private List<ChannelResponse> channels;

    public static HobbyTestResponse from(Map<List<HobbyScore>, List<Channel>> responses) {
        Map.Entry<List<HobbyScore>, List<Channel>> entry = responses.entrySet().iterator().next();
        List<HobbyScore> hobbyScores = entry.getKey();
        List<Channel> channels = entry.getValue();

        List<HobbyScoreResponse> hobbyScoreResponses = hobbyScores.stream()
                .map(HobbyScoreResponse::from)
                .toList();

        List<ChannelResponse> channelResponses = channels.stream()
                .map(ChannelResponse::from)
                .toList();

        return HobbyTestResponse.builder()
                .hobbyScores(hobbyScoreResponses)
                .channels(channelResponses)
                .build();
    }
}