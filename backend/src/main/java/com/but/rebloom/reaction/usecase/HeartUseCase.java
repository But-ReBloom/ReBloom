package com.but.rebloom.reaction.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.exception.UserNotFoundException;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.post.domain.Post;
import com.but.rebloom.reaction.domain.Heart;
import com.but.rebloom.reaction.dto.request.CheckHeartExistsRequest;
import com.but.rebloom.reaction.dto.request.CreateHeartRequest;
import com.but.rebloom.reaction.dto.request.DeleteHeartRequest;
import com.but.rebloom.channel.exception.AlreadyUsingHeartException;
import com.but.rebloom.channel.exception.PostNotFoundException;
import com.but.rebloom.reaction.repository.HeartRepository;
import com.but.rebloom.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // 게시글 조회
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        // 이미 하트를 눌렀는지 확인
        if (heartRepository.existsByUserIdAndPostId(request.getUserId(), request.getPostId())) {
            throw new AlreadyUsingHeartException("already heart exists");
        }

        // 하트 생성
        Heart heart = Heart.builder()
                .user(user)
                .post(post)
                .build();

        return heartRepository.save(heart);
    }

    // 특정 게시글의 하트 목록 조회
    public List<Heart> getHeartsByPost(Long postId) {
        return heartRepository.findByPost_PostId(postId);
    }

    // 특정 유저가 누른 하트 목록 조회
    public List<Heart> getHeartsByUser(String userId) {
        return heartRepository.findByUser_UserId(userId);
    }

    // 특정 게시글의 하트 수 조회
    public Map<String, Long> getHeartCount(Long postId) {
        return heartRepository.countByPostId(postId);
    }

    // 특정 유저가 특정 게시글에 하트를 눌렀는지 확인
    public Map<String, Boolean> checkHeartExists(CheckHeartExistsRequest request) {
        Boolean isSuccess = heartRepository.existsByUserIdAndPostId(request.getUserId(), request.getPostId());
        String postTitle = postRepository.findPostByPostId(request.getPostId()).getPostTitle();
        return Map.of(postTitle, isSuccess);
    }

    // 하트 삭제 (좋아요 취소)
    @Transactional
    public void deleteHeart(DeleteHeartRequest request) {
        // 하트가 존재하는지 확인
        if (!heartRepository.existsByUserIdAndPostId(request.getUserId(), request.getPostId())) {
            throw new AlreadyUsingHeartException("heart not exists");
        }

        heartRepository.deleteByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId());
    }

    // 게시글 삭제 시 하트 일괄 삭제
    @Transactional
    public void deleteHeartsByPost(Long postId) {
        heartRepository.deleteByPost_PostId(postId);
    }
}
