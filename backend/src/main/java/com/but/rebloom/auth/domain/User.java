package com.but.rebloom.auth.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    // 테이블 속성 연결
    @Id
    @Column(name = "email", unique = true, nullable = false)
    private String userEmail;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String userPassword;

    @Column(name = "name", nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Builder.Default    // 기본값 설정
    private Role userRole = Role.USER;

    @Column(name = "tier_point")
    @Builder.Default    // 기본값 설정
    private Integer userTierPoint = 0;

    @Column(name = "point")
    @Builder.Default    // 기본값 설정
    private Integer userPoint = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    @Builder.Default
    private Provider provider = Provider.SELF;
}
