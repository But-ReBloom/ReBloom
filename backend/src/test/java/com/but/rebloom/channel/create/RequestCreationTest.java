package com.but.rebloom.channel.create;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.domain.channel.exception.AlreadyUsingChannelException;
import com.but.rebloom.domain.channel.exception.InsufficientPointException;
import com.but.rebloom.domain.channel.exception.InsufficientTierPointException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestCreationTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private HobbyRepository hobbyRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("채널 생성 요청 테스트 - 성공")
    public void requestCreationSuccessTest() {
        // Given
        CreateChannelRequest createChannelRequest = new CreateChannelRequest(
                "channelTitle",
                "channelIntro",
                "channelDescription",
                "userEmail",
                1L,
                2L,
                3L
        );

        User mockUser = User.builder()
                .userPoint(1000)
                .userTierPoint(1000)
                .build();

        Channel mockChannel = Channel.builder().build();

        Hobby mockHobby = Hobby.builder().build();

        when(userRepository.findByUserEmail(createChannelRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(channelRepository.existsByChannelTitle(createChannelRequest.getChannelTitle()))
                .thenReturn(false);
        when(userRepository.save(mockUser))
                .thenReturn(mockUser);
        when(hobbyRepository.findByHobbyId(anyLong()))
                .thenReturn(Optional.of(mockHobby));
        when(channelRepository.save(any(Channel.class)))
                .thenReturn(mockChannel);

        // When
        Channel channel = channelUseCase.requestCreation(createChannelRequest);

        // Then
        assertThat(channel).isEqualTo(mockChannel);
    }

    @Test
    @DisplayName("채널 생성 요청 테스트 - 유저 조회 실패로 인한 실패")
    public void requestCreationFailByUserNotFoundTest() {
        // Given
        CreateChannelRequest createChannelRequest = new CreateChannelRequest(
                "channelTitle",
                "channelIntro",
                "channelDescription",
                "userEmail",
                1L,
                2L,
                3L
        );

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> channelUseCase.requestCreation(createChannelRequest));
    }

    @Test
    @DisplayName("채널 생성 요청 테스트 - 이미 존재하는 채널명으로 인한 실패")
    public void requestCreationFailByAlreadyUsingChannelTest() {
        // Given
        CreateChannelRequest createChannelRequest = new CreateChannelRequest(
                "channelTitle",
                "channelIntro",
                "channelDescription",
                "userEmail",
                1L,
                2L,
                3L
        );

        User mockUser = User.builder()
                .userPoint(1000)
                .userTierPoint(1000)
                .build();

        when(userRepository.findByUserEmail(createChannelRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(channelRepository.existsByChannelTitle(createChannelRequest.getChannelTitle()))
                .thenReturn(true);

        // When & Then
        assertThrows(AlreadyUsingChannelException.class,
                () -> channelUseCase.requestCreation(createChannelRequest));
    }

    @Test
    @DisplayName("채널 생성 요청 테스트 - 티어 포인트 부족으로 인한 실패")
    public void requestCreationFailByInsufficientTierPointTest() {
        // Given
        CreateChannelRequest createChannelRequest = new CreateChannelRequest(
                "channelTitle",
                "channelIntro",
                "channelDescription",
                "userEmail",
                1L,
                2L,
                3L
        );

        User mockUser = User.builder()
                .userPoint(1000)
                .userTierPoint(0)
                .build();

        when(userRepository.findByUserEmail(createChannelRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(channelRepository.existsByChannelTitle(createChannelRequest.getChannelTitle()))
                .thenReturn(false);

        // When & Then
        assertThrows(InsufficientTierPointException.class,
                () -> channelUseCase.requestCreation(createChannelRequest));
    }

    @Test
    @DisplayName("채널 생성 요청 테스트 - 포인트 부족으로 인한 실패")
    public void requestCreationFailByInsufficientPointTest() {
        // Given
        CreateChannelRequest createChannelRequest = new CreateChannelRequest(
                "channelTitle",
                "channelIntro",
                "channelDescription",
                "userEmail",
                1L,
                2L,
                3L
        );

        User mockUser = User.builder()
                .userPoint(0)
                .userTierPoint(1000)
                .build();

        when(userRepository.findByUserEmail(createChannelRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(channelRepository.existsByChannelTitle(createChannelRequest.getChannelTitle()))
                .thenReturn(false);

        // When & Then
        assertThrows(InsufficientPointException.class,
                () -> channelUseCase.requestCreation(createChannelRequest));
    }

    @Test
    @DisplayName("채널 생성 요청 테스트 - 취미 조회 실패으로 인한 실패")
    public void requestCreationFailByHobbyNotFoundTest() {
        // Given
        CreateChannelRequest createChannelRequest = new CreateChannelRequest(
                "channelTitle",
                "channelIntro",
                "channelDescription",
                "userEmail",
                1L,
                2L,
                3L
        );

        User mockUser = User.builder()
                .userPoint(1000)
                .userTierPoint(1000)
                .build();

        when(userRepository.findByUserEmail(createChannelRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(channelRepository.existsByChannelTitle(createChannelRequest.getChannelTitle()))
                .thenReturn(false);

        // When & Then
        assertThrows(HobbyNotFoundException.class,
                () -> channelUseCase.requestCreation(createChannelRequest));
    }
}
