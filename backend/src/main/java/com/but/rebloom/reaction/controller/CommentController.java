package com.but.rebloom.reaction.controller;

import com.but.rebloom.reaction.domain.Comment;
import com.but.rebloom.reaction.dto.request.CreateCommentRequest;
import com.but.rebloom.reaction.dto.request.UpdateCommentRequest;
import com.but.rebloom.reaction.dto.response.CreateCommentResponse;
import com.but.rebloom.reaction.dto.response.FindCommentResponse;
import com.but.rebloom.reaction.usecase.CommentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentUseCase commentUseCase;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@Valid @RequestBody CreateCommentRequest request) {
        Comment comment = commentUseCase.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCommentResponse.from(comment));
    }

    // 특정 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CreateCommentResponse> getComment(@PathVariable Long commentId) {
        Comment comment = commentUseCase.getComment(commentId);
        return ResponseEntity.ok(CreateCommentResponse.from(comment));
    }

    // 특정 게시글의 댓글 목록 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<FindCommentResponse> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentUseCase.getCommentsByPost(postId);
        return ResponseEntity.ok(FindCommentResponse.from(comments));
    }

    // 특정 유저가 작성한 댓글 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<FindCommentResponse> getCommentsByUser(@PathVariable String userId) {
        List<Comment> comments = commentUseCase.getCommentsByUser(userId);
        return ResponseEntity.ok(FindCommentResponse.from(comments));
    }

    // 특정 게시글의 댓글 수 조회
    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Long> getCommentCount(@PathVariable Long postId) {
        long count = commentUseCase.getCommentCount(postId);
        return ResponseEntity.ok(count);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CreateCommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestParam String userId,
            @Valid @RequestBody UpdateCommentRequest request) {
        Comment comment = commentUseCase.updateComment(commentId, userId, request);
        return ResponseEntity.ok(CreateCommentResponse.from(comment));
    }

    // 댓글 삭제 (일반 유저)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestParam String userId) {
        commentUseCase.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }

    // 댓글 삭제 (관리자)
    @DeleteMapping("/admin/{commentId}")
    public ResponseEntity<Void> deleteCommentByAdmin(@PathVariable Long commentId) {
        commentUseCase.deleteCommentByAdmin(commentId);
        return ResponseEntity.noContent().build();
    }
}
