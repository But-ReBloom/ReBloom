package com.but.rebloom.domain.hobby.repository;

import com.but.rebloom.domain.hobby.domain.HobbyWeight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomHobbyWeightRepository {
    List<HobbyWeight> findByCategoryAndLimit(String category, double value, int limit);
}