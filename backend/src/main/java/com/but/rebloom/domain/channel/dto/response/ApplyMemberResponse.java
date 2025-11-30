package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.channel.domain.UserChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplyMemberResponse {
    @NotBlank
    private String userEmail;
    @NotNull
    private Long channelId;
    @NotBlank
    private String applyMessage;

    public static ApplyMemberResponse from(UserChannel userChannel) {
        return ApplyMemberResponse.builder()
                .userEmail(userChannel.getUserEmail())
                .channelId(userChannel.getChannelId())
                .applyMessage(userChannel.getApplyMessage())
                .build();
    }
}
