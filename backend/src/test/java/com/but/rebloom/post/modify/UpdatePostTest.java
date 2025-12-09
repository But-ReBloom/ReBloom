package com.but.rebloom.post.modify;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.dto.request.UpdatePostRequest;
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
public class UpdatePostTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 수정 테스트 - 성공")
    public void updatePostSuccessTest() {
        // Given
        Long postId = 1L;
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(
                "userid",
                "posttitle",
                "postcontent",
                "postimage"
        );

        User mockUser = User.builder()
                .userEmail(updatePostRequest.getUserId())
                .build();

        Post mockPost = Post.builder()
                .postTitle("posttitle")
                .postContent("postcontent")
                .postImage("postimage")
                .postId(postId)
                .user(mockUser)
                .build();

        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(postRepository.save(any(Post.class)))
                .thenReturn(mockPost);

        // When
        Post post = postUseCase.updatePost(postId, updatePostRequest);

        // Then
        assertThat(post).isEqualTo(mockPost);
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 게시글 조회 실패로 인한 실패")
    public void updatePostFailByPostNotFoundTest() {
        // Given
        Long postId = 1L;
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(
                "userid",
                "posttitle",
                "postcontent",
                "postimage"
        );

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> postUseCase.updatePost(postId, updatePostRequest));
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 권한 부족으로 인한 실패")
    public void updatePostFailByNoAuthorityTest() {
        // Given
        Long postId = 1L;
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(
                "userid",
                "posttitle",
                "postcontent",
                "postimage"
        );

        User mockUser = User.builder()
                .userEmail(updatePostRequest.getUserId())
                .build();

        Post mockPost = Post.builder()
                .postTitle("posttitle")
                .postContent("postcontent")
                .postImage("postimage")
                .postId(postId)
                .user(
                        User.builder()
                                .userEmail("anotheruseremail@email.com")
                                .build()
                )
                .build();

        when(postRepository.findByPostId(postId))
                .thenReturn(Optional.of(mockPost));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> postUseCase.updatePost(postId, updatePostRequest));
    }
}
