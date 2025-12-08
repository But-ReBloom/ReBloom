package com.but.rebloom.channel.admin;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.ChannelStatus;
import com.but.rebloom.domain.channel.exception.AlreadyProcessedChannelException;
import com.but.rebloom.domain.channel.exception.AlreadyRejectedChannelException;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
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
public class RejectChannelTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("채널 거절 테스트 - 성공")
    public void rejectChannelSuccessTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .channelStatus(ChannelStatus.PENDING)
                .user(
                    User.builder()
                            .userRole(Role.USER)
                            .userTierPoint(1)
                            .userPoint(1)
                            .build()
                )
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(mockChannel));
        when(userRepository.save(any(User.class)))
                .thenReturn(mockUser);
        when(channelRepository.save(any(Channel.class)))
                .thenReturn(mockChannel);

        // When
        channelUseCase.rejectChannel(channelId);

        // Then
        assertThat(mockChannel.getChannelStatus()).isEqualTo(ChannelStatus.REJECTED);
    }

    @Test
    @DisplayName("채널 거절 테스트 - 권한 부족으로 실패")
    public void rejectChannelFailByNoAuthorityTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.USER)
                .build();
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> channelUseCase.rejectChannel(channelId));
    }

    @Test
    @DisplayName("채널 거절 테스트 - 채널 조회 실패로 실패")
    public void rejectChannelFailByChannelNotFoundTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> channelUseCase.rejectChannel(channelId));
    }

    @Test
    @DisplayName("채널 거절 테스트 - 이미 승인된 채널로 실패")
    public void rejectChannelFailByAlreadyProcessedChannelTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .channelStatus(ChannelStatus.ACCEPTED)
                .user(
                        User.builder()
                                .userRole(Role.USER)
                                .userTierPoint(1)
                                .userPoint(1)
                                .build()
                )
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(AlreadyProcessedChannelException.class,
                () -> channelUseCase.rejectChannel(channelId));
    }

    @Test
    @DisplayName("채널 거절 테스트 - 이미 거절된 채널로 실패")
    public void rejectChannelFailByAlreadyRejectedChannelTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .channelStatus(ChannelStatus.REJECTED)
                .user(
                        User.builder()
                                .userRole(Role.USER)
                                .userTierPoint(1)
                                .userPoint(1)
                                .build()
                )
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(AlreadyRejectedChannelException.class,
                () -> channelUseCase.rejectChannel(channelId));
    }
}
