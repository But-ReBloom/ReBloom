package com.but.rebloom.hobby.domain;

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
@Table(name = "hobby")
public class HobbyWeight {
    @Id
    @Column(name = "h_id", nullable = false, unique = true, length = 255)
    private String userId;

    @Column(name = "h_name", nullable = false, length = 255)
    private String hobbyName;

    @Column(name = "h_w_social", nullable = false)
    private Double hobbyWeightSocial;

    @Column(name = "h_w_learning", nullable = false)
    private Double hobbyWeightLearning;

    @Column(name = "h_w_planning", nullable = false)
    private Double hobbyWeightPlanning;

    @Column(name = "h_w_focus", nullable = false)
    private Double hobbyWeightFocus;

    @Column(name = "h_w_creativity", nullable = false)
    private Double hobbyWeightCreativity;
}
