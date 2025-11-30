package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.ApplyMemberRequest;
import com.but.rebloom.domain.channel.dto.request.RejectMemberRequest;
import com.but.rebloom.domain.channel.exception.*;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUserUseCase {
    // 디비 접근
    private final ChannelRepository channelRepository;
    private final UserChannelRepository userChannelRepository;
    // 함수 호출
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    // 가입 신청
    @Transactional
    public UserChannel applyMemberVerification(ApplyMemberRequest applyMemberRequest) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        Long channelId = channelRepository.findByChannelId(applyMemberRequest.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("채널이 조회되지 않음")).getChannelId();

        if (userChannelRepository
                .existsByChannelIdAndUserEmail(channelId, userEmail)) {
            throw new AlreadyUsingUserChannelException("이미 지원한 채널");
        }

        return UserChannel.builder()
                .userEmail(userEmail)
                .channelId(channelId)
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .applyMessage(applyMemberRequest.getApplyMessage())
                .build();
    }

    // 가입 거절
    @Transactional
    public UserChannel rejectMemberVerification(RejectMemberRequest rejectMemberRequest) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        Channel channel = channelRepository.findByChannelId(rejectMemberRequest.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("채널이 조회되지 않음"));

        if (!userEmail.equals(channel.getUser().getUserEmail())) {
            throw new NoAuthorityException("거절할 권한이 없습니다.");
        }

        UserChannel userChannel = userChannelRepository
                .findByChannelIdAndUserEmail(
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
}
