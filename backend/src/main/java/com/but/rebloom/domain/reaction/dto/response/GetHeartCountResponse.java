package com.but.rebloom.domain.reaction.dto.response;

import com.but.rebloom.domain.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class GetHeartCountResponse {
    @NotNull
    private Long postId;
    @NotBlank
    private String postTitle;
    @NotNull
    private Long heartCount;

    public static GetHeartCountResponse from(Map<Post, Long> response) {
        Map.Entry<Post, Long> entry = response.entrySet().iterator().next();
        Post post = entry.getKey();
        Long heartCount = entry.getValue();

        return GetHeartCountResponse.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .heartCount(heartCount)
                .build();
    }
}
