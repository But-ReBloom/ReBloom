package com.but.rebloom.userchannel.find;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.exception.UserChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.channel.usecase.VerifyUserUseCase;
import com.but.rebloom.global.exception.NoAuthorityException;
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
    public void getApplyUsersByChannelIdFailByChannelNotFoundTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .userRole(Role.ADMIN)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> verifyUserUseCase.getApplyUsersByChannelId(channelId));
    }

    @Test
    @DisplayName("채널 지원 유저 확인 테스트 - 권한 부족으로 실패")
    public void getApplyUsersByChannelIdFailByNoAuthorityTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder().channelId(channelId)
                .user(
                        User.builder()
                                .userEmail("useremail@email.com")
                                .build()
                )
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> verifyUserUseCase.getApplyUsersByChannelId(channelId));
    }

    @Test
    @DisplayName("채널 지원 유저 확인 테스트 - 유저 채널 조회 실패로 실패")
    public void getApplyUsersByChannelIdFailByUserChannelNotFoundTest() {
        // Given
        Long channelId = 1L;

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder().channelId(channelId)
                .user(mockUser)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(UserChannelNotFoundException.class,
                () -> verifyUserUseCase.getApplyUsersByChannelId(channelId));
    }
}
