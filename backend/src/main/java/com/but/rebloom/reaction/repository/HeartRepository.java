package com.but.rebloom.reaction.repository;

import com.but.rebloom.reaction.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    // 특정 게시글의 모든 하트 조회
    List<Heart> findByPostId(Long postId);

    // 특정 유저가 누른 모든 하트 조회
    List<Heart> findByUserId(String userId);

    // 하트 존재 유무 확인 - (userId + postId)
    boolean existsByUserIdAndPostId(String userId, Long postId);

    //  특정 게시글의 하트 수
    long countByPostId(Long postId);

    // 하트 취소
    void deleteByUserIdAndPostId(String userUserId, Long postId);
}
