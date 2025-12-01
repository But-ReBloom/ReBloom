package com.but.rebloom.domain.auth.domain;

import com.but.rebloom.domain.hobby.domain.Activity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Enumerated(EnumType.STRING)
    private TierName userTier = TierName.IRON;

    @Column(name = "u_recent_date", nullable = false)
    @UpdateTimestamp
    @Builder.Default
    private LocalDate userRecentDate = LocalDate.now();

    @Column(name = "u_streak", nullable = false)
    @Builder.Default
    private Integer userStreak = 1;

    @JoinColumn(name = "u_current_act", referencedColumnName = "act_id", nullable = true)
    @Builder.Default
    @OneToOne(fetch = FetchType.LAZY)
    private Activity userCurrentActivity = null;
}