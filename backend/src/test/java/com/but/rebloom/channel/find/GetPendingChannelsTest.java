package com.but.rebloom.channel.find;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import com.but.rebloom.global.exception.NoAuthorityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPendingChannelsTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("대기 채널 조회 테스트 - 성공")
    public void getPendingChannelsSuccessTest() {
        // Given
        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder().build();
        List<Channel> mockChannels = List.of(mockChannel);

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByIsAcceptedFalse())
                .thenReturn(mockChannels);

        // When
        List<Channel> channels = channelUseCase.getPendingChannels();

        // Then
        assertThat(channels).isEqualTo(mockChannels);
    }

    @Test
    @DisplayName("대기 채널 조회 테스트 - 권한 부족으로 실패")
    public void getPendingChannelsFailByNoAuthorityTest() {
        // Given
        User mockUser = User.builder()
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> channelUseCase.getPendingChannels());
    }
}
