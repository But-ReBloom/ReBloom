package com.but.rebloom.domain.reaction.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.reaction.domain.Heart;
import com.but.rebloom.domain.reaction.dto.request.CheckHeartExistsRequest;
import com.but.rebloom.domain.reaction.dto.request.CreateHeartRequest;
import com.but.rebloom.domain.reaction.dto.request.DeleteHeartRequest;
import com.but.rebloom.domain.reaction.exception.AlreadyUsingHeartException;
import com.but.rebloom.domain.reaction.dto.request.HeartNotificationRequest;
import com.but.rebloom.domain.reaction.exception.HeartNotFoundException;
import com.but.rebloom.domain.reaction.repository.HeartRepository;
import com.but.rebloom.domain.post.repository.PostRepository;
import com.but.rebloom.global.exception.NoAuthorityException;
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
    private final NotificationUseCase notificationUseCase;
    private final FindCurrentUserUseCase findCurrentUserUseCase;

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
        if (heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId())) {
            throw new AlreadyUsingHeartException("already heart exists");
        }

        // 하트 생성
        Heart heart = Heart.builder()
                .user(user)
                .post(post)
                .build();

        Heart savedHeart = heartRepository.save(heart);
        // 본인이 작성한 글에 남긴 좋아요가 아닐 때만 알림 전송
        if(!post.getUser().getUserId().equals(user.getUserId())) {
            HeartNotificationRequest heartNotificationRequest = HeartNotificationRequest.builder()
                    .ownerUserId(post.getUser().getUserId())
                    .ownerEmail(post.getUser().getUserEmail())
                    .likerUserId(user.getUserId())
                    .heartId(savedHeart.getHeartId())
                    .postId(post.getPostId())
                    .build();

            notificationUseCase.sendHeartNotification(heartNotificationRequest);
        }

        return savedHeart;
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
    public Map<Post, Long> getHeartCount(Long postId) {
        return Map.of(
                postRepository.findByPostId(postId)
                        .orElseThrow(() -> new PostNotFoundException("게시글이 조회되지 않음"))
                , heartRepository.countByPost_PostId(postId)
        );
    }

    // 특정 유저가 특정 게시글에 하트를 눌렀는지 확인
    public Map<Post, Boolean> checkHeartExists(CheckHeartExistsRequest request) {
        boolean isSuccess = heartRepository.existsByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId());
        Post post = postRepository.findByPostId(request.getPostId())
                .orElseThrow(() -> new PostNotFoundException("게시글이 조회되지 않음"));
        return Map.of(post, isSuccess);
    }

    // 하트 삭제 (좋아요 취소)
    @Transactional
    public void deleteHeart(DeleteHeartRequest request) {
        Heart heart = heartRepository.findByPost_PostIdAndUser_UserId(request.getPostId(), request.getUserId())
                    .orElseThrow(() -> new HeartNotFoundException("하트조회 실패"));

        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserEmail().equals(heart.getUser().getUserEmail())) {
            throw new NoAuthorityException("취소할 권한이 없습니다.");
        }

        heartRepository.deleteByUser_UserIdAndPost_PostId(request.getUserId(), request.getPostId());
    }
}