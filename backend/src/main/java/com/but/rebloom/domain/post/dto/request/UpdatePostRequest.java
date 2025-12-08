package com.but.rebloom.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostRequest {
    @NotBlank
    private String userId;
    @NotNull
    private String postTitle;
    @NotNull
    private String postContent;
    private String postImage;
}
