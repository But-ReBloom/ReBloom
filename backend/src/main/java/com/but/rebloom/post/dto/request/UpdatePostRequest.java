package com.but.rebloom.post.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostRequest {
    @NotNull
    private String postTitle;

    @NotNull
    private String postContent;

    private String postImage;
}
