package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserChannelDetailedInfoResponse {
    @NotBlank
    private String userEmail;
    @NotNull
    private Long channelId;
    @NotNull
    private VerifyStatus verifyStatus;
    @NotBlank
    private String applyMessage;

    public static GetUserChannelDetailedInfoResponse from(UserChannel userChannel) {
        return GetUserChannelDetailedInfoResponse.builder()
                .userEmail(userChannel.getUser().getUserEmail())
                .channelId(userChannel.getChannel().getChannelId())
                .verifyStatus(userChannel.getUserChannelVerifyStatus())
                .applyMessage(userChannel.getApplyMessage())
                .build();
    }
}
