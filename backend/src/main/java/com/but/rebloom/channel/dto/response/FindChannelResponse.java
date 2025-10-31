package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindChannelResponse {
    private List<CreateChannelResponse> channels;
    private int totalCount;

    public static FindChannelResponse from(List<Channel> channels) {
        List<CreateChannelResponse> channelResponses = channels.stream()
                .map(CreateChannelResponse::from)
                .collect(Collectors.toList());

        return FindChannelResponse.builder()
                .channels(channelResponses)
                .totalCount(channelResponses.size())
                .build();
    }
}
