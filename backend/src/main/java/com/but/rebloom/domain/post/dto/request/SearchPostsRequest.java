package com.but.rebloom.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SearchPostsRequest {
    @NotBlank
    private String keyword;
}
