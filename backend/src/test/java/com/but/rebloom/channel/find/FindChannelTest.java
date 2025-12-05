package com.but.rebloom.channel.find;

import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.dto.request.SearchChannelRequest;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FindChannelTest {
    @Mock
    private ChannelRepository channelRepository;
    @InjectMocks
    private ChannelUseCase channelUseCase;

    @Test
    @DisplayName("키워드 조회 테스트 - 성공")
    public void findChannelSuccessTest() {
        // Given
        SearchChannelRequest request = new SearchChannelRequest(
                "keyword"
        );

        Channel mockChannel = Channel.builder().build();
        List<Channel> mockChannels = List.of(mockChannel);

        when(channelRepository.searchByKeyword(request.getKeyword()))
                .thenReturn(mockChannels);

        // When
        List<Channel> channels = channelUseCase.findChannel(request);

        // Then
        assertThat(channels).isEqualTo(mockChannels);
    }
}
