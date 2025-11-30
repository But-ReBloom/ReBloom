package com.but.rebloom.domain.reaction.repository;

import com.but.rebloom.domain.reaction.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    // 특정 게시글의 모든 하트 조회
    List<Heart> findByPost_PostId(Long postId);

    // 특정 유저가 누른 모든 하트 조회
    List<Heart> findByUser_UserId(String userId);

    // 하트 존재 유무 확인 - (userId + postId)
    boolean existsByUser_UserIdAndPost_PostId(String userId, Long postId);

    // 하트 조회 - (userId + postId)
    Optional<Heart> findByPost_PostIdAndUser_UserId(Long postId, String userId);

    //  특정 게시글의 하트 수
    long countByPost_PostId(Long postId);

    // 하트 취소
    void deleteByUser_UserIdAndPost_PostId(String userUserId, Long postId);
}