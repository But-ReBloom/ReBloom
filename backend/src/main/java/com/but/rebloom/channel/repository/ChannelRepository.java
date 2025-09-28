package com.but.rebloom.channel.repository;

import com.but.rebloom.channel.domain.Channel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    // 특정 키워드를 제목이나 설명에 포함한 채널 조회
    List<Channel> findByChannelTitleContainingOrChannelDescriptionContaining(String channelTitle, String channelDescription);

    // 승인된 채널 목록 조회
    List<Channel> findByIsAcceptedTrue(Boolean isAccepted);

    // 아직 승인되지 않은 채널 목록 조회
    List<Channel> findByIsAcceptedFalse(Boolean isAccepted);

    // 관리자 승인/거절 처리 (일주일 이내)
    @Query("SELECT c FROM Channel c WHERE c.isAccepted = false AND c.channelCreatedAt < :deadline")
    List<Channel> findPendingChannelsOlderThan(LocalDateTime deadline);

}