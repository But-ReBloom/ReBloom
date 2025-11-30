package com.but.rebloom.domain.post.usecase;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.jwt.JwtTokenProvider;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.post.domain.Status;
import com.but.rebloom.domain.post.domain.Type;
import com.but.rebloom.domain.post.dto.request.CreatePostRequest;
import com.but.rebloom.domain.post.dto.request.SearchPostsRequest;
import com.but.rebloom.domain.post.dto.request.UpdatePostRequest;
import com.but.rebloom.domain.channel.exception.AlreadyProcessedChannelException;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.exception.ForbiddenAccessException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.post.exception.PostNotFoundException;
import com.but.rebloom.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostUseCase {
    // 디비 접근
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    // 함수 호출
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 게시글 생성
    @Transactional
    public Post createPost(CreatePostRequest request) {
        // 유저 조회
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Channel channel = channelRepository.findByChannelId(request.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        // 게시글 생성
        Post post = Post.builder()
                .user(user)
                .channel(channel)
                .postTitle(request.getPostTitle())
                .postContent(request.getPostContent())
                .postImage(request.getPostImage())
                .postType(request.getPostType() != null ? request.getPostType() : Type.NORMAL)
                .build();

        String post1AchievementTitle = "커뮤니티 시작!";
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(user.getUserEmail(), post1AchievementTitle);

        return postRepository.save(post);
    }

    // 특정 게시글 조회
    @Transactional
    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        // 조회수 증가
        post.setPostViewers(post.getPostViewers() + 1);
        postRepository.save(post);

        return post;
    }

    // 특정 채널의 게시물 목록 조회(최신순)
    public List<Post> getPostsByChannel(Long channelId) {
        return postRepository.findByChannel_ChannelIdOrderByPostCreatedAtDesc(channelId);
    }

    // 특정 유저의 게시물 목록 조회(최신순)
    public List<Post> getPostsByUser(String userId) {
        return postRepository.findByUser_UserIdOrderByPostCreatedAtDesc(userId);
    }

    // 인기글 조회
    public List<Post> getPopularPosts() {
        return postRepository.findByPostType(Type.POPULAR);
    }

    // 게시글 검색
    public List<Post> searchPosts(SearchPostsRequest request) {
        return postRepository.searchByKeyword(request.getKeyword());
    }

    // 인증 게시글 상태별 조회
    public List<Post> getPostsByStatus(Status status) {
        return postRepository.findByPostStatus(status);
    }

    // 인증 게시글 승인
    @Transactional
    public Post approvePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        if(post.getPostStatus() == Status.APPROVED) {
            throw new AlreadyProcessedChannelException("Post is already approved");
        }
        post.setPostStatus(Status.APPROVED);
        return postRepository.save(post);
    }

    // 인증 게시글 거절
    @Transactional
    public Post rejectPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        if(post.getPostStatus() == Status.APPROVED) {
            throw new AlreadyProcessedChannelException("Post is already approved");
        }
        post.setPostStatus(Status.REJECTED);
        return postRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public Post updatePost(Long postId, UpdatePostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        // 작성자 확인
        if (!post.getUser().getUserId().equals(request.getUserId())) {
            throw new ForbiddenAccessException("본인이 작성한 게시글만 수정 가능");
        }

        // 게시글 수정
        post.setPostTitle(request.getPostTitle());
        post.setPostContent(request.getPostContent());
        post.setPostImage(request.getPostImage());
        return postRepository.save(post);
    }

    // 게시글 삭제(유저 본인)
    @Transactional
    public void deletePost(String token, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String userId = findCurrentUserUseCase.getCurrentUser().getUserId();

        // 작성자 확인
        if (!post.getUser().getUserId().equals(userId)) {
            throw new ForbiddenAccessException("본인이 작성한 게시글만 삭제 가능");
        }

        postRepository.delete(post);
    }

    // 게시글 삭제 (관리자)
    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        postRepository.delete(post);
    }
}