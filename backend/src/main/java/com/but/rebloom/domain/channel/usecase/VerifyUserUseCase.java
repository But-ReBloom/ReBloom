package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUserUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

//    // 채널 신청
//    public UserChannel applyMemberVerification() {
//
//    }
}
