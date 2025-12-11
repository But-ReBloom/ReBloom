package com.but.rebloom.post.modify;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.post.domain.Post;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class DeletePostTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 삭제 테스트 - 성공")
    public void deletePostSuccessTest() {
        // Given
        Long postId = 1L;

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Post mockPost = Post.builder()
                .user(mockUser)
                .build();

        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        doNothing().when(postRepository).delete(any(Post.class));

        // When & Then
        postUseCase.deletePost(postId);
    }

    @Test
    @DisplayName("게시글 삭제 테스트 - 게시글 조회 실패로 인한 실패")
    public void deletePostFailByPostNotFoundTest() {
        // Given
        Long postId = 1L;

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> postUseCase.deletePost(postId));
    }

    @Test
    @DisplayName("게시글 삭제 테스트 - 권한 부족으로 인한 실패")
    public void deletePostFailByNoAuthorityTest() {
        // Given
        Long postId = 1L;

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Post mockPost = Post.builder()
                .user(
                        User.builder()
                                .userEmail("post@email.com")
                                .build()
                )
                .build();

        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> postUseCase.deletePost(postId));
    }
}
