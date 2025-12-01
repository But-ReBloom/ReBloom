package com.but.rebloom.domain.channel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchChannelRequest {
    @NotBlank
    private String keyword;
}
