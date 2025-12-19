package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.ChannelStatus;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.domain.channel.dto.request.SearchChannelRequest;
import com.but.rebloom.domain.channel.exception.*;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.global.exception.NoAuthorityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelUseCase {
    // 디비 접근
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final UserChannelRepository userChannelRepository;
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    private static final int requiredPoints = 500; // 채널 생성에 필요한 포인트 임시
    private static final int requiredTierPoint = 500; // 채널 생성할 때 요구되는 최소 티어 임시

    // 채널 생성 요청
    @Transactional
    public Channel requestCreation(CreateChannelRequest request) {
        // 유저 조회
        User user = findCurrentUserUseCase.getCurrentUser();

        if (channelRepository.existsByChannelTitle(request.getChannelTitle())) {
            throw new AlreadyUsingChannelException("이미 존재하는 채널 이름");
        }

        // 티어 포인트 확인
        if (user.getUserTierPoint() < requiredTierPoint) {
            throw new InsufficientTierPointException("티어 포인트가 부족함");
        }
        user.setUserTierPoint(user.getUserTierPoint() - requiredTierPoint);
        //포인트 확인
        if (user.getUserPoint() < requiredPoints) {
            throw new InsufficientPointException("포인트가 부족함");
        }
        user.setUserPoint(user.getUserPoint() - requiredPoints);
        userRepository.save(user);

        Hobby hobby1 = hobbyRepository.findByHobbyId(request.getChannelLinkedHobby1())
                .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));

        Hobby hobby2 = null;
        Hobby hobby3 = null;

        if (request.getChannelLinkedHobby2() != null) {
            hobby2 = hobbyRepository.findByHobbyId(request.getChannelLinkedHobby2())
                    .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
        } else {
            hobby2 = hobby1;
        }
        if (request.getChannelLinkedHobby3() != null) {
            hobby3 = hobbyRepository.findByHobbyId(request.getChannelLinkedHobby3())
                    .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
        } else {
            hobby3 = hobby1;
        }

        // 채널 생성(승인 대기)
        Channel channel = Channel.builder()
                .user(user)
                .channelTitle(request.getChannelTitle())
                .channelIntro(request.getChannelIntro())
                .channelDescription(request.getChannelDescription())
                .channelStatus(ChannelStatus.ACCEPTED)
                .channelLinkedHobby1(hobby1)
                .channelLinkedHobby2(hobby2)
                .channelLinkedHobby3(hobby3)
                .build();

        return channelRepository.save(channel);
    }

    // 채널 검색
    public List<Channel> findChannel(SearchChannelRequest request) {
        List<Channel> channels = channelRepository
                .searchByKeyword(request.getKeyword());

        if (channels.isEmpty()) {
            throw new ChannelNotFoundException("채널 조회 실패");
        }

        return channels;
    }

    // 승인된 채널 목록
    public List<Channel> getApprovedChannels() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

//        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
//            throw new NoAuthorityException("조회할 권한이 없습니다.");
//        }

        List<Channel> channels = channelRepository.findByChannelStatusAccepted();

        if (channels.isEmpty()) {
            throw new ChannelNotFoundException("채널 조회 실패");
        }

        return channels;
    }

    // 승인대기 채널 목록
    public List<Channel> getPendingChannels() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("조회할 권한이 없습니다.");
        }

        List<Channel> channels = channelRepository.findByChannelStatusPending();

        if (channels.isEmpty()) {
            throw new ChannelNotFoundException("채널 조회 실패");
        }

        return channels;
    }

    // 채널 승인
    @Transactional
    public Channel approveChannel(Long channelId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("승인할 권한이 없습니다.");
        }

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("채널 조회 실패"));

        if (channel.getChannelStatus().equals(ChannelStatus.ACCEPTED)) {
            throw new AlreadyProcessedChannelException("이미 승인된 채널");
        }

        hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));

        if (channel.getChannelLinkedHobby2() != null) {
            hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby2().getHobbyId())
                    .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
        } if (channel.getChannelLinkedHobby3() != null) {
            hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby3().getHobbyId())
                    .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
        }

        channel.setChannelStatus(ChannelStatus.ACCEPTED);

        UserChannel userChannel = UserChannel.builder()
                .channel(channel)
                .user(channel.getUser())
                .userChannelVerifyStatus(VerifyStatus.APPROVED)
                .applyMessage("")
                .build();

        userChannelRepository.save(userChannel);

        return channelRepository.save(channel);
    }

    // 채널 거절
    @Transactional
    public void rejectChannel(Long channelId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("거절할 권한이 없습니다.");
        }

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("채널 조회 실패"));

        if (channel.getChannelStatus().equals(ChannelStatus.ACCEPTED)) {
            throw new AlreadyProcessedChannelException("이미 승인된 채널");
        }
        if (channel.getChannelStatus().equals(ChannelStatus.REJECTED)) {
            throw new AlreadyRejectedChannelException("이미 거절된 채널");
        }

        User user = channel.getUser();
        // 티어포인트 환불
        user.setUserTierPoint(user.getUserTierPoint() + requiredTierPoint);
        // 포인트 환불
        user.setUserPoint(user.getUserPoint() + requiredPoints);
        userRepository.save(user);

        // 삭제 처리
        channel.setChannelStatus(ChannelStatus.REJECTED);
        channelRepository.save(channel);
    }

    // 특정 채널 조회
    public Channel getChannel(Long channelId) {
        return channelRepository.findByChannelIdAndChannelStatusAccepted(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("채널 조회 실패"));
    }

    // 특정 채널의 유저 목록 조회
    public List<UserChannel> getChannelUser(Long channelId) {
        List<UserChannel> userChannels = userChannelRepository
                .findByChannel_ChannelIdAndUserChannelVerifyStatus(
                    channelId,
                    VerifyStatus.APPROVED
                );

        if (userChannels.isEmpty()) {
            throw new UserChannelNotFoundException("유저 채널 조회 실패");
        }

        return userChannels;
    }
}