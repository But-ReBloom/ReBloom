package com.but.rebloom.domain.channel.domain;

import com.but.rebloom.domain.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(UserChannelId.class)
@Table(name = "user_to_channels")
public class UserChannel {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_email", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_ch_id", nullable = false)
    private Channel channel;

    @Column(name = "uc_message", nullable = false)
    private String applyMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "uc_status", nullable = false)
    @Builder.Default
    private VerifyStatus userChannelVerifyStatus = VerifyStatus.WAITING;
}
