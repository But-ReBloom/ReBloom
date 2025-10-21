package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.InitialTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialTestRepository extends JpaRepository<InitialTest, Long> {
    InitialTest findBySetNoAndCategory(int setNo, String category);
}