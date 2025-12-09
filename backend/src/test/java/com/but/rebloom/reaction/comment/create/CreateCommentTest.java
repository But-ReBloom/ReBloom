package com.but.rebloom.reaction.comment.create;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.reaction.domain.Comment;
import com.but.rebloom.domain.reaction.dto.request.CreateCommentRequest;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCommentTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 추가 테스트 - 성공")
    public void createCommentSuccessTest() {
        // Given
        CreateCommentRequest createCommentRequest = new CreateCommentRequest(
                "userid",
                1L,
                "commentcontent"
        );

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .userId(createCommentRequest.getUserId())
                .build();

        Post mockPost = Post.builder().postId(1L)
                .user(mockUser)
                .postId(createCommentRequest.getPostId())
                .build();

        Comment mockComment = Comment.builder()
                .user(mockUser)
                .post(mockPost)
                .commentContent(createCommentRequest.getCommentContent())
                .build();

        when(userRepository.findByUserId(createCommentRequest.getUserId()))
                .thenReturn(Optional.of(mockUser));
        when(postRepository.findByPostId(createCommentRequest.getPostId()))
                .thenReturn(Optional.of(mockPost));
        doNothing().when(defaultUserAchievementUseCase).updateUserAchievementToSuccess(anyString(), anyString());
        doNothing().when(defaultUserAchievementUseCase).updateUserAchievementProgress(anyString(), anyString(), anyFloat());
        when(commentRepository.save(any(Comment.class)))
                .thenReturn(mockComment);

        // When
        Comment comment = commentUseCase.createComment(createCommentRequest);

        // Then
        assertThat(comment).isEqualTo(mockComment);
    }

    @Test
    @DisplayName("댓글 추가 테스트 - 유저 조회 실패로 인한 실패")
    public void createCommentFailByUserNotFoundTest() {
        // Given
        CreateCommentRequest createCommentRequest = new CreateCommentRequest(
                "userid",
                1L,
                "commentcontent"
        );

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> commentUseCase.createComment(createCommentRequest));
    }

    @Test
    @DisplayName("댓글 추가 테스트 - 게시글 조회 실패로 인한 실패")
    public void createCommentFailByPostNotFoundTest() {
        // Given
        CreateCommentRequest createCommentRequest = new CreateCommentRequest(
                "userid",
                1L,
                "commentcontent"
        );

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .userId(createCommentRequest.getUserId())
                .build();

        when(userRepository.findByUserId(createCommentRequest.getUserId()))
                .thenReturn(Optional.of(mockUser));

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> commentUseCase.createComment(createCommentRequest));
    }
}
