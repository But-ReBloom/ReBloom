package com.but.rebloom.domain.post.usecase;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.global.exception.NoAuthorityException;
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
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 게시글 생성
    @Transactional
    public Post createPost(CreatePostRequest request) {
        // 유저 조회
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Channel channel = channelRepository.findByChannelIdAndChannelStatusAccepted(request.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));

        // 게시글 생성
        Post post = Post.builder()
                .user(user)
                .channel(channel)
                .postTitle(request.getPostTitle())
                .postContent(request.getPostContent())
                .postImage(request.getPostImage())
                .build();

        if (request.getPostType() != null) {
            post.setPostType(request.getPostType());
        }

        String post1AchievementTitle = "커뮤니티 시작!";
        defaultUserAchievementUseCase.updateUserAchievementToSuccess(user.getUserEmail(), post1AchievementTitle);

        return postRepository.save(post);
    }

    // 특정 게시글 조회
    @Transactional
    public Post getPost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        // 조회수 증가
        post.setPostViewers(post.getPostViewers() + 1);

        if (post.getPostViewers() >= 100) {
            post.setPostType(Type.POPULAR);
        }

        postRepository.save(post);

        return post;
    }

    // 특정 채널의 게시물 목록 조회(최신순)
    public List<Post> getPostsByChannel(Long channelId) {
        List<Post> posts = postRepository.findByChannel_ChannelIdOrderByPostCreatedAtDesc(channelId);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("게시글 조회 실패");
        }

        return posts;
    }

    // 특정 유저의 게시물 목록 조회(최신순)
    public List<Post> getPostsByUser(String userId) {
        List<Post> posts = postRepository.findByUser_UserIdOrderByPostCreatedAtDesc(userId);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("게시글 조회 실패");
        }

        return posts;
    }

    // 인기글 조회
    public List<Post> getPopularPosts() {
        List<Post> posts = postRepository.findByPostType(Type.POPULAR);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("게시글 조회 실패");
        }

        return posts;
    }

    // 게시글 검색
    public List<Post> searchPosts(SearchPostsRequest request) {
        List<Post> posts = postRepository.searchByKeyword(request.getKeyword());

        if (posts.isEmpty()) {
            throw new PostNotFoundException("게시글 조회 실패");
        }

        return posts;
    }

    // 인증 게시글 상태별 조회
    public List<Post> getPostsByStatus(Status status) {
        List<Post> posts = postRepository.findByPostStatus(status);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("게시글 조회 실패");
        }

        return posts;
    }

    // 인증 게시글 승인
    @Transactional
    public Post approvePost(Long postId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("승인할 권한이 없습니다.");
        }

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        if (post.getPostStatus() == Status.APPROVED) {
            throw new AlreadyProcessedChannelException("Post is already approved");
        }
        post.setPostStatus(Status.APPROVED);
        return postRepository.save(post);
    }

    // 인증 게시글 거절
    @Transactional
    public Post rejectPost(Long postId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("거절할 권한이 없습니다.");
        }

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        if(post.getPostStatus() == Status.REJECTED) {
            throw new AlreadyProcessedChannelException("Post is already rejected");
        }
        post.setPostStatus(Status.REJECTED);
        return postRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public Post updatePost(Long postId, UpdatePostRequest request) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserEmail().equals(post.getUser().getUserEmail())) {
            throw new NoAuthorityException("수정할 권한이 없습니다.");
        }

        // 게시글 수정
        post.setPostTitle(request.getPostTitle());
        post.setPostContent(request.getPostContent());
        post.setPostImage(request.getPostImage());
        return postRepository.save(post);
    }

    // 게시글 삭제(유저 본인)
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserEmail().equals(post.getUser().getUserEmail())) {
            throw new NoAuthorityException("삭제할 권한이 없습니다.");
        }

        postRepository.delete(post);
    }

    // 게시글 삭제 (관리자)
    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        User currentUser = findCurrentUserUseCase.getCurrentUser();

        if (!currentUser.getUserRole().equals(Role.ADMIN)) {
            throw new NoAuthorityException("삭제할 권한이 없습니다.");
        }

        postRepository.delete(post);
    }
}