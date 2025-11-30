package com.but.rebloom.domain.hobby.repository;

import com.but.rebloom.domain.hobby.domain.Hobby;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomHobbyRepository {
    List<Hobby> findByCategoryAndLimit(String category, double value, int limit);
}