package com.but.rebloom.post.find;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.domain.Status;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.post.usecase.PostUseCase;
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
public class GetPostsByStatusTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 조회 테스트 - 성공")
    public void getPostsByStatusSuccessTest() {
        // Given
        Status status = Status.REJECTED;

        Post mockPost = Post.builder().build();
        List<Post> mockPosts = List.of(mockPost);

        when(postRepository.findByPostStatus(status))
                .thenReturn(mockPosts);

        // When
        List<Post> posts = postUseCase.getPostsByStatus(status);

        // Then
        assertThat(posts.size()).isEqualTo(mockPosts.size());
    }

    @Test
    @DisplayName("게시글 조회 테스트 - 게시글 조회 실패로 인한 실패")
    public void getPostsByStatusFailByPostNotFoundTest() {
        // Given
        Status status = Status.REJECTED;

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> postUseCase.getPostsByStatus(status));
    }
}
