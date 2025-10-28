package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Post;
import com.but.rebloom.channel.domain.Status;
import com.but.rebloom.channel.domain.Type;
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
    private Long postId;
    private String userId;
    private Long channelId;
    private String postTitle;
    private String postContent;
    private String postImage;
    private Type postType;
    private Status postStatus;
    private LocalDateTime postCreatedAt;
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
