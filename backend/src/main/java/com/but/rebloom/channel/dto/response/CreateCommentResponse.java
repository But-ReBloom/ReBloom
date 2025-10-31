package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentResponse {
    private Long commentId;
    private String userId;
    private String userName;
    private Long postId;
    private String commentContent;
    private LocalDateTime commentCreatedAt;

    public static CreateCommentResponse from(Comment comment) {
        return CreateCommentResponse.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUser().getUserId())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getPostId())
                .commentContent(comment.getCommentContent())
                .commentCreatedAt(comment.getCommentCreatedAt())
                .build();
    }
}
