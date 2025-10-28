package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.HobbyWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyWeightRepository extends JpaRepository<HobbyWeight, Long> {
}