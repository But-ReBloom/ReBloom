package com.but.rebloom.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @Column(name = "u_email", nullable = false, unique = true, length = 100)
    private String userEmail;

    @Column(name = "u_id", nullable = false, unique = true, length = 255)
    private String userId;

    @Column(name = "u_current_act")
    private Integer currentAct; // u_current_act int

    @Column(name = "u_password", nullable = false, length = 255)
    private String userPassword;

    @Column(name = "u_name", nullable = false, length = 20)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "u_role", nullable = false, length = 255)
    @Builder.Default
    private Role userRole = Role.USER;

    @Column(name = "u_tier_point", nullable = false)
    @Builder.Default
    private Integer userTierPoint = 0;

    @Column(name = "u_point", nullable = false)
    @Builder.Default
    private Integer userPoint = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "u_provider", length = 255)
    @Builder.Default
    private Provider userProvider = Provider.SELF;

    @Column(name = "u_tier", nullable = false, length = 20)
    @Builder.Default
    private String userTier = "BRONZE";

    @Column(name = "u_recent_date", nullable = false)
    private LocalDate userRecentDate;

    @Column(name = "u_streak", nullable = false)
    @Builder.Default
    private Integer userStreak = 1;
}
