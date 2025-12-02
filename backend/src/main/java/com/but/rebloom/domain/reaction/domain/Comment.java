package com.but.rebloom.domain.reaction.domain;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.post.domain.Post;
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
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id", nullable = false)
    private Long commentId;

    @Column(name = "co_content", nullable = false, length = 500)
    private String commentContent;

    @CreationTimestamp
    @Column(name = "co_created_at", nullable = false, updatable = false)
    private LocalDateTime commentCreatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_u_email", nullable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_p_id", nullable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
