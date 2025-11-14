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
    List<Heart> findByPost_PostId(Long postId);

    // 특정 유저가 누른 모든 하트 조회
    List<Heart> findByUser_UserId(String userId);

    @Query("""
        select case when count(h) > 0 then true else false end
        from Heart h
        where h.postId = :postId and trim(h.userId) = :userId
    """)
    Boolean existsByUserIdAndPostId(
            @Param("userId") String userId,
            @Param("postId") Long postId
    );

    //  특정 게시글의 하트 수
    @Query("""
        select h.post.postTitle, count(h) from Heart h
        where h.postId = :postId
    """)
    Map<String, Long> countByPostId(@Param("postId") Long postId);

    // 하트 취소
    void deleteByUser_UserIdAndPost_PostId(String userUserId, Long postId);

    // 게시글 삭제 시 하트 일괄 삭제
    void deleteByPost_PostId(Long postId);
}
