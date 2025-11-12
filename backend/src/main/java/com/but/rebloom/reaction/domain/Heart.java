package com.but.rebloom.reaction.domain;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hearts")
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id", nullable = false)
    private Long heartId;

    @Column(name = "fk_u_email", nullable = false, insertable = false, updatable = false)
    private String userEmail;

    @Column(name = "fk_u_id", nullable = false, insertable = false, updatable = false)
    private String userId;

    @Column(name = "fk_p_id", nullable = false, insertable = false, updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "fk_u_email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_p_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
