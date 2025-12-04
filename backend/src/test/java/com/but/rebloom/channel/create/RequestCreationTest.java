package com.but.rebloom.channel.create;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        when(channelRepository.save(any()))
                .thenReturn(mockChannel);

        // When
        Channel channel = channelUseCase.requestCreation(createChannelRequest);

        // Then
        assertThat(channel).isEqualTo(mockChannel);
    }
}
