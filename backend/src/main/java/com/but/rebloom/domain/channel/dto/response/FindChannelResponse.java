package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.channel.domain.Channel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FindChannelResponse {
    @NotNull
    private List<FindOneChannelResponse> responses;
    @NotNull
    private Integer totalCount;

    public static FindChannelResponse from(List<Channel> channels) {
        List<FindOneChannelResponse> channelResponses = channels.stream()
                .map(FindOneChannelResponse::from)
                .collect(Collectors.toList());

        return FindChannelResponse.builder()
                .responses(channelResponses)
                .totalCount(channelResponses.size())
                .build();
    }
}
