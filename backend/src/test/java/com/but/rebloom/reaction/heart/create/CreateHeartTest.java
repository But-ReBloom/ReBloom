package com.but.rebloom.reaction.heart.create;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.domain.reaction.domain.Heart;
import com.but.rebloom.domain.reaction.dto.request.CreateHeartRequest;
import com.but.rebloom.domain.reaction.exception.AlreadyUsingHeartException;
import com.but.rebloom.domain.reaction.repository.HeartRepository;
import com.but.rebloom.domain.reaction.usecase.HeartUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CreateHeartTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private HeartRepository heartRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private HeartUseCase heartUseCase;

    @Test
    @DisplayName("하트 생성 테스트 - 성공")
    public void createHeartSuccessTest() {
        // Given
        CreateHeartRequest request = new CreateHeartRequest(
                "userid",
                1L
        );

        User mockUser = User.builder()
                .userId(request.getUserId())
                .userEmail("useremail@email.com")
                .build();

        Post mockPost = Post.builder()
                .postId(request.getPostId())
                .user(mockUser)
                .build();

        Heart mockHeart = Heart.builder()
                .user(mockUser)
                .post(mockPost)
                .build();

        when(userRepository.findByUserId(request.getUserId()))
                .thenReturn(Optional.of(mockUser));
        when(postRepository.findByPostId(request.getPostId()))
                .thenReturn(Optional.of(mockPost));
        when(heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId()))
                .thenReturn(false);
        when(heartRepository.save(any(Heart.class)))
                .thenReturn(mockHeart);

        // When
        Heart heart = heartUseCase.createHeart(request);

        // Then
        assertThat(heart).isEqualTo(mockHeart);
    }

    @Test
    @DisplayName("하트 생성 테스트 - 유저 조회 실패로 인한 실패")
    public void createHeartFailByUserNotFoundTest() {
        // Given
        CreateHeartRequest request = new CreateHeartRequest(
                "userid",
                1L
        );

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> heartUseCase.createHeart(request));
    }

    @Test
    @DisplayName("하트 생성 테스트 - 게시글 조회 실패로 인한 실패")
    public void createHeartFailByPostNotFoundTest() {
        // Given
        CreateHeartRequest request = new CreateHeartRequest(
                "userid",
                1L
        );

        User mockUser = User.builder()
                .userId(request.getUserId())
                .userEmail("useremail@email.com")
                .build();

        when(userRepository.findByUserId(request.getUserId()))
                .thenReturn(Optional.of(mockUser));

        // When & Then
        assertThrows(PostNotFoundException.class,
                () -> heartUseCase.createHeart(request));
    }

    @Test
    @DisplayName("하트 생성 테스트 - 이미 존재하는 하트로 인한 실패")
    public void createHeartFailByPostAlreadyExistHeartTest() {
        // Given
        CreateHeartRequest request = new CreateHeartRequest(
                "userid",
                1L
        );

        User mockUser = User.builder()
                .userId(request.getUserId())
                .userEmail("useremail@email.com")
                .build();

        Post mockPost = Post.builder()
                .postId(request.getPostId())
                .user(mockUser)
                .build();

        when(userRepository.findByUserId(request.getUserId()))
                .thenReturn(Optional.of(mockUser));
        when(postRepository.findByPostId(request.getPostId()))
                .thenReturn(Optional.of(mockPost));
        when(heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId()))
                .thenReturn(true);

        // When & Then
        assertThrows(AlreadyUsingHeartException.class,
                () -> heartUseCase.createHeart(request));
    }
}
