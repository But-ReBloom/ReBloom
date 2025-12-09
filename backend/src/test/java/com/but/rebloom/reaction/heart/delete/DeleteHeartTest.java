package com.but.rebloom.reaction.heart.delete;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.reaction.domain.Heart;
import com.but.rebloom.domain.reaction.dto.request.DeleteHeartRequest;
import com.but.rebloom.domain.reaction.exception.HeartNotFoundException;
import com.but.rebloom.domain.reaction.repository.HeartRepository;
import com.but.rebloom.domain.reaction.usecase.HeartUseCase;
import com.but.rebloom.global.exception.NoAuthorityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteHeartTest {
    @Mock
    private HeartRepository heartRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private HeartUseCase heartUseCase;

    @Test
    @DisplayName("하트 취소 테스트 - 성공")
    public void deleteHeartSuccessTest() {
        // Given
        DeleteHeartRequest request = new DeleteHeartRequest(
                "userid",
                1L
        );

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Heart mockHeart = Heart.builder()
                .user(mockUser)
                .build();

        when(heartRepository.findByPost_PostIdAndUser_UserId(request.getPostId(), request.getUserId()))
                .thenReturn(Optional.of(mockHeart));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        heartUseCase.deleteHeart(request);
    }

    @Test
    @DisplayName("하트 취소 테스트 - 하트 조회 실패로 인한 실패")
    public void deleteHeartFailByHeartNotFoundTest() {
        // Given
        DeleteHeartRequest request = new DeleteHeartRequest(
                "userid",
                1L
        );

        // When & Then
        assertThrows(HeartNotFoundException.class,
                () -> heartUseCase.deleteHeart(request));
    }

    @Test
    @DisplayName("하트 취소 테스트 - 권한 부족으로 인한 실패")
    public void deleteHeartFailByNoAuthorityTest() {
        // Given
        DeleteHeartRequest request = new DeleteHeartRequest(
                "userid",
                1L
        );

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Heart mockHeart = Heart.builder()
                .user(
                        User.builder()
                                .userEmail("heart@email.com")
                                .build()
                )
                .build();

        when(heartRepository.findByPost_PostIdAndUser_UserId(request.getPostId(), request.getUserId()))
                .thenReturn(Optional.of(mockHeart));
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> heartUseCase.deleteHeart(request));
    }
}
