package com.but.rebloom.channel.userchannel.apply;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.ApplyMemberRequest;
import com.but.rebloom.domain.channel.exception.AlreadyUsingUserChannelException;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyMemberVerificationTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("채널 가입 신청 테스트 - 성공")
    public void applyMemberVerificationSuccessTest() {
        // Given
        ApplyMemberRequest applyMemberRequest = new ApplyMemberRequest(
                1L,
                "applyMessage"
        );

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .build();

        Channel mockChannel = Channel.builder()
                .channelId(1L)
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .user(mockUser)
                .channel(mockChannel)
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .applyMessage(applyMemberRequest.getApplyMessage())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(applyMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.existsByChannel_ChannelIdAndUser_UserEmail(applyMemberRequest.getChannelId(), mockUser.getUserEmail()))
                .thenReturn(false);
        when(userChannelRepository.save(any(UserChannel.class)))
                .thenReturn(mockUserChannel);

        // When
        UserChannel userChannel = verifyUserUseCase.applyMemberVerification(applyMemberRequest);

        // Then
        assertThat(userChannel).isEqualTo(mockUserChannel);
    }

    @Test
    @DisplayName("채널 가입 신청 테스트 - 채널 조회 실패로 실패")
    public void applyMemberVerificationFailByChannelNotFoundTest() {
        // Given
        ApplyMemberRequest applyMemberRequest = new ApplyMemberRequest(
                1L,
                "applyMessage"
        );

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> verifyUserUseCase.applyMemberVerification(applyMemberRequest));
    }

    @Test
    @DisplayName("채널 가입 신청 테스트 - 이미 지원한 채널로 실패")
    public void applyMemberVerificationFailByAlreadyAppliedTest() {
        // Given
        ApplyMemberRequest applyMemberRequest = new ApplyMemberRequest(
                1L,
                "applyMessage"
        );

        User mockUser = User.builder()
                .userEmail("test@test.com")
                .build();

        Channel mockChannel = Channel.builder()
                .channelId(1L)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(channelRepository.findByChannelIdAndChannelStatusAccepted(applyMemberRequest.getChannelId()))
                .thenReturn(Optional.of(mockChannel));
        when(userChannelRepository.existsByChannel_ChannelIdAndUser_UserEmail(applyMemberRequest.getChannelId(), mockUser.getUserEmail()))
                .thenReturn(true);

        // When & Then
        assertThrows(AlreadyUsingUserChannelException.class,
                () -> verifyUserUseCase.applyMemberVerification(applyMemberRequest));
    }
}
