package com.but.rebloom.channel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindChannelRequest {
    private String channelTitle;
    private Long channelId;
    private String userId;
}
