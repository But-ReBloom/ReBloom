package com.but.rebloom.reaction.heart.find;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.reaction.dto.request.CheckHeartExistsRequest;
import com.but.rebloom.domain.reaction.repository.HeartRepository;
import com.but.rebloom.domain.reaction.usecase.HeartUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckHeartExistsTest {
    @Mock
    private HeartRepository heartRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private HeartUseCase heartUseCase;

    @Test
    @DisplayName("하트 존재 확인 테스트 - 성공")
    public void checkHeartExistsSuccessTest() {
        // Given
        CheckHeartExistsRequest request = new CheckHeartExistsRequest(
                "userid",
                1L
        );

        boolean mockIsSuccess = false;

        Post mockPost = Post.builder().build();

        when(heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId()))
                .thenReturn(mockIsSuccess);
        when(postRepository.findByPostId(request.getPostId()))
                .thenReturn(Optional.of(mockPost));

        // When
        Map<Post, Boolean> result = heartUseCase.checkHeartExists(request);

        // Then
        assertThat(result).isNotNull();

        Map.Entry<Post, Boolean> entry = result.entrySet().iterator().next();
        Post post = entry.getKey();
        Boolean isSuccess = entry.getValue();

        assertThat(post).isEqualTo(mockPost);
        assertThat(isSuccess).isEqualTo(mockIsSuccess);
    }

    @Test
    @DisplayName("하트 존재 확인 테스트 - 게시글 조회 실패로 인한 실패")
    public void checkHeartExistsFailByPostNotFoundTest() {
        // Given
        CheckHeartExistsRequest request = new CheckHeartExistsRequest(
                "userid",
                1L
        );

        boolean mockIsSuccess = false;

        when(heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId()))
                .thenReturn(mockIsSuccess);

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> heartUseCase.checkHeartExists(request));
    }
}
