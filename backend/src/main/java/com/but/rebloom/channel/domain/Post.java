package com.but.rebloom.channel.domain;

import com.but.rebloom.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @Column(name = "title", nullable = false)
    private String postTitle;

    @Column(name = "content", nullable = false)
    private String postContent;

    @Column(name = "image", nullable = true)
    private String postImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Builder.Default    // 기본값 설정
    private Type postType = Type.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private Status postStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime postCreatedAt;

    @Column(name = "veiwers", nullable = false)
    @Builder.Default
    private int veiwers = 0;
}
