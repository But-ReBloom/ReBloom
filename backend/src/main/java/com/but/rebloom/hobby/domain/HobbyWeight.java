package com.but.rebloom.hobby.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HobbyWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyId;

    private String hobbyName;
    private double weight1;
    private double weight2;
    private double weight3;
    private double weight4;
    private double weight5;
}