package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.channel.domain.UserChannel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class GetUserChannelInfoResponse {
    @NotNull
    private List<GetUserChannelDetailedInfoResponse> userChannels;
    @NotNull
    private Integer applyCount;

    public static GetUserChannelInfoResponse from(List<UserChannel> userChannels) {
        List<GetUserChannelDetailedInfoResponse> userChannelResponses = userChannels.stream()
                .map(GetUserChannelDetailedInfoResponse::from)
                .collect(Collectors.toList());

        return GetUserChannelInfoResponse.builder()
                .userChannels(userChannelResponses)
                .applyCount(userChannels.size())
                .build();
    }
}
