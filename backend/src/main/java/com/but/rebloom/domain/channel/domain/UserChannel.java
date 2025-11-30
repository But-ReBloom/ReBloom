package com.but.rebloom.domain.channel.domain;

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
    @Column(name = "fk_u_email", nullable = false)
    private String userEmail;

    @Id
    @Column(name = "fk_ch_id", nullable = false, insertable = false, updatable = false)
    private Long channelId;

    @Column(name = "uc_message", nullable = false)
    private String applyMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "uc_status", nullable = false)
    @Builder.Default
    private VerifyStatus userChannelVerifyStatus = VerifyStatus.WAITING;
}
