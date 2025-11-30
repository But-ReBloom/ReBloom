package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.ApplyMemberRequest;
import com.but.rebloom.domain.channel.exception.AlreadyUsingUserChannelException;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.exception.UserChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
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

    // 채널 신청
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
}
