package com.but.rebloom.domain;

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

    @Id
    @Column(name = "email", unique = true, nullable = false)
    private String userEmail;

    @Column(name = "id", unique = true, nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String userPassword;

    @Column(name = "name", nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Builder.Default
    private Role userRole = Role.USER;

    @Column(name = "tier_point")
    @Builder.Default
    private Integer userTierPoint = 0;

    @Column(name = "point")
    @Builder.Default
    private Integer userPoint = 0;
}
