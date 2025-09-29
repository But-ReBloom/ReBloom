package com.but.rebloom.channel.repository;

import com.but.rebloom.channel.domain.Channel;
import com.but.rebloom.channel.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    // 특정 게시글의 모든 하트 조회
    List<Heart> findByPost_PostId(Long postId);

    // 특정 유저가 누른 모든 하트 조회
    List<Heart> findByUser_UserId(String userId);

    boolean existsByUser_UserIdAndPost_PostId(String UserId, Long postPostId);

    //  특정 게시글의 하트 수
    long countByPost_PostId(Long postId);

    // 하트 취소
    void deleteByUser_UserIdAndPost_PostId(String userUserId, Long postId);

    // 게시글 삭제 시 하트 일괄 삭제
    void deleteByPost_PostId(Long postId);
}
