package com.but.rebloom.channel.userchannel.admin;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.ApproveMemberRequest;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.exception.UserChannelNotFoundException;
import com.but.rebloom.domain.channel.exception.WrongStatusException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApproveMemberVerificationTest {
    @Mock
    private UserChannelRepository userChannelRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("채널 가입 승인 테스트 - 성공")
    public void approveMemberVerificationSuccessTest() {
        // Given
        ApproveMemberRequest approveMemberRequest = new ApproveMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(approveMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .channelId(approveMemberRequest.getChannelId())
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .user(mockUser)
                .channel(mockChannel)
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(approveMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(approveMemberRequest.getChannelId(), approveMemberRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUserChannel));
        when(userChannelRepository.save(any(UserChannel.class)))
                .thenReturn(mockUserChannel);

        // When
        UserChannel userChannel = verifyUserUseCase.approveMemberVerification(approveMemberRequest);

        // Then
        assertThat(userChannel.getUserChannelVerifyStatus()).isEqualTo(VerifyStatus.APPROVED);
    }

    @Test
    @DisplayName("채널 가입 승인 테스트 - 채널 조회 실패로 실패")
    public void approveMemberVerificationFailByChannelNotFoundTest() {
        // Given
        ApproveMemberRequest approveMemberRequest = new ApproveMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(approveMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> verifyUserUseCase.approveMemberVerification(approveMemberRequest));
    }

    @Test
    @DisplayName("채널 가입 승인 테스트 - 권한 부족으로 실패")
    public void approveMemberVerificationFailByNoAuthorityTest() {
        // Given
        ApproveMemberRequest approveMemberRequest = new ApproveMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(approveMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(
                        User.builder()
                                .userEmail("channel@email.com")
                                .build()
                )
                .channelId(approveMemberRequest.getChannelId())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(approveMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> verifyUserUseCase.approveMemberVerification(approveMemberRequest));
    }

    @Test
    @DisplayName("채널 가입 승인 테스트 - 유저 채널 조회 실패로 실패")
    public void approveMemberVerificationFailByUserChannelNotFoundTest() {
        // Given
        ApproveMemberRequest approveMemberRequest = new ApproveMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(approveMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .channelId(approveMemberRequest.getChannelId())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(approveMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(UserChannelNotFoundException.class,
                () -> verifyUserUseCase.approveMemberVerification(approveMemberRequest));
    }

    @Test
    @DisplayName("채널 가입 승인 테스트 - 대기 상태가 아닌 유저 채널로 인한 실패")
    public void approveMemberVerificationFailByNotWaitingStateTest() {
        // Given
        ApproveMemberRequest approveMemberRequest = new ApproveMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(approveMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .channelId(approveMemberRequest.getChannelId())
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .user(mockUser)
                .channel(mockChannel)
                .userChannelVerifyStatus(VerifyStatus.APPROVED)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(approveMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(approveMemberRequest.getChannelId(), approveMemberRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUserChannel));

        // When & Then
        assertThrows(WrongStatusException.class,
                () -> verifyUserUseCase.approveMemberVerification(approveMemberRequest));
    }
}
