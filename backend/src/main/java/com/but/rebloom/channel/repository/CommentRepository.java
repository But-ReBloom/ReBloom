package com.but.rebloom.channel.repository;

import com.but.rebloom.channel.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글에 달린 댓글 조회 (최신순)
    List<Comment> findByPost_PostIdOrderByCommentCreatedAtDesc(Long postId);

    // 특정 유저가 작성한 댓글 조회(최신순)
    List<Comment> findByUser_UserIdOrderByCommentCreatedAtDesc(String UserId);

    // 특정 게시글의 댓글 수 카운트
    long countByPost_PostId(Long postId);

    boolean existsByUser_UserIdAndCommentId(String userUserId, Long commentId);

    // 특정 댓글 삭제 (권한 체크는 유스케이스에서)
    void deleteByCommentId(Long commentId);

    // 게시글 삭제 시 해당 게시글의 모든 댓글 일괄 삭제
    void deleteByPost_PostId(Long postId);
}
