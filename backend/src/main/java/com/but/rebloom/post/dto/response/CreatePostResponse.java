package com.but.rebloom.post.dto.response;

import com.but.rebloom.post.domain.Post;
import com.but.rebloom.post.domain.Status;
import com.but.rebloom.post.domain.Type;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostResponse {
    @NotNull
    private Long postId;

    @NotNull
    private String userId;

    @NotNull
    private Long channelId;

    @NotNull
    private String postTitle;

    @NotNull
    private String postContent;

    @NotNull
    private String postImage;

    @NotNull
    private Type postType;

    @NotNull
    private Status postStatus;

    @NotNull
    private LocalDateTime postCreatedAt;

    @NotNull
    private int viewers;

    public static CreatePostResponse from(Post post) {
        return CreatePostResponse.builder()
                .postId(post.getPostId())
                .userId(post.getUser().getUserId())
                .channelId(post.getChannel().getChannelId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postImage(post.getPostImage())
                .postType(post.getPostType())
                .postStatus(post.getPostStatus())
                .postCreatedAt(post.getPostCreatedAt())
                .viewers(post.getVeiwers())
                .build();
    }
}
