package com.but.rebloom.hobby.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "initial_tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitialTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "it_question_id")
    private Long initialTestId;

    @Column(name = "it_set_no", nullable = false)
    private Integer initialTestSetNumber;

    @Column(name = "it_category", nullable = false, length = 255)
    private String initialTestCategory;

    @Column(name = "it_question", nullable = false, length = 255)
    private String initialTestQuestion;

    @Column(name = "it_w_social", nullable = false)
    private Double initialTestSocialWeight;

    @Column(name = "it_w_learning", nullable = false)
    private Double initialTestLearningWeight;

    @Column(name = "it_w_planning", nullable = false)
    private Double initialTestPlanningWeight;

    @Column(name = "it_w_focus", nullable = false)
    private Double initialTestFocusWeight;

    @Column(name = "it_w_creativity", nullable = false)
    private Double initialTestCreativityWeight;
}
