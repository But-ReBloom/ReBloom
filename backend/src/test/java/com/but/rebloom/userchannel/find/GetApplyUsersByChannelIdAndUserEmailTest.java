package com.but.rebloom.userchannel.find;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetApplyUsersByChannelIdAndUserEmailTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("유저 신청 목록 조회 테스트 - 성공")
    public void getApplyUsersByChannelIdAndUserEmailSuccessTest() {
        // Given
        Long channelId = 1L;
        String userEmail = "test@test.com";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .user(mockUser)
                .channel(mockChannel)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(channelId))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(channelId, userEmail))
                .thenReturn(Optional.of(mockUserChannel));

        // When
        UserChannel userChannel = verifyUserUseCase.getApplyUsersByChannelIdAndUserEmail(channelId, userEmail);

        // Then
        assertThat(userChannel).isEqualTo(mockUserChannel);
    }

    @Test
    @DisplayName("유저 신청 목록 조회 테스트 - 채널 조회 실패로 실패")
    public void getApplyUsersByChannelIdAndUserEmailFailByChannelNotFoundTest() {
        // Given
        Long channelId = 1L;
        String userEmail = "test@test.com";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> verifyUserUseCase.getApplyUsersByChannelIdAndUserEmail(channelId, userEmail));
    }

    @Test
    @DisplayName("유저 신청 목록 조회 테스트 - 권한 부족으로 실패")
    public void getApplyUsersByChannelIdAndUserEmailFailByNoAuthorityTest() {
        // Given
        Long channelId = 1L;
        String userEmail = "test@test.com";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(
                        User.builder()
                                .userEmail("user@test.com")
                                .build()
                )
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(channelId))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> verifyUserUseCase.getApplyUsersByChannelIdAndUserEmail(channelId, userEmail));
    }
}
