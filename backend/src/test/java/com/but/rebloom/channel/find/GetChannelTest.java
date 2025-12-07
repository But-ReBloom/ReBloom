package com.but.rebloom.channel.find;

import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetChannelTest {
    @Mock
    private ChannelRepository channelRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("채널 조회 테스트 - 성공")
    public void getChannelSuccessTest() {
        // Given
        Long channelId = 1L;

        Channel mockChannel = Channel.builder().build();

        when(channelRepository.findByChannelIdAndChannelStatusAccepted(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When
        Channel channel = channelUseCase.getChannel(channelId);

        // Then
        assertThat(channel).isEqualTo(mockChannel);
    }

    @Test
    @DisplayName("채널 조회 테스트 - 채널 조회 실패로 인한 실패")
    public void getChannelFailByChannelNotFoundTest() {
        // Given
        Long channelId = 1L;

        // When & Then
        Assertions.assertThrows(ChannelNotFoundException.class,
                () -> channelUseCase.getChannel(channelId));
    }
}
