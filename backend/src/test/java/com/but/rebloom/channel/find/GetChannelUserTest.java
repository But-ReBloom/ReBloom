package com.but.rebloom.channel.find;

import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetChannelUserTest {
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("채널 유저 조회 테스트 - 성공")
    public void getChannelUserSuccessTest() {
        // Given
        Long channelId = 1L;

        UserChannel mockUserChannel = UserChannel.builder().build();
        List<UserChannel> mockUserChannels = List.of(mockUserChannel);

        when(userChannelRepository.findByChannel_ChannelIdAndUserChannelVerifyStatus(
                channelId,
                VerifyStatus.APPROVED
                ))
                .thenReturn(mockUserChannels);

        // When
        List<UserChannel> userChannels = channelUseCase.getChannelUser(channelId);

        // Then
        assertThat(userChannels).size().isEqualTo(mockUserChannels.size());
    }
}
