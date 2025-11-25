package com.but.rebloom.reaction.dto.response;

import com.but.rebloom.auth.domain.TierName;
import com.but.rebloom.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class GetCommentCountResponse {
    @NotBlank
    private String postTitle;
    @NotBlank
    private String userId;
    @NotNull
    private TierName userTier;
    @NotNull
    private Long postCount;

    public static GetCommentCountResponse from(Map<Post, Long> response) {
        Map.Entry<Post, Long> entry = response.entrySet().iterator().next();
        Post post = entry.getKey();
        Long postCount = entry.getValue();

        return GetCommentCountResponse.builder()
                .postTitle(post.getPostTitle())
                .userId(post.getUser().getUserId())
                .userTier(post.getUser().getUserTier())
                .postCount(postCount)
                .build();
    }
}
