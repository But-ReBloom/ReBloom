package com.but.rebloom.reaction.comment.find;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.reaction.repository.CommentRepository;
import com.but.rebloom.domain.reaction.usecase.CommentUseCase;
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
public class GetCommentCountTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentUseCase commentUseCase;

    @Test
    @DisplayName("댓글 조회 테스트 - 성공")
    public void getCommentCountSuccessTest() {
        // Given
        Long postId = 1L;

        Long commentCount = 100L;

        Post mockPost = Post.builder().build();

        when(commentRepository.countByPost_PostId(postId))
                .thenReturn(commentCount);
        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));

        // When
        Map<Post, Long> result = commentUseCase.getCommentCount(postId);

        // Then
        assertThat(result).isNotNull();

        Map.Entry<Post, Long> entry = result.entrySet().iterator().next();
        Post post = entry.getKey();
        Long postCount = entry.getValue();

        assertThat(post).isEqualTo(mockPost);
        assertThat(postCount).isEqualTo(commentCount);
    }

    @Test
    @DisplayName("댓글 조회 테스트 - 댓글 조회 실패로 인한 실패")
    public void getCommentCountFailByCommentNotFoundTest() {
        // Given
        Long postId = 1L;

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> commentUseCase.getCommentCount(postId));
    }
}
