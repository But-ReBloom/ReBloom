package com.but.rebloom.domain.hobby.domain;

import com.but.rebloom.domain.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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

    @Column(name = "act_start", nullable = false)
    @CreationTimestamp
    private LocalDate activityStart;

    @Column(name = "act_recent", nullable = false)
    @UpdateTimestamp
    @Builder.Default
    private LocalDate activityRecent = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_h_id", referencedColumnName = "h_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hobby hobby;
}