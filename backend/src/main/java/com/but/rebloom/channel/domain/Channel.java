package com.but.rebloom.channel.domain;

import com.but.rebloom.auth.domain.User;
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

    @Column(name = "ch_lk_hobby_1", nullable = false)
    private Long ChannelLinkedHobby1;

    @Column(name = "ch_lk_hobby_2", nullable = true)
    private Long ChannelLinkedHobby2;

    @Column(name = "ch_lk_hobby_3", nullable = true)
    private Long ChannelLinkedHobby3;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
