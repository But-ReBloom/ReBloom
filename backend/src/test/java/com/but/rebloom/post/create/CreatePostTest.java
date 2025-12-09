package com.but.rebloom.post.create;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.domain.Type;
import com.but.rebloom.domain.post.dto.request.CreatePostRequest;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreatePostTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostUseCase postUseCase;

    @Test
    @DisplayName("게시글 생성 테스트 - 성공")
    public void createPostSuccessTest() {
        // Given
        CreatePostRequest createPostRequest = new CreatePostRequest(
                "userid",
                1L,
                "posttitle",
                "postcontent",
                "postimage",
                Type.NORMAL
        );

        User mockUser = User.builder()
                .userEmail("useremail@email.com")
                .build();

        Channel mockChannel = Channel.builder().build();

        Post mockPost = Post.builder().build();

        when(userRepository.findByUserId(createPostRequest.getUserId()))
                .thenReturn(Optional.of(mockUser));
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(createPostRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        doNothing().when(defaultUserAchievementUseCase).updateUserAchievementToSuccess(anyString(), anyString());
        when(postRepository.save(any(Post.class)))
                .thenReturn(mockPost);

        // When
        Post post = postUseCase.createPost(createPostRequest);

        // Then
        assertThat(post.getPostType()).isEqualTo(createPostRequest.getPostType());
    }

    @Test
    @DisplayName("게시글 생성 테스트 - 유저 조회 실패로 인한 실패")
    public void createPostFailByUserNotFoundTest() {
        // Given
        CreatePostRequest createPostRequest = new CreatePostRequest(
                "userid",
                1L,
                "posttitle",
                "postcontent",
                "postimage",
                Type.NORMAL
        );

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> postUseCase.createPost(createPostRequest));
    }

    @Test
    @DisplayName("게시글 생성 테스트 - 채널 조회 실패로 인한 실패")
    public void createPostFailByChannelNotFoundTest() {
        // Given
        CreatePostRequest createPostRequest = new CreatePostRequest(
                "userid",
                1L,
                "posttitle",
                "postcontent",
                "postimage",
                Type.NORMAL
        );

        User mockUser = User.builder()
                .userEmail("useremail@email.com")
                .build();

        when(userRepository.findByUserId(createPostRequest.getUserId()))
                .thenReturn(Optional.of(mockUser));

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> postUseCase.createPost(createPostRequest));
    }
}
