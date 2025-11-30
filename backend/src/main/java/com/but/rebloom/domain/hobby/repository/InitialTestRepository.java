package com.but.rebloom.domain.hobby.repository;

import com.but.rebloom.domain.hobby.domain.InitialTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitialTestRepository extends JpaRepository<InitialTest, Long> {
    // 질문지 조회 - (SetNo)
    @Query("""
        select i
        from InitialTest i
        where i.initialTestSetNumber in (:setNo1, :setNo2)
    """)
    List<InitialTest> findBySetNo(
            @Param("setNo1") int setNo1,
            @Param("setNo2") int setNo2
    );
}