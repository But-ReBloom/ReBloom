package com.but.rebloom.userchannel.find;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.channel.usecase.VerifyUserUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetApplyUsersByChannelIdTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("채널 지원 유저 확인 테스트 - 성공")
    public void getApplyUsersByChannelIdSuccessTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .userRole(Role.ADMIN)
                .build();

        Channel mockChannel = Channel.builder().channelId(channelId)
                .user(mockUser)
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .build();
        List<UserChannel> mockUserChannels = List.of(mockUserChannel);

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(channelId))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.findByChannel_ChannelIdAndUserChannelVerifyStatus(channelId, VerifyStatus.WAITING))
                .thenReturn(mockUserChannels);

        // When
        List<UserChannel> userChannels = verifyUserUseCase.getApplyUsersByChannelId(channelId);

        // Then
        assertThat(userChannels.size()).isEqualTo(mockUserChannels.size());
    }

    @Test
    @DisplayName("채널 지원 유저 확인 테스트 - 채널 조회 실패로 실패")
    public void getApplyUsersByChannelIdFailChannelNotFoundTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .userRole(Role.ADMIN)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When
        assertThrows(ChannelNotFoundException.class,
                () -> verifyUserUseCase.getApplyUsersByChannelId(channelId));
    }
}
