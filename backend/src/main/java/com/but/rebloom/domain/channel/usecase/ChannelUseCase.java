package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.domain.channel.dto.request.SearchChannelRequest;
import com.but.rebloom.domain.channel.exception.*;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
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
        User user = userRepository.findByUserEmail(request.getUserEmail())
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

        Hobby hobby1 = hobbyRepository.findByHobbyId(request.getChannelLinkedHobby1())
                .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));

        Hobby hobby2 = null;
        Hobby hobby3 = null;

        if (request.getChannelLinkedHobby2() != null) {
            hobby2 = hobbyRepository.findByHobbyId(request.getChannelLinkedHobby2())
                    .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
        } if (request.getChannelLinkedHobby3() != null) {
            hobby3 = hobbyRepository.findByHobbyId(request.getChannelLinkedHobby3())
                    .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
        }

        // 채널 생성(승인 대기)
        Channel channel = Channel.builder()
                .user(user)
                .channelTitle(request.getChannelTitle())
                .channelIntro(request.getChannelIntro())
                .channelDescription(request.getChannelDescription())
                .isAccepted(false)
                .channelLinkedHobby1(hobby1)
                .channelLinkedHobby2(hobby2)
                .channelLinkedHobby3(hobby3)
                .build();

        return channelRepository.save(channel);
    }

    // 채널 검색
    public List<Channel> findChannel(SearchChannelRequest request) {
        return channelRepository
                .searchByKeyword(request.getKeyword());
    }

    // 승인된 채널 목록
    public List<Channel> getApprovedChannels() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("조회할 권한이 없습니다.");
        }

        return channelRepository.findByIsAcceptedTrue();
    }

    // 승인대기 채널 목록
    public List<Channel> getPendingChannels() {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("조회할 권한이 없습니다.");
        }

        return channelRepository.findByIsAcceptedFalse();
    }

    // 채널 승인
    @Transactional
    public Channel approveChannel(Long channelId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("승인할 권한이 없습니다.");
        }

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        if (channel.getIsAccepted()) {
            throw new AlreadyProcessedChannelException("This channel is already accepted");
        }

        hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));

        if (channel.getChannelLinkedHobby2() != null) {
            hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby2().getHobbyId())
                    .orElseThrow(() -> new ActivityNotFoundException("활동이 조회되지 않음"));
        } if (channel.getChannelLinkedHobby3() != null) {
            hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby3().getHobbyId())
                    .orElseThrow(() -> new ActivityNotFoundException("활동이 조회되지 않음"));
        }

        channel.setIsAccepted(true);

        UserChannel userChannel = UserChannel.builder()
                .channelId(channelId)
                .userEmail(channel.getUser().getUserEmail())
                .userChannelVerifyStatus(VerifyStatus.APPROVED)
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
        return channelRepository.findByChannelIdAndIsAcceptedTrue(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));
    }

    // 특정 채널의 유저 목록 조회
    public List<UserChannel> getChannelUser(Long channelId) {
        return userChannelRepository.findByChannelIdAndUserChannelVerifyStatus(
                channelId,
                VerifyStatus.APPROVED
        );
    }
}