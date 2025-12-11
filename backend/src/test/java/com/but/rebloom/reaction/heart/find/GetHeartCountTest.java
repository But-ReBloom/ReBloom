package com.but.rebloom.reaction.heart.find;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.reaction.repository.HeartRepository;
import com.but.rebloom.domain.reaction.usecase.HeartUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class GetHeartCountTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private HeartRepository heartRepository;
    @InjectMocks
    private HeartUseCase heartUseCase;

    @Test
    @DisplayName("하트 수 검색 테스트 - 성공")
    public void getHeartCountSuccessTest() {
        // Given
        Long postId = 1L;

        Post mockPost = Post.builder()
                .postId(postId)
                .build();

        Long mockHeartCount = 100L;

        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(heartRepository.countByPost_PostId(postId))
                .thenReturn(mockHeartCount);

        // When
        Map<Post, Long> result = heartUseCase.getHeartCount(postId);

        // Then
        assertThat(result).isNotNull();

        Map.Entry<Post, Long> entry = result.entrySet().iterator().next();
        Post post = entry.getKey();
        Long heartCount = entry.getValue();

        assertThat(post).isEqualTo(mockPost);
        assertThat(heartCount).isEqualTo(mockHeartCount);
    }

    @Test
    @DisplayName("하트 수 검색 테스트 - 게시글 조회 실패로 인한 실패")
    public void getHeartCountFailByPostNotFoundTest() {
        // Given
        Long postId = 1L;

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> heartUseCase.getHeartCount(postId));
    }
}
