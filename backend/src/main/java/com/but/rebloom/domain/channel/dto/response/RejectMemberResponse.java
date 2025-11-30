package com.but.rebloom.domain.channel.dto.response;

import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RejectMemberResponse {
    @NotBlank
    private String userEmail;
    @NotNull
    private Long channelId;
    @NotNull
    private VerifyStatus verifyStatus;

    public static RejectMemberResponse from(UserChannel userChannel) {
        return RejectMemberResponse.builder()
                .userEmail(userChannel.getUserEmail())
                .channelId(userChannel.getChannelId())
                .verifyStatus(userChannel.getUserChannelVerifyStatus())
                .build();
    }
}
