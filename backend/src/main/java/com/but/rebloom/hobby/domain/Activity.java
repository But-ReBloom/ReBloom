package com.but.rebloom.hobby.domain;

import com.but.rebloom.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.Query;

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
    private Long actId;

    @Column(name = "fk_u_email", nullable = false, unique = true, length = 100)
    private String userEmail;

    @Column(name = "act_name", nullable = false, length = 50)
    private String actName;

    @Column(name = "act_start", nullable = false)
    private LocalDate actStart;

    @Column(name = "act_recent", nullable = false)
    private LocalDate actRecent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
