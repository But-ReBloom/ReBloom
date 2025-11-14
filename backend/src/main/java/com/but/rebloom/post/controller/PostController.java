package com.but.rebloom.post.controller;

import com.but.rebloom.common.dto.ApiResponse;
import com.but.rebloom.post.domain.Post;
import com.but.rebloom.post.domain.Status;
import com.but.rebloom.post.dto.request.CreatePostRequest;
import com.but.rebloom.post.dto.request.UpdatePostRequest;
import com.but.rebloom.post.dto.response.CreatePostResponse;
import com.but.rebloom.post.dto.response.FindPostResponse;
import com.but.rebloom.post.usecase.PostUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostUseCase postUseCase;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(@Valid @RequestBody CreatePostRequest request) {
        Post post = postUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreatePostResponse.from(post)));
    }

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<CreatePostResponse>> findPost(@PathVariable Long postId) {
        Post post = postUseCase.getPost(postId);
        return ResponseEntity.ok(ApiResponse.success(CreatePostResponse.from(post)));
    }

    // 특정 채널의 게시글 목록 조회
    @GetMapping("/channel/{channelId}")
    public ResponseEntity<ApiResponse<FindPostResponse>> getPostsByChannel(@PathVariable Long channelId) {
        List<Post> posts = postUseCase.getPostsByChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success(FindPostResponse.from(posts)));
    }

    // 특정 유저의 게시글 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<FindPostResponse>> getPostsByUser(@PathVariable String userId) {
        List<Post> posts = postUseCase.getPostsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(FindPostResponse.from(posts)));
    }

    // 인기글 조회
    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<FindPostResponse>> getPopularPosts() {
        List<Post> posts = postUseCase.getPopularPosts();
        return ResponseEntity.ok(ApiResponse.success(FindPostResponse.from(posts)));
    }

    // 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<FindPostResponse>> searchPosts(@RequestParam String keyword) {
        List<Post> posts = postUseCase.searchPosts(keyword);
        return ResponseEntity.ok(ApiResponse.success(FindPostResponse.from(posts)));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<CreatePostResponse>> updatePost(@PathVariable Long postId, @RequestParam String userId, @Valid @RequestBody UpdatePostRequest request) {
        Post post = postUseCase.updatePost(postId, userId, request);
        return ResponseEntity.ok(ApiResponse.success(CreatePostResponse.from(post)));
    }

    // 게시글 삭제(유저 본인)
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId, @RequestParam String userId) {
        postUseCase.deletePost(postId, userId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // admin
    // 게시글 삭제 (관리자)
    @DeleteMapping("/admin/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePostByAdmin(@PathVariable Long postId) {
        postUseCase.deletePostByAdmin(postId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 상태별 게시글 조회 (인증게시글)
    @GetMapping("/admin/status/{status}")
    public ResponseEntity<ApiResponse<FindPostResponse>> getPostsByStatus(@PathVariable Status status) {
        List<Post> posts = postUseCase.getPostsByStatus(status);
        return ResponseEntity.ok(ApiResponse.success(FindPostResponse.from(posts)));
    }

    // 인증 게시글 승인 (관리자용)
    @PatchMapping("/admin/{postId}/approve")
    public ResponseEntity<ApiResponse<CreatePostResponse>> approvePost(@PathVariable Long postId) {
        Post post = postUseCase.approvePost(postId);
        return ResponseEntity.ok(ApiResponse.success(CreatePostResponse.from(post)));
    }

    // 인증 게시글 거절 (관리자용)
    @PatchMapping("/admin/{postId}/reject")
    public ResponseEntity<ApiResponse<CreatePostResponse>> rejectPost(@PathVariable Long postId) {
        Post post = postUseCase.rejectPost(postId);
        return ResponseEntity.ok(ApiResponse.success(CreatePostResponse.from(post)));
    }

}
