package com.but.rebloom.domain.post.dto.request;

import com.but.rebloom.domain.post.domain.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreatePostRequest {
    @NotNull
    private String userId;

    @NotNull
    private Long channelId;

    @NotNull
    private String postTitle;

    @NotNull
    private String postContent;

    private String postImage;
    private Type postType;
}
