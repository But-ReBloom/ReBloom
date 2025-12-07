package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.ApplyMemberRequest;
import com.but.rebloom.domain.channel.dto.request.ApproveMemberRequest;
import com.but.rebloom.domain.channel.dto.request.RejectMemberRequest;
import com.but.rebloom.domain.channel.exception.*;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.global.exception.NoAuthorityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerifyUserUseCase {
    // 디비 접근
    private final ChannelRepository channelRepository;
    private final UserChannelRepository userChannelRepository;
    // 함수 호출
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    // 채널의 신청 목록 확인
    public List<UserChannel> getApplyUsersByChannelId(Long channelId) {
        User user = findCurrentUserUseCase.getCurrentUser();
        Channel channel = channelRepository.findByChannelIdAndChannelStatusAccepted(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("채널 조회 실패"));

        if (!(user.getUserEmail().equals(channel.getUser().getUserEmail()) || user.getUserRole().equals(Role.ADMIN))) {
            throw new NoAuthorityException("조회할 권한이 없습니다.");
        }

        List<UserChannel> userChannels = userChannelRepository
                .findByChannel_ChannelIdAndUserChannelVerifyStatus(
                        channelId,
                        VerifyStatus.WAITING
                );

        if (userChannels.isEmpty()) {
            throw new UserChannelNotFoundException("유저 채널 조회 실패");
        }

        return userChannels;
    }

    // 유저의 신청 목록 확인
    public List<UserChannel> getApplyUsersByUserEmail(String userEmail) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!(currentUser.getUserEmail().equals(userEmail) || currentUser.getUserRole().equals(Role.ADMIN))) {
            throw new NoAuthorityException("조회할 권한이 없습니다.");
        }

        List<UserChannel> userChannels = userChannelRepository
                .findByUser_UserEmailAndUserChannelVerifyStatus(
                        userEmail,
                        VerifyStatus.WAITING
                );

        if (userChannels.isEmpty()) {
            throw new UserChannelNotFoundException("유저 채널 조회 실패");
        }

        return userChannels;
    }

    // 채널의 특정 유저 신청 목록 확인
    public UserChannel getApplyUsersByChannelIdAndUserEmail(Long channelId, String userEmail) {
        User user = findCurrentUserUseCase.getCurrentUser();
        Channel channel = channelRepository.findByChannelIdAndChannelStatusAccepted(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("채널 조회 실패"));

        if (!(user.getUserEmail().equals(channel.getUser().getUserEmail()) || user.getUserRole().equals(Role.ADMIN))) {
            throw new NoAuthorityException("조회할 권한이 없습니다.");
        }

        return userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(channelId, userEmail)
                .orElseThrow(() -> new UserChannelNotFoundException("유저 채널 조회 실패"));
    }

    // 유저의 특정 채널 신청 목록 확인
    public UserChannel getApplyUsersByUserEmailAndChannelId(String userEmail, Long channelId) {
        return userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(channelId, userEmail)
                .orElseThrow(() -> new UserChannelNotFoundException("유저 채널 조회 실패"));
    }

    // 가입 신청
    @Transactional
    public UserChannel applyMemberVerification(ApplyMemberRequest applyMemberRequest) {
        User user = findCurrentUserUseCase.getCurrentUser();

        Channel channel = channelRepository.findByChannelIdAndChannelStatusAccepted(applyMemberRequest.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("채널이 조회되지 않음"));

        if (userChannelRepository
                .existsByChannel_ChannelIdAndUser_UserEmail(channel.getChannelId(), user.getUserEmail())) {
            throw new AlreadyUsingUserChannelException("이미 지원한 채널");
        }

        UserChannel userChannel = UserChannel.builder()
                .user(user)
                .channel(channel)
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .applyMessage(applyMemberRequest.getApplyMessage())
                .build();

        return userChannelRepository.save(userChannel);
    }

    // 가입 거절
    @Transactional
    public UserChannel rejectMemberVerification(RejectMemberRequest rejectMemberRequest) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        Channel channel = channelRepository.findByChannelIdAndChannelStatusAccepted(rejectMemberRequest.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("채널이 조회되지 않음"));

        if (!(currentUser.getUserEmail().equals(channel.getUser().getUserEmail()) || currentUser.getUserRole().equals(Role.ADMIN))) {
            throw new NoAuthorityException("거절할 권한이 없습니다.");
        }

        UserChannel userChannel = userChannelRepository
                .findByChannel_ChannelIdAndUser_UserEmail(
                    channel.getChannelId(),
                    rejectMemberRequest.getUserEmail()
                )
                .orElseThrow(() -> new UserChannelNotFoundException("유저 채널 조회 실패"));

        if (!userChannel.getUserChannelVerifyStatus().equals(VerifyStatus.WAITING)) {
            throw new WrongStatusException("대기 상태가 아닙니다.");
        }

        userChannel.setUserChannelVerifyStatus(VerifyStatus.REJECTED);
        return userChannelRepository.save(userChannel);
    }

    // 가입 승인
    @Transactional
    public UserChannel approveMemberVerification(ApproveMemberRequest approveMemberRequest) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        Channel channel = channelRepository.findByChannelIdAndChannelStatusAccepted(approveMemberRequest.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("채널이 조회되지 않음"));

        if (!(currentUser.getUserEmail().equals(channel.getUser().getUserEmail()) || currentUser.getUserRole().equals(Role.ADMIN))) {
            throw new NoAuthorityException("승인할 권한이 없습니다.");
        }

        UserChannel userChannel = userChannelRepository
                .findByChannel_ChannelIdAndUser_UserEmail(
                        channel.getChannelId(),
                        approveMemberRequest.getUserEmail()
                )
                .orElseThrow(() -> new UserChannelNotFoundException("유저 채널 조회 실패"));

        if (!userChannel.getUserChannelVerifyStatus().equals(VerifyStatus.WAITING)) {
            throw new WrongStatusException("대기 상태가 아닙니다.");
        }

        userChannel.setUserChannelVerifyStatus(VerifyStatus.APPROVED);
        return userChannelRepository.save(userChannel);
    }
}
