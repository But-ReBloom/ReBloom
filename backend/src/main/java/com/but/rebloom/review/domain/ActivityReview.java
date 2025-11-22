package com.but.rebloom.review.domain;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.hobby.domain.HobbyWeight;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activity_reviews")
public class ActivityReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ar_id")
    private Long reviewId;

    @Column(name = "fk_u_email", nullable = false)
    private String userEmail;

    @Column(name = "fk_h_id", nullable = false)
    private Long hobbyId;

    @Column(name = "ar_content", columnDefinition = "TEXT")
    private String reviewContent;

    // 취향 벡터 변화량
    @Column(name = "ar_social_delta", nullable = false)
    private Double socialDelta;

    @Column(name = "ar_learning_delta", nullable = false)
    private Double learningDelta;

    @Column(name = "ar_planning_delta", nullable = false)
    private Double planningDelta;

    @Column(name = "ar_focus_delta", nullable = false)
    private Double focusDelta;

    @Column(name = "ar_creativity_delta", nullable = false)
    private Double creativityDelta;

    @Column(name = "ar_created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_h_id", referencedColumnName = "h_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HobbyWeight hobby;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
