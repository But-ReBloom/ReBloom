package com.but.rebloom.domain.channel.repository;

import com.but.rebloom.domain.channel.domain.Channel;
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
    @Query("""
        select c
        from Channel c
        where c.channelId = :channelId and c.channelStatus = "ACCEPTED"
    """)
    Optional<Channel> findByChannelIdAndChannelStatusAccepted(@Param("channelId") Long channelId);

    // 특정 키워드를 제목이나 설명에 포함한 채널 조회
    @Query("""
        select c from Channel c
        where (c.channelTitle like concat('%', :keyword, '%')
            or c.channelIntro like concat('%', :keyword, '%')
            or c.channelDescription like concat('%', :keyword, '%'))
            and c.channelStatus = 'ACCEPTED'
        order by c.channelTitle asc
    """)
    List<Channel> searchByKeyword(@Param("keyword") String keyword);

    // 승인된 채널 목록 조회
    @Query("""
        select c
        from Channel c
        where c.channelStatus = 'ACCEPTED'
    """)
    List<Channel> findByChannelStatusAccepted();

    // 아직 승인되지 않은 채널 목록 조회
    @Query("""
        select c
        from Channel c
        where c.channelStatus = 'PENDING'
    """)
    List<Channel> findByChannelStatusPending();

    // 연관 취미로 채널 목록 검색
    @Query("""
        select c from
        Channel c
        where :hobbyId in (
            c.channelLinkedHobby1.hobbyId,
            c.channelLinkedHobby2.hobbyId,
            c.channelLinkedHobby3.hobbyId
        )
    """)
    List<Channel> findByChannelLinkedHobby(@Param("hobbyId") Long hobbyId);

    // 관리자 승인/거절 처리 (일주일 이내)
    @Query("""
        select c
        from Channel c
        where c.channelStatus = "PENDING"
            and c.channelCreatedAt < :deadline
    """)
    List<Channel> findPendingChannelsOlderThan(@Param("deadline") LocalDateTime deadline);

    // 채널이 존재하는지 확인
    boolean existsByChannelTitle(String channelTitle);
}