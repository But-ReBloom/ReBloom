package com.but.rebloom.domain.review.domain;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.hobby.domain.Hobby;
import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.*;
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
    @Column(name = "actr_review_id")
    private Long reviewId;

    @Column(name = "actr_review_question")
    private String reviewQuestion;

    @Column(name = "actr_review_answer")
    private String reviewAnswer;

    @Column(name = "ar_created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_email", referencedColumnName = "u_email", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_h_id", referencedColumnName = "h_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hobby hobby;
}