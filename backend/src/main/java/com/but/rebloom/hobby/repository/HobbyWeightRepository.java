package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.HobbyWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyWeightRepository extends JpaRepository<HobbyWeight, Long>, CustomHobbyWeightRepository {
    // 취미 가중치 조회 - 전체
    @Query("""
        select distinct hw
        from HobbyWeight hw
        where hw.hobbyId in (
            select min(hw.hobbyId)
            from HobbyWeight hw
            group by hw.hobbyName
        )
    """)
    List<HobbyWeight> findAllHobbyWeight();
}