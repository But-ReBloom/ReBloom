package com.but.rebloom.domain.channel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ApproveMemberRequest {
    @NotBlank
    private String userEmail;
    @NotNull
    private Long channelId;
}
