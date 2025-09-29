package com.but.rebloom.channel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPostRequest {
    private String postTitle;
    private Long postId;
    private String userId;
}
