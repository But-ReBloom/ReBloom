package com.but.rebloom.reaction.dto.response;

import com.but.rebloom.reaction.domain.Comment;
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
public class CreateCommentResponse {
    @NotNull
    private Long commentId;

    @NotNull
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    private Long postId;

    @NotNull
    private String commentContent;

    @NotNull
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
