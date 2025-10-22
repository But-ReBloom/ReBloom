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
public class  InitialTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "set_no", nullable = false)
    private int setNo;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "weight1", nullable = false)
    private double weight1;

    @Column(name = "weight2", nullable = false)
    private double weight2;

    @Column(name = "weight3")
    private double weight3;

    @Column(name = "weight4")
    private double weight4;

    @Column(name = "weight5")
    private double weight5;
}