package com.but.rebloom.domain.post.domain;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.channel.domain.Channel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(name = "p_id", nullable = false)
    private Long postId;

    @Column(name = "p_title", nullable = false)
    private String postTitle;

    @Column(name = "p_content", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String postContent;

    @Column(name = "p_image")
    private String postImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "p_type", nullable = false)
    @Builder.Default
    private Type postType = Type.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "p_status")
    @Builder.Default
    private Status postStatus = Status.PENDING;

    @CreationTimestamp
    @Column(name = "p_created_at", nullable = false, updatable = false)
    private LocalDateTime postCreatedAt;

    @Column(name = "p_viewers", nullable = false)
    @Builder.Default
    private Integer postViewers = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_ch_id", nullable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email", nullable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
