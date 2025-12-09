package com.but.rebloom.post.admin;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.exception.AlreadyProcessedChannelException;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.domain.Status;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.post.usecase.PostUseCase;
import com.but.rebloom.global.exception.NoAuthorityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RejectPostTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 거절 테스트 - 성공")
    public void rejectPostSuccessTest() {
        // Given
        Long postId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Post mockPost = Post.builder()
                .postStatus(Status.PENDING)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(postRepository.save(any(Post.class)))
                .thenReturn(mockPost);

        // When
        Post post = postUseCase.rejectPost(postId);

        // Then
        assertThat(post.getPostStatus()).isEqualTo(Status.REJECTED);
    }

    @Test
    @DisplayName("게시글 거절 테스트 - 권한 부족으로 실패")
    public void rejectPostFailByNoAuthorityTest() {
        // Given
        Long postId = 1L;

        User mockUser = User.builder()
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> postUseCase.approvePost(postId));
    }

    @Test
    @DisplayName("게시글 거절 테스트 - 게시글 조회 실패로 인한 실패")
    public void rejectPostFailByPostNotFoundTest() {
        // Given
        Long postId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> postUseCase.rejectPost(postId));
    }

    @Test
    @DisplayName("게시글 거절 테스트 - 이미 승인된 게시글로 인한 실패")
    public void rejectPostFailByAlreadyProceedPostTest() {
        // Given
        Long postId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Post mockPost = Post.builder()
                .postStatus(Status.REJECTED)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));

        // When & Then
        assertThrows(AlreadyProcessedChannelException.class,
                () -> postUseCase.rejectPost(postId));
    }
}
