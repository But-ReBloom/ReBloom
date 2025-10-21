package com.but.rebloom.hobby.domain;

import com.but.rebloom.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "activity_name", nullable = false)
    private String activityName;

    @Column(name = "activity_start", nullable = false)
    @CreationTimestamp
    private LocalDate activityStart;

    @Column(name = "activity_recent", nullable = false)
    @UpdateTimestamp
    private LocalDate activityRecent;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email",
                insertable = false, updatable = false)
    private User user;

    @Column(name = "email", nullable = false)
    private String userEmail;
}
