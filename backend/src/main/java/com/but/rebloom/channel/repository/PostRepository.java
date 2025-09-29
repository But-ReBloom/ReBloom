package com.but.rebloom.channel.repository;

import com.but.rebloom.channel.domain.Post;
import com.but.rebloom.channel.domain.Status;
import com.but.rebloom.channel.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 채널의 모든 게시글 조회(최신순)
    List<Post> findByChannel_ChannelIdOrderByPostCreatedAtDesc(Long channelId);

    List<Post> findByUser_UserIdOrderByPostCreateAtDesc(String userUserId);

    // 인기글 조회
    List<Post> findByPostType(Type type);

    // 특정 키워드를 제목이나 설명에 포함한 게시글 조회(최신순)
    List<Post> findByTitleContainingOrContentContainingOrderByPostCreatedAtDescDesc(String title, String content);

    // 인증
    // 상태 별 게시글 조회
    List<Post> findByStatus(Status status);

    // 본인이 작성한 게시글인지 확인(일반 유저 게시글 삭제)
    boolean existByUser_UserId(String userId);
}
