package com.but.rebloom.domain.channel.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.domain.channel.dto.request.SearchChannelRequest;
import com.but.rebloom.domain.channel.exception.AlreadyProcessedChannelException;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.exception.InsufficientPointException;
import com.but.rebloom.domain.channel.exception.InsufficientTeirPointException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelUseCase {
    // 디비 접근
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;

    private static final int requiredPoints = 500; // 채널 생성에 필요한 포인트 임시
    private static final int requiredTierPoint = 500; // 채널 생성할 때 요구되는 최소 티어 임시

    // 채널 생성 요청
    @Transactional
    public Map<Channel, String> requestCreation(CreateChannelRequest request) {
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
                .channelLinkedHobby1(hobbyRepository.findByHobbyId(request.getChannelLinkedHobby1())
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음")))
                .channelLinkedHobby2(hobbyRepository.findByHobbyId(request.getChannelLinkedHobby2())
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음")))
                .channelLinkedHobby3(hobbyRepository.findByHobbyId(request.getChannelLinkedHobby3())
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음")))
                .build();

        channelRepository.save(channel);

        return Map.of(channel, hobby1.getHobbyName());
    }

    // 채널 검색
    public List<Map<Channel, String>> findChannel(SearchChannelRequest request) {
        List<Channel> channels = channelRepository
                .searchByKeyword(request.getKeyword());

        return channels.stream()
                .filter(Channel::getIsAccepted)
                .map(channel -> {
                    String hobbyName = hobbyRepository
                            .findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                            .map(Hobby::getHobbyName)
                            .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
                    return Map.of(channel, hobbyName);
                })
                .collect(Collectors.toList());
    }

    // 승인된 채널 목록
    public List<Map<Channel, String>> getApprovedChannels() {
        List<Channel> channels = channelRepository.findByIsAcceptedTrue();

        return channels.stream()
                .filter(Channel::getIsAccepted)
                .map(channel -> {
                    String hobbyName = hobbyRepository
                            .findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                            .map(Hobby::getHobbyName)
                            .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
                    return Map.of(channel, hobbyName);
                })
                .collect(Collectors.toList());
    }

    // 승인대기 채널 목록
    public List<Map<Channel, String>> getPendingChannels() {
        List<Channel> channels = channelRepository.findByIsAcceptedFalse();

        return channels.stream()
                .filter(Channel::getIsAccepted)
                .map(channel -> {
                    String hobbyName = hobbyRepository
                            .findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                            .map(Hobby::getHobbyName)
                            .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
                    return Map.of(channel, hobbyName);
                })
                .collect(Collectors.toList());
    }

    // 채널 승인
    @Transactional
    public Map<Channel, List<String>> approveChannel(Long channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        if (channel.getIsAccepted()) {
            throw new AlreadyProcessedChannelException("This channel is already accepted");
        }

        Hobby hobby1 = hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));

        Hobby hobby2 = null;
        Hobby hobby3 = null;

        if (channel.getChannelLinkedHobby2() != null) {
            hobby2 = hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby2().getHobbyId())
                    .orElseThrow(() -> new ActivityNotFoundException("활동이 조회되지 않음"));
        } if (channel.getChannelLinkedHobby3() != null) {
            hobby2 = hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby3().getHobbyId())
                    .orElseThrow(() -> new ActivityNotFoundException("활동이 조회되지 않음"));
        }

        channel.setIsAccepted(true);
        channelRepository.save(channel);

        return Map.of(channel, List.of(hobby1.getHobbyName(), hobby2.getHobbyName(), hobby3.getHobbyName()));
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
    public Map<Channel, String> getChannel(Long channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        Hobby hobby = hobbyRepository.findByHobbyId(channel.getChannelLinkedHobby1().getHobbyId())
                .orElseThrow(() -> new ActivityNotFoundException("활동이 조회되지 않음"));

        return Map.of(channel, hobby.getHobbyName());
    }
}