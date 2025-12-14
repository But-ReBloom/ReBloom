package com.but.rebloom.reaction.comment.modify;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.exception.ForbiddenAccessException;
import com.but.rebloom.domain.reaction.domain.Comment;
import com.but.rebloom.domain.reaction.dto.request.UpdateCommentRequest;
import com.but.rebloom.domain.reaction.exception.CommentNotFoundException;
import com.but.rebloom.domain.reaction.repository.CommentRepository;
import com.but.rebloom.domain.reaction.usecase.CommentUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UpdateCommentTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 수정 테스트 - 성공")
    public void updateCommentSuccessTest() {
        // Given
        Long commentId = 1L;

        UpdateCommentRequest request = new UpdateCommentRequest(
                "commentcontent"
        );

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Comment mockComment = Comment.builder()
                .user(mockUser)
                .commentContent("commentcontent")
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(commentRepository.findByCommentId(commentId))
                .thenReturn(Optional.of(mockComment));
        when(commentRepository.save(any(Comment.class)))
                .thenReturn(mockComment);

        // When
        Comment comment = commentUseCase.updateComment(commentId, request);

        // Then
        assertThat(comment).isEqualTo(mockComment);
    }

    @Test
    @DisplayName("댓글 수정 테스트 - 댓글 조회 실패로 인한 실패")
    public void updateCommentFailByCommentNotFoundTest() {
        // Given
        Long commentId = 100L;

        UpdateCommentRequest request = new UpdateCommentRequest(
                "commentcontent"
        );

        // When & Then
        assertThrows(CommentNotFoundException.class,
                () -> commentUseCase.updateComment(commentId, request));
    }

    @Test
    @DisplayName("댓글 수정 테스트 - 권한 부족으로 인한 실패")
    public void updateCommentFailByForbiddenAccessTest() {
        // Given
        Long commentId = 1L;

        UpdateCommentRequest request = new UpdateCommentRequest(
                "commentcontent"
        );

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Comment mockComment = Comment.builder()
                .user(
                        User.builder()
                                .userEmail("comment@email.com")
                                .build()
                )
                .commentContent("commentcontent")
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(commentRepository.findByCommentId(commentId))
                .thenReturn(Optional.of(mockComment));

        // When & Then
        assertThrows(ForbiddenAccessException.class,
                () -> commentUseCase.updateComment(commentId, request));
    }
}
