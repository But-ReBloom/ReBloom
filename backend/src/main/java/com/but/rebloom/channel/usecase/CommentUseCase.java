package com.but.rebloom.channel.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.exception.UserNotFoundException;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.channel.domain.Comment;
import com.but.rebloom.channel.domain.Post;
import com.but.rebloom.channel.dto.request.CreateCommentRequest;
import com.but.rebloom.channel.dto.request.UpdateCommentRequest;
import com.but.rebloom.channel.exception.CommentNotFoundException;
import com.but.rebloom.channel.exception.ForbiddenAccessException;
import com.but.rebloom.channel.exception.PostNotFoundException;
import com.but.rebloom.channel.repository.CommentRepository;
import com.but.rebloom.channel.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentUseCase {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 댓글 생성
    @Transactional
    public Comment createComment(CreateCommentRequest request) {
        // 유저 조회
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // 게시글 조회
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        // 댓글 생성
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .commentContent(request.getCommentContent())
                .build();

        return commentRepository.save(comment);
    }

    // 특정 댓글 조회
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다."));
    }

    // 특정 게시글의 댓글 목록 조회 (최신순)
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_PostIdOrderByCommentCreatedAtDesc(postId);
    }

    // 특정 유저가 작성한 댓글 목록 조회 (최신순)
    public List<Comment> getCommentsByUser(String userId) {
        return commentRepository.findByUser_UserIdOrderByCommentCreatedAtDesc(userId);
    }

    // 특정 게시글의 댓글 수 조회
    public long getCommentCount(Long postId) {
        return commentRepository.countByPost_PostId(postId);
    }

    // 댓글 수정
    @Transactional
    public Comment updateComment(Long commentId, String userId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        // 작성자 확인
        if (!comment.getUser().getUserId().equals(userId)) {
            throw new ForbiddenAccessException("본인이 작성한 댓글만 수정 가능");
        }

        // 댓글 수정
        comment.setCommentContent(request.getCommentContent());

        return commentRepository.save(comment);
    }

    // 댓글 삭제 (일반 유저)
    @Transactional
    public void deleteComment(Long commentId, String userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        // 작성자 확인
        if (!comment.getUser().getUserId().equals(userId)) {
            throw new ForbiddenAccessException("본인이 작성한 댓글만 삭제가능");
        }

        commentRepository.delete(comment);
    }

    // 댓글 삭제 (관리자)
    @Transactional
    public void deleteCommentByAdmin(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }

    // 게시글 삭제 시 해당 게시글의 모든 댓글 일괄 삭제
    @Transactional
    public void deleteCommentsByPost(Long postId) {
        commentRepository.deleteByPost_PostId(postId);
    }
}
