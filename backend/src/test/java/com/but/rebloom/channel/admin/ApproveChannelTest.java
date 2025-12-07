package com.but.rebloom.channel.admin;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.exception.AlreadyProcessedChannelException;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApproveChannelTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private HobbyRepository hobbyRepository;
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("채널 승인 테스트 - 성공")
    public void approveChannelSuccessTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .isAccepted(false)
                .channelLinkedHobby1(
                        Hobby.builder()
                        .hobbyId(1L)
                        .build()
                )
                .channelLinkedHobby2(
                        Hobby.builder()
                        .hobbyId(1L)
                        .build()
                )
                .channelLinkedHobby3(
                        Hobby.builder()
                        .hobbyId(1L)
                        .build()
                )
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .channel(mockChannel)
                .user(mockUser)
                .userChannelVerifyStatus(VerifyStatus.APPROVED)
                .applyMessage("")
                .build();

        Hobby mockHobby = Hobby.builder().build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(mockChannel));
        when(hobbyRepository.findByHobbyId(anyLong()))
                .thenReturn(Optional.of(mockHobby));
        when(userChannelRepository.save(any()))
                .thenReturn(mockUserChannel);
        when(channelRepository.save(any()))
                .thenReturn(mockChannel);

        // When
        Channel channel = channelUseCase.approveChannel(channelId);

        // Then
        assertThat(channel.getIsAccepted()).isTrue();
    }

    @Test
    @DisplayName("채널 승인 테스트 - 권한 부족으로 실패")
    public void approveChannelFailByNoAuthorityTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> channelUseCase.approveChannel(channelId));
    }

    @Test
    @DisplayName("채널 승인 테스트 - 채널 조회 실패로 실패")
    public void approveChannelFailByChannelNotFoundTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> channelUseCase.approveChannel(channelId));
    }

    @Test
    @DisplayName("채널 승인 테스트 - 이미 승인된 채널로 실패")
    public void approveChannelFailByAlreadyProcessedChannelTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .isAccepted(true)
                .channelLinkedHobby1(Hobby.builder()
                        .hobbyId(0L)
                        .build())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(AlreadyProcessedChannelException.class,
                () -> channelUseCase.approveChannel(channelId));
    }

    @Test
    @DisplayName("채널 승인 테스트 - 취미 조회 실패로 실패")
    public void approveChannelFailByHobbyNotFoundTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .isAccepted(false)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findById(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(HobbyNotFoundException.class,
                () -> channelUseCase.approveChannel(channelId));
    }
}
