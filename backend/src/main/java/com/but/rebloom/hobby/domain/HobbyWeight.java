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
@Table(name = "hobby_weights")
public class HobbyWeight {
    @Id
    @Column(name = "fk_u_id", nullable = false, unique = true, length = 255)
    private String userId;

    @Column(name = "hw_social", nullable = false)
    private Double hobbyWeightSocial;

    @Column(name = "hw_learning", nullable = false)
    private Double hobbyWeightLearning;

    @Column(name = "hw_planning", nullable = false)
    private Double hobbyWeightPlanning;

    @Column(name = "hw_focus", nullable = false)
    private Double hobbyWeightFocus;

    @Column(name = "hw_creativity", nullable = false)
    private Double hobbyWeightCreativity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_id", referencedColumnName = "u_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
