package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SearchChannelRequest {
    @NotBlank
    private String keyword;
}
