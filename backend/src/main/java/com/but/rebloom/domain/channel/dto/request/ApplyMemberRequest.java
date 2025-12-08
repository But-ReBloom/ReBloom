package com.but.rebloom.domain.channel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplyMemberRequest {
    @NotNull
    private Long channelId;
    @NotBlank
    private String applyMessage;
}
