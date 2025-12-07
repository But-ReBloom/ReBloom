package com.but.rebloom.channel.admin;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @DisplayName("채널 거절 테스트 성공")
    public void rejectChannelSuccessTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder()
                .isAccepted(false)
                .build();
    }
}
