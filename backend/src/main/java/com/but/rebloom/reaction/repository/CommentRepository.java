package com.but.rebloom.reaction.repository;

import com.but.rebloom.reaction.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글에 달린 댓글 조회 (최신순)
    List<Comment> findByPostIdOrderByCommentCreatedAtDesc(Long postId);

    // 특정 유저가 작성한 댓글 조회(최신순)
    List<Comment> findByUserIdOrderByCommentCreatedAtDesc(String UserId);

    // 특정 게시글의 댓글 수 카운트
    long countByPostId(Long postId);
}
