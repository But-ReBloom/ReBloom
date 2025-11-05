package com.but.rebloom.post.dto.request;

import com.but.rebloom.post.domain.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
