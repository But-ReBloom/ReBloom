package com.but.rebloom.domain.reaction.controller;

import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.reaction.domain.Comment;
import com.but.rebloom.domain.reaction.dto.request.CreateCommentRequest;
import com.but.rebloom.domain.reaction.dto.request.UpdateCommentRequest;
import com.but.rebloom.domain.reaction.dto.response.CreateCommentResponse;
import com.but.rebloom.domain.reaction.dto.response.FindCommentResponse;
import com.but.rebloom.domain.reaction.dto.response.GetCommentCountResponse;
import com.but.rebloom.domain.reaction.usecase.CommentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentUseCase commentUseCase;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<ApiResponse<CreateCommentResponse>> createComment(@Valid @RequestBody CreateCommentRequest request) {
        Comment comment = commentUseCase.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreateCommentResponse.from(comment)));
    }

    // 특정 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CreateCommentResponse>> getComment(@PathVariable Long commentId) {
        Comment comment = commentUseCase.getComment(commentId);
        return ResponseEntity.ok(ApiResponse.success(CreateCommentResponse.from(comment)));
    }

    // 특정 게시글의 댓글 목록 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<FindCommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentUseCase.getCommentsByPost(postId);
        return ResponseEntity.ok(ApiResponse.success(FindCommentResponse.from(comments)));
    }

    // 특정 유저가 작성한 댓글 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<FindCommentResponse>> getCommentsByUser(@PathVariable String userId) {
        List<Comment> comments = commentUseCase.getCommentsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(FindCommentResponse.from(comments)));
    }

    // 특정 게시글의 댓글 수 조회
    @GetMapping("/post/{postId}/count")
    public ResponseEntity<ApiResponse<GetCommentCountResponse>> getCommentCount(@PathVariable Long postId) {
        Map<Post, Long> response = commentUseCase.getCommentCount(postId);
        return ResponseEntity.ok(ApiResponse.success(GetCommentCountResponse.from(response)));
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CreateCommentResponse>> updateComment(
            @PathVariable Long commentId,
            @RequestBody String token,
            @Valid @RequestBody UpdateCommentRequest request
    ) {
        Comment comment = commentUseCase.updateComment(commentId, token, request);
        return ResponseEntity.ok(ApiResponse.success(CreateCommentResponse.from(comment)));
    }

    // 댓글 삭제 (일반 유저)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long commentId,
            @RequestParam String userId
    ) {
        commentUseCase.deleteComment(commentId, userId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 댓글 삭제 (관리자)
    @DeleteMapping("/admin/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteCommentByAdmin(@PathVariable Long commentId) {
        commentUseCase.deleteCommentByAdmin(commentId);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
