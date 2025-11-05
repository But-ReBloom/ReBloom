package com.but.rebloom.post.repository;

import com.but.rebloom.post.domain.Post;
import com.but.rebloom.post.domain.Status;
import com.but.rebloom.post.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 채널의 모든 게시글 조회(최신순)
    List<Post> findByChannel_ChannelIdOrderByPostCreatedAtDesc(Long channelId);

    // 특정 유저의 모든 게시글 조회(최신순)
    List<Post> findByUser_UserIdOrderByPostCreatedAtDesc(String userId);

    // 인기글 조회
    List<Post> findByPostType(Type type);

    // 특정 키워드를 제목이나 설명에 포함한 게시글 조회(최신순)
    List<Post> findByPostTitleContainingOrPostContentContainingOrderByPostCreatedAtDesc(String title, String content);

    // 인증
    // 상태 별 게시글 조회
    List<Post> findByPostStatus(Status status);
}
