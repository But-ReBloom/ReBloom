package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.HobbyWeight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomHobbyWeightRepository {
    List<HobbyWeight> findByCategoryAndLimit(String category, double value, int limit);
}