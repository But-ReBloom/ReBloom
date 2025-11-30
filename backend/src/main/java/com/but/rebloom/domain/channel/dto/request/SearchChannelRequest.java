package com.but.rebloom.domain.channel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SearchChannelRequest {
    @NotBlank
    private String keyword;
}
