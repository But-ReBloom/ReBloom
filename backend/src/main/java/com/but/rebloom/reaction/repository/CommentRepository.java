package com.but.rebloom.reaction.repository;

import com.but.rebloom.reaction.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글에 달린 댓글 조회 (최신순)
    List<Comment> findByPost_PostIdOrderByCommentCreatedAtDesc(Long postId);

    // 특정 유저가 작성한 댓글 조회(최신순)
    List<Comment> findByUser_UserIdOrderByCommentCreatedAtDesc(String UserId);

    // 특정 게시글의 댓글 수 카운트
    @Query("""
        select p.postTitle, count(*) from Comment c, Post p
        where c.postId = p.postId
    """)
    Map<String, Long> countByPostId(@Param("postId") Long postId);

    // 게시글 삭제 시 해당 게시글의 모든 댓글 일괄 삭제
    void deleteByPost_PostId(Long postId);
}
