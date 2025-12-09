package com.but.rebloom.post.find;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.post.usecase.PostUseCase;
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
public class GetPostTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 조회 테스트 - 성공")
    public void getPostSuccessTest() {
        // Given
        Long postId = 1L;

        Post mockPost = Post.builder()
                .postId(postId)
                .postViewers(1)
                .build();

        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(postRepository.save(any(Post.class)))
                .thenReturn(mockPost);

        // When
        Post post = postUseCase.getPost(postId);

        // Then
        assertThat(post).isEqualTo(mockPost);
    }

    @Test
    @DisplayName("게시글 조회 테스트 - 게시글 조회 실패로 인한 실패")
    public void getPostFailByPostNotFoundTest() {
        // Given
        Long postId = 1L;

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> postUseCase.getPost(postId));
    }
}
