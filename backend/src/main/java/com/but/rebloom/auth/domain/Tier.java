package com.but.rebloom.auth.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tier_rank")
public class Tier {
    @Id
    @Column(name = "tr_name", nullable = false)
    private String tierName;

    @Column(name = "tr_min_point", nullable = false)
    private Integer tierMinPoint;

    @Column(name = "tr_max_point", nullable = false)
    private Integer tierMaxPoint;
}
