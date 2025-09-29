package com.but.rebloom.channel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {
    @NotNull
    private String postTitle;

    @NotNull
    private String postContent;

    private String postImage;

    @NotNull
    private String userId;
}
