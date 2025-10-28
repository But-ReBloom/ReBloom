package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.InitialTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InitialTestRepository extends JpaRepository<InitialTest, Long> {
    // 질문지 조회 - (SetNo)
    @Query("SELECT initialTest FROM InitialTest initialTest " +
            "WHERE initialTest.setNo = :setNo1 OR initialTest.setNo = :setNo2")
    Optional<List<InitialTest>> findBySetNo(
            @Param("setNo1") int setNo1,
            @Param("setNo2") int setNo2
    );
    // 질문지 조회 - (SetNo + Category)
    Optional<InitialTest> findBySetNoAndCategory(int setNo, String category);
}