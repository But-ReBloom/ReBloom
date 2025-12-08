package com.but.rebloom.reaction.comment.find;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCommentTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 조회 테스트 - 성공")
    public void getCommentSuccessTest() {
        // Given
        Long commentId = 1L;

        Comment mockComment = Comment.builder().build();

        when(commentRepository.findByCommentId(commentId))
                .thenReturn(Optional.of(mockComment));

        // When
        Comment comment = commentUseCase.getComment(commentId);

        // Then
        assertThat(comment).isEqualTo(mockComment);
    }

    @Test
    @DisplayName("댓글 조회 테스트 - 댓글 조회 실패로 인한 실패")
    public void getCommentFailByCommentNotFoundTest() {
        // Given
        Long commentId = 1L;

        // When & Then
        assertThrows(CommentNotFoundException.class,
                () -> commentUseCase.getComment(commentId));
    }
}
