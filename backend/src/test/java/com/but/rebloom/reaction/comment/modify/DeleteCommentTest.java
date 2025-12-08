package com.but.rebloom.reaction.comment.modify;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.channel.exception.ForbiddenAccessException;
import com.but.rebloom.domain.reaction.domain.Comment;
import com.but.rebloom.domain.reaction.exception.CommentNotFoundException;
import com.but.rebloom.domain.reaction.repository.CommentRepository;
import com.but.rebloom.domain.reaction.usecase.CommentUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteCommentTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 삭제 테스트 - 성공")
    public void deleteCommentSuccessTest() {
        // Given
        Long commentId = 1L;

        String userId = "testUser";

        Comment mockComment = Comment.builder()
                .commentId(commentId)
                .user(
                        User.builder()
                                .userId(userId)
                                .build()
                )
                .build();

        when(commentRepository.findByCommentId(commentId))
                .thenReturn(Optional.of(mockComment));
        doNothing().when(commentRepository).delete(any(Comment.class));

        // When & Then
        commentUseCase.deleteComment(commentId, userId);
    }

    @Test
    @DisplayName("댓글 삭제 테스트 - 댓글 조회 실패로 인한 실패")
    public void deleteCommentFailByCommentNotFoundTest() {
        // Given
        Long commentId = 1L;

        String userId = "testUser";

        // When & Then
        assertThrows(CommentNotFoundException.class,
                () -> commentUseCase.deleteComment(commentId, userId));
    }

    @Test
    @DisplayName("댓글 삭제 테스트 - 댓글 조회 실패로 인한 실패")
    public void deleteCommentFailByForbiddenAccessTest() {
        // Given
        Long commentId = 1L;

        String userId = "testUser";

        Comment mockComment = Comment.builder()
                .commentId(commentId)
                .user(
                        User.builder()
                                .userId("userId")
                                .build()
                )
                .build();

        when(commentRepository.findByCommentId(commentId))
                .thenReturn(Optional.of(mockComment));

        // When & Then
        assertThrows(ForbiddenAccessException.class,
                () -> commentUseCase.deleteComment(commentId, userId));
    }
}
