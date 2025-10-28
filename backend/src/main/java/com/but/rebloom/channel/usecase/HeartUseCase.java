package com.but.rebloom.channel.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.channel.domain.Heart;
import com.but.rebloom.channel.domain.Post;
import com.but.rebloom.channel.dto.request.CreateHeartRequest;
import com.but.rebloom.channel.dto.request.DeleteHeartRequest;
import com.but.rebloom.channel.repository.HeartRepository;
import com.but.rebloom.channel.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartUseCase {
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 하트 생성 (좋아요 누르기)
    @Transactional
    public Heart createHeart(CreateHeartRequest request) {
        // 유저 조회
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 게시글 조회
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // 이미 하트를 눌렀는지 확인
        if (heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId())) {
            throw new IllegalStateException("already heart exists");
        }

        // 하트 생성
        Heart heart = Heart.builder()
                .user(user)
                .post(post)
                .build();

        return heartRepository.save(heart);
    }

    // 특정 게시글의 하트 목록 조회
    @Transactional
    public List<Heart> getHeartsByPost(Long postId) {
        return heartRepository.findByPost_PostId(postId);
    }

    // 특정 유저가 누른 하트 목록 조회
    @Transactional
    public List<Heart> getHeartsByUser(String userId) {
        return heartRepository.findByUser_UserId(userId);
    }

    // 특정 게시글의 하트 수 조회
    @Transactional
    public long getHeartCount(Long postId) {
        return heartRepository.countByPost_PostId(postId);
    }

    // 특정 유저가 특정 게시글에 하트를 눌렀는지 확인
    @Transactional
    public boolean isHeartExists(String userId, Long postId) {
        return heartRepository.existsByUser_UserIdAndPost_PostId(userId, postId);
    }

    // 하트 삭제 (좋아요 취소)
    @Transactional
    public void deleteHeart(DeleteHeartRequest request) {
        // 하트가 존재하는지 확인
        if (!heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId())) {
            throw new IllegalArgumentException("heart not exists");
        }

        heartRepository.deleteByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId());
    }

    // 게시글 삭제 시 하트 일괄 삭제
    @Transactional
    public void deleteHeartsByPost(Long postId) {
        heartRepository.deleteByPost_PostId(postId);
    }
}
