package com.but.rebloom.domain.channel.repository;

import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {
    // 특정채널의 유저 목록 조회
    List<UserChannel> findByChannel_ChannelIdAndUserChannelVerifyStatus(
            Long channelId,
            VerifyStatus userChannelVerifyStatus
    );

    // 특정유저의 채널 목록 조회
    List<UserChannel> findByUser_UserEmailAndUserChannelVerifyStatus(
            String userEmail,
            VerifyStatus userChannelVerifyStatus
    );

    // 존재하는지 확인
    boolean existsByChannel_ChannelIdAndUser_UserEmail(Long channelId, String email);

    // 유저 채널 확인
    Optional<UserChannel> findByChannel_ChannelIdAndUser_UserEmail(Long channelId, String email);

    // 유저 채널 목록 확인
    Optional<UserChannel> findByUser_UserEmail(String email);
}
