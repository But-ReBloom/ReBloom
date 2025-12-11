package com.but.rebloom.post.find;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.domain.Type;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.post.usecase.PostUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class GetPopularPostsTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 조회 테스트 - 성공")
    public void getPopularPostsSuccessTest() {
        // Given
        Post mockPost = Post.builder().build();
        List<Post> mockPosts = List.of(mockPost);

        when(postRepository.findByPostType(Type.POPULAR))
                .thenReturn(mockPosts);

        // When
        List<Post> posts = postUseCase.getPopularPosts();

        // Then
        assertThat(posts.size()).isEqualTo(mockPosts.size());
    }

    @Test
    @DisplayName("게시글 조회 테스트 - 게시글 조회 실패로 인한 실패")
    public void getPopularPostsFailByPostNotFoundTest() {
        // Given & When & Then
        assertThrows(PostNotFoundException.class,
                () -> postUseCase.getPopularPosts());
    }
}
