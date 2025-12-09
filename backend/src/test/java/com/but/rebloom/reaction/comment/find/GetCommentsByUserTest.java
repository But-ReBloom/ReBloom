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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCommentsByUserTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 조회 테스트 - 성공")
    public void getCommentsByUserSuccessTest() {
        // Given
        String userId = "userId";

        Comment mockComment = Comment.builder().build();
        List<Comment> mockComments = List.of(mockComment);

        when(commentRepository.findByUser_UserIdOrderByCommentCreatedAtDesc(userId))
                .thenReturn(mockComments);

        // When
        List<Comment> comments = commentUseCase.getCommentsByUser(userId);

        // Then
        assertThat(comments.size()).isEqualTo(mockComments.size());
    }

    @Test
    @DisplayName("댓글 조회 테스트 - 댓글 조회 실패로 인한 실패")
    public void getCommentsByUserFailByCommentNotFoundTest() {
        // Given
        String userId = "userId";

        // When & Then
        assertThrows(CommentNotFoundException.class,
                () -> commentUseCase.getCommentsByUser(userId));
    }
}
