package com.but.rebloom.domain.hobby.repository;

import com.but.rebloom.domain.hobby.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long>, CustomHobbyRepository {
    // 취미 가중치 조회 - 전체
    @Query("""
        select distinct h
        from Hobby h
        where h.hobbyId in (
            select min(h.hobbyId)
            from Hobby h
            group by h.hobbyName
        )
    """)
    List<Hobby> findAllHobby();

    Optional<Hobby> findByHobbyId(Long hobbyId);
}