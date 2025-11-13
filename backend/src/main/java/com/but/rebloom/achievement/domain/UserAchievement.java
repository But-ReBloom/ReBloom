package com.but.rebloom.achievement.domain;

import com.but.rebloom.auth.domain.User;
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
@IdClass(UserAchievementId.class)
@Table(name = "users_to_achieves")
public class UserAchievement {
    @Id
    @Column(name = "fk_u_email", nullable = false)
    private String userEmail;

    @Id
    @Column(name = "fk_ach_id", nullable = false, insertable = false, updatable = false)
    private Long achievementId;

    @Column(name = "fk_u_id", nullable = false)
    private String userId;

    @Column(name = "fk_ach_title", nullable = false)
    private String achievementTitle;

    @Column(name = "uach_progress", nullable = false)
    private Float userAchievementProgress;

    @Column(name = "uach_is_success", nullable = false)
    private Boolean isSuccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_ach_id", referencedColumnName = "ach_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Achievement achievement;
}
