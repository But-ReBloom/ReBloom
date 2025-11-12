package com.but.rebloom.hobby.domain;

import com.but.rebloom.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id", nullable = false)
    private Long activityId;

    @Column(name = "fk_u_email", nullable = false, unique = true, length = 100,
            insertable = false, updatable = false)
    private String userEmail;

    @Column(name = "act_name", nullable = false, length = 50)
    private String activityName;

    @Column(name = "act_start", nullable = false)
    private LocalDate activityStart;

    @Column(name = "act_recent", nullable = false)
    @UpdateTimestamp
    @Builder.Default
    private LocalDate activityRecent = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}