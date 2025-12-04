package com.but.rebloom.domain.reaction.dto.response;

import com.but.rebloom.domain.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class CheckHeartExistsResponse {
    @NotBlank
    private String postTitle;
    @NotNull
    private Boolean isExists;

    public static CheckHeartExistsResponse from(Map<Post, Boolean> response) {
        Map.Entry<Post, Boolean> entry = response.entrySet().iterator().next();
        Post post = entry.getKey();
        Boolean isExists = entry.getValue();

        return CheckHeartExistsResponse.builder()
                .postTitle(post.getPostTitle())
                .isExists(isExists)
                .build();
    }
}
