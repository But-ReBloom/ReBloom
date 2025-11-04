package com.but.rebloom.channel.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.exception.UserNotFoundException;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.channel.domain.Channel;
import com.but.rebloom.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.channel.exception.AlreadyProcessedChannelException;
import com.but.rebloom.channel.exception.ChannelNotFoundException;
import com.but.rebloom.channel.exception.InsufficientPointException;
import com.but.rebloom.channel.exception.InsufficientTeirPointException;
import com.but.rebloom.channel.repository.ChannelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelUseCase {
    // 디비 접근
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    private static final int requiredPoints = 500; // 채널 생성에 필요한 포인트 임시
    private static final int requiredTierPoint = 500; // 채널 생성할 때 요구되는 최소 티어 임시

    // 채널 생성 요청
    @Transactional
    public Channel requestCreation(CreateChannelRequest request) {
        // 유저 조회
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        // 티어 포인트 확인
        if (user.getUserTierPoint() < requiredTierPoint) {
            throw new InsufficientTeirPointException("Tier point not enough");
        }
        user.setUserTierPoint(user.getUserTierPoint() - requiredTierPoint);
        //포인트 확인
        if (user.getUserPoint() < requiredPoints) {
            throw new InsufficientPointException("Point not enough");
        }
        user.setUserPoint(user.getUserPoint() - requiredPoints);
        userRepository.save(user);

        // 채널 생성(승인 대기)
        Channel channel = Channel.builder()
                .user(user)
                .channelTitle(request.getChannelTitle())
                .channelIntro(request.getChannelIntro())
                .channelDescription(request.getChannelDescription())
                .isAccepted(false)
                .build();
        return channelRepository.save(channel);
    }

    // 채널 검색
    public List<Channel> findChannel(String keyword) {
        List<Channel> channels = channelRepository
                .findByChannelTitleContainingOrChannelDescriptionContaining(
                        keyword,
                        keyword
                );

        return channels.stream()
                .filter(Channel::getIsAccepted)
                .collect(Collectors.toList());
    }

    // 승인된 채널 목록
    public List<Channel> getApprovedChannels() {
        return channelRepository.findByIsAcceptedTrue();
    }

    // 승인대기 채널 목록
    public List<Channel> getPendingChannels() {
        return channelRepository.findByIsAcceptedFalse();
    }

    // 채널 승인
    @Transactional
    public Channel approveChannel(Long channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        if (channel.getIsAccepted()) {
            throw new AlreadyProcessedChannelException("This channel is already accepted");
        }

        channel.setIsAccepted(true);
        return channelRepository.save(channel);
    }

    // 채널 거절
    @Transactional
    public void rejectChannel(Long channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        if (channel.getIsAccepted()) {
            throw new AlreadyProcessedChannelException("This channel is already rejected");
        }

        User user = channel.getUser();
        // 티어포인트 환불
        user.setUserTierPoint(user.getUserTierPoint() + requiredTierPoint);
        // 포인트 환불
        user.setUserPoint(user.getUserPoint() + requiredPoints);
        userRepository.save(user);

        // 삭제
        channelRepository.delete(channel);
    }

    // 특정 채널 조회
    public Channel getChannel(Long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));
    }
}