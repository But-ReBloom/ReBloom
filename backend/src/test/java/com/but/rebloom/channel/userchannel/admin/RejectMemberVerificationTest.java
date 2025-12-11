package com.but.rebloom.channel.userchannel.admin;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.RejectMemberRequest;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class RejectMemberVerificationTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private UserChannelRepository userChannelRepository;
    @Mock
    private ChannelRepository channelRepository;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("채널 거절 테스트 - 성공")
    public void rejectMemberVerificationSuccessTest() {
        // Given
        RejectMemberRequest rejectMemberRequest = new RejectMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail("useremail@email.com")
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .channelId(rejectMemberRequest.getChannelId())
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .user(mockUser)
                .channel(mockChannel)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(rejectMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(rejectMemberRequest.getChannelId(), mockUser.getUserEmail()))
                .thenReturn(Optional.of(mockUserChannel));
        when(userChannelRepository.save(any(UserChannel.class)))
                .thenReturn(mockUserChannel);

        // When
        UserChannel userChannel = verifyUserUseCase.rejectMemberVerification(rejectMemberRequest);

        // Then
        assertThat(userChannel.getUserChannelVerifyStatus()).isEqualTo(VerifyStatus.REJECTED);
    }

    @Test
    @DisplayName("채널 거절 테스트 - 채널 조회 실패로 실패")
    public void rejectMemberVerificationFailByChannelNotFoundTest() {
        // Given
        RejectMemberRequest rejectMemberRequest = new RejectMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(rejectMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> verifyUserUseCase.rejectMemberVerification(rejectMemberRequest));
    }

    @Test
    @DisplayName("채널 거절 테스트 - 권한 부족으로 실패")
    public void rejectMemberVerificationFailByNoAuthorityTest() {
        // Given
        RejectMemberRequest rejectMemberRequest = new RejectMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(rejectMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(
                        User.builder()
                                .userEmail("channel@email.com")
                                .userRole(Role.USER)
                                .build()
                )
                .channelId(rejectMemberRequest.getChannelId())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(rejectMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> verifyUserUseCase.rejectMemberVerification(rejectMemberRequest));
    }

    @Test
    @DisplayName("채널 거절 테스트 - 유저 채널 조회 실패로 실패")
        public void rejectMemberVerificationFailByUserChannelNotFoundTest() {
        // Given
        RejectMemberRequest rejectMemberRequest = new RejectMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail(rejectMemberRequest.getUserEmail())
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .channelId(rejectMemberRequest.getChannelId())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(rejectMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));

        // When & Then
        assertThrows(UserChannelNotFoundException.class,
                () -> verifyUserUseCase.rejectMemberVerification(rejectMemberRequest));
    }

    @Test
    @DisplayName("채널 거절 테스트 - 대기중이 아닌 유저 채널로 실패")
    public void rejectMemberVerificationFailByNotWaitingStateTest() {
        // Given
        RejectMemberRequest rejectMemberRequest = new RejectMemberRequest(
                "useremail@email.com",
                1L
        );

        User mockUser = User.builder()
                .userEmail("useremail@email.com")
                .userRole(Role.USER)
                .build();

        Channel mockChannel = Channel.builder()
                .user(mockUser)
                .channelId(rejectMemberRequest.getChannelId())
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .userChannelVerifyStatus(VerifyStatus.APPROVED)
                .user(mockUser)
                .channel(mockChannel)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(rejectMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(rejectMemberRequest.getChannelId(), mockUser.getUserEmail()))
                .thenReturn(Optional.of(mockUserChannel));

        // When & Then
        assertThrows(WrongStatusException.class,
                () -> verifyUserUseCase.rejectMemberVerification(rejectMemberRequest));
    }
}
