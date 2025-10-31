package com.but.rebloom.channel.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.channel.domain.Channel;
import com.but.rebloom.channel.domain.Post;
import com.but.rebloom.channel.domain.Status;
import com.but.rebloom.channel.domain.Type;
import com.but.rebloom.channel.dto.request.CreatePostRequest;
import com.but.rebloom.channel.dto.request.FindPostRequest;
import com.but.rebloom.channel.dto.request.UpdatePostRequest;
import com.but.rebloom.channel.dto.response.CreatePostResponse;
import com.but.rebloom.channel.repository.ChannelRepository;
import com.but.rebloom.channel.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostUseCase {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    // 게시글 생성
    @Transactional
    public Post createPost(CreatePostRequest request) {
        // 유저 조회
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        Channel channel = channelRepository.findByChannelId(request.getChannelId())
                .orElseThrow(() -> new IllegalArgumentException("Channel Not Found"));

        // 게시글 생성
        Post post = Post.builder()
                .user(user)
                .channel(channel)
                .postTitle(request.getPostTitle())
                .postContent(request.getPostContent())
                .postImage(request.getPostImage())
                .postType(request.getPostType() != null ? request.getPostType() : Type.NORMAL)
                .build();

        return postRepository.save(post);
    }

    // 특정 게시글 조회
    @Transactional
    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        // 조회수 증가
        post.setVeiwers(post.getVeiwers() + 1);
        postRepository.save(post);

        return post;
    }

    // 특정 채널의 게시물 목록 조회(최신순)
    @Transactional
    public List<Post> getPostsByChannel(Long channelId) {
        return postRepository.findByChannel_ChannelIdOrderByPostCreatedAtDesc(channelId);
    }

    // 특정 유저의 게시물 목록 조회(최신순)
    @Transactional
    public List<Post> getPostsByUser(String userId) {
        return postRepository.findByUser_UserIdOrderByPostCreatedAtDesc(userId);
    }

    // 인기글 조회
    @Transactional
    public List<Post> getPopularPosts() {
        return postRepository.findByPostType(Type.POPULAR);
    }

    // 게시글 검색
    @Transactional
    public List<Post> searchPosts(FindPostRequest request){
        return postRepository.findByPostTitleContainingOrPostContentContainingOrderByPostCreatedAtDesc(request.getKeyword(), request.getKeyword());
    }

    // 인증 게시글 상태별 조회
    @Transactional
    public List<Post> getPostsByStatus(Status status) {
        return postRepository.findByPostStatus(status);
    }

    // 인증 게시글 승인
    @Transactional
    public Post approvePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        if(post.getPostStatus() == Status.APPROVED) {
            throw new IllegalArgumentException("Post is already approved");
        }
        post.setPostStatus(Status.APPROVED);
        return postRepository.save(post);
    }

    // 인증 게시글 거절
    @Transactional
    public Post rejectPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        if(post.getPostStatus() == Status.APPROVED) {
            throw new IllegalArgumentException("Post is already approved");
        }
        post.setPostStatus(Status.REJECTED);
        return postRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public Post updatePost(Long postId, String userId, UpdatePostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        // 작성자 확인
        if (!post.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 수정 가능");
        }

        // 게시글 수정
        post.setPostTitle(request.getPostTitle());
        post.setPostContent(request.getPostContent());
        post.setPostImage(request.getPostImage());
        return postRepository.save(post);
    }

    // 게시글 삭제(유저 본인)
    @Transactional
    public void deletePost(Long postId, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        // 작성자 확인
        if (!post.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 삭제 가능");
        }

        postRepository.delete(post);
    }

    // 게시글 삭제 (관리자)
    @Transactional
    public void deletePostByAdmin(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        postRepository.delete(post);
    }
}
