package com.but.rebloom.post.repository;

import com.but.rebloom.post.domain.Post;
import com.but.rebloom.post.domain.Status;
import com.but.rebloom.post.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("""
        select p
        from Post p
        where p.postId = :postId
    """)
    Post findPostByPostId(@Param("postId") Long postId);

    // 특정 키워드를 제목이나 설명에 포함한 게시글 조회(최신순)
    @Query("""
        select p from Post p
        where p.postTitle like concat('%', :keyword, '%')
            or p.postContent like concat('%', :keyword, '%')
        order by p.postTitle asc
    """)
    List<Post> findByPostInfoByKeyword(@Param("keyword") String keyword);

    // 인증
    // 상태 별 게시글 조회
    List<Post> findByPostStatus(Status status);
}
