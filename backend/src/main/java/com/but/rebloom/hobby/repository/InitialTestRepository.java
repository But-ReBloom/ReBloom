package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.InitialTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InitialTestRepository extends JpaRepository<InitialTest, Long> {
    // 질문지 조회 - (SetNo + Category)
    Optional<InitialTest> findBySetNoAndCategory(int setNo, String category);
}