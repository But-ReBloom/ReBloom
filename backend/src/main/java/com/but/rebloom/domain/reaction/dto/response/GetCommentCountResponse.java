package com.but.rebloom.domain.reaction.dto.response;

import com.but.rebloom.domain.auth.domain.TierName;
import com.but.rebloom.domain.post.domain.Post;
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
    private Long commentCount;

    public static GetCommentCountResponse from(Map<Post, Long> response) {
        Map.Entry<Post, Long> entry = response.entrySet().iterator().next();
        Post post = entry.getKey();
        Long commentCount = entry.getValue();

        return GetCommentCountResponse.builder()
                .postTitle(post.getPostTitle())
                .userId(post.getUser().getUserId())
                .userTier(post.getUser().getUserTier())
                .commentCount(commentCount)
                .build();
    }
}
