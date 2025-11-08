package com.but.rebloom.achievement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "achieves")
public class Achievement {
    // 테이블 속성 연결
    @Id
    @Column(name = "achieve_id", unique = true, nullable = false)
    private Long achievementId;

    @Column(name = "title", unique = true, nullable = false)
    private String achievementTitle;

    @Column(name = "description", nullable = false)
    private String achievementDescription;

    @Column(name = "reward_point", nullable = false)
    private String achievementRewardPoint;

    @Column(name = "tier_point", nullable = false)
    private String achievementTierPoint;
}
