package com.but.rebloom.reaction.comment.modify;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.reaction.domain.Comment;
import com.but.rebloom.domain.reaction.exception.CommentNotFoundException;
import com.but.rebloom.domain.reaction.repository.CommentRepository;
import com.but.rebloom.domain.reaction.usecase.CommentUseCase;
import com.but.rebloom.global.exception.NoAuthorityException;
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
public class DeleteCommentByAdminTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 삭제 테스트 - 성공")
    public void deleteCommentByAdminSuccessTest() {
        // Given
        Long commentId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Comment mockComment = Comment.builder().build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(commentRepository.findByCommentId(commentId))
                .thenReturn(Optional.of(mockComment));
        doNothing().when(commentRepository).delete(any(Comment.class));

        // When & Then
        commentUseCase.deleteCommentByAdmin(commentId);
    }

    @Test
    @DisplayName("댓글 삭제 테스트 - 권한 부족으로 인한 실패")
    public void deleteCommentByAdminFailByNoAuthorityTest() {
        // Given
        Long commentId = 1L;

        User mockUser = User.builder()
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> commentUseCase.deleteCommentByAdmin(commentId));
    }

    @Test
    @DisplayName("댓글 삭제 테스트 - 댓글 조회 실패 인한 실패")
    public void deleteCommentByAdminFailByCommentNotFoundTest() {
        // Given
        Long commentId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(CommentNotFoundException.class,
                () -> commentUseCase.deleteCommentByAdmin(commentId));
    }
}
