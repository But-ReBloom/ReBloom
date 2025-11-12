package com.but.rebloom.channel.domain;

import com.but.rebloom.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id", nullable = false)
    private Long channelId;

    @ManyToOne
    @JoinColumn(name = "u_email", nullable = false)
    private User user; // User entity와 맵핑을 위해 필드명을 userId가 아닌 user로

    @Column(name = "title", nullable = false)
    private String channelTitle;

    @Column(name = "intro", nullable = false)
    private String channelIntro;

    @Column(name = "description", nullable = false)
    private String channelDescription;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime channelCreatedAt;

    @Column(name = "is_accepted",nullable = false)
    private Boolean isAccepted = false;

}
