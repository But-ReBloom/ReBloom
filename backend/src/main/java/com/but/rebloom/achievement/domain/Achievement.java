package com.but.rebloom.achievement.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "achieves")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ach_id", nullable = false, unique = true)
    private Long achievementId;

    @Column(name = "ach_title", nullable = false, unique = true, length = 100)
    private String achievementTitle;

    @Column(name = "ach_description", nullable = false, columnDefinition = "TEXT")
    private String achievementDescription;

    @Column(name = "ach_reward_point", nullable = false)
    private Integer achievementRewardPoint;

    @Column(name = "ach_reward_tier_point", nullable = false)
    private Integer achievementRewardTierPoint;
}
