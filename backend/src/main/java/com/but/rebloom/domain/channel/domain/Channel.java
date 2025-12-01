package com.but.rebloom.domain.channel.domain;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.hobby.domain.Hobby;
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
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id")
    private Long channelId;

    @Column(name = "ch_title", nullable = false)
    private String channelTitle;

    @Column(name = "ch_intro", nullable = false)
    private String channelIntro;

    @Column(name = "ch_description", nullable = false)
    private String channelDescription;

    @CreationTimestamp
    @Column(name = "ch_created_at", nullable = false, updatable = false)
    private LocalDateTime channelCreatedAt;

    @Column(name = "ch_is_accepted", nullable = false)
    private Boolean isAccepted = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ch_lk_h_1_id", referencedColumnName = "h_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hobby channelLinkedHobby1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ch_lk_h_2_id", referencedColumnName = "h_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Builder.Default
    private Hobby channelLinkedHobby2 = null;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ch_lk_h_3_id", referencedColumnName = "h_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Builder.Default
    private Hobby channelLinkedHobby3 = null;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
