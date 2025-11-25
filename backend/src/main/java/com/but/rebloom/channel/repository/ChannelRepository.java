package com.but.rebloom.channel.repository;

import com.but.rebloom.channel.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    // ID로 채널 조회
    Optional<Channel> findByChannelId(Long channelId);

    // 특정 키워드를 제목이나 설명에 포함한 채널 조회
    @Query("""
        select c from Channel c
        where c.channelTitle like concat('%', :keyword, '%')
            or c.channelDescription like concat('%', :keyword, '%')
        order by c.channelTitle asc
    """)
    List<Channel> searchByKeyword(@Param("keyword") String keyword);

    // 승인된 채널 목록 조회
    List<Channel> findByIsAcceptedTrue();

    // 아직 승인되지 않은 채널 목록 조회
    List<Channel> findByIsAcceptedFalse();

    // 연관 취미로 채널 목록 검색
    @Query("""
        select c from
        Channel c
        where c.ChannelLinkedHobby1 = :hobbyId
            or c.ChannelLinkedHobby2 = :hobbyId
            or c.ChannelLinkedHobby3 = :hobbyId
    """)
    List<Channel> findByChannelLinkedHobby(@Param("hobbyId") Long hobbyId);

    // 관리자 승인/거절 처리 (일주일 이내)
    @Query("select c from Channel c where c.isAccepted = false and c.channelCreatedAt < :deadline")
    List<Channel> findPendingChannelsOlderThan(@Param("deadline") LocalDateTime deadline);
}