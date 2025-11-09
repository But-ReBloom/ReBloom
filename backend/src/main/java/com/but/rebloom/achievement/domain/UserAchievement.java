package com.but.rebloom.achievement.domain;

import com.but.rebloom.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserAchievementId.class) // 복합키 클래스 연결
@Table(name = "users_to_achieves")
public class UserAchievement {
    @Id
    @Column(name = "email", nullable = false)
    private String userEmail;

    @Id
    @Column(name = "achieve_id", nullable = false)
    private Long achieveId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "achieve_title", nullable = false)
    private String achieveTitle;

    @Column(name = "progress", nullable = false)
    private Float progress;

    @Column(name = "is_success", nullable = false)
    private Boolean isSuccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achieve_id", referencedColumnName = "achieve_id", insertable = false, updatable = false)
    private Achievement achievement;
}
