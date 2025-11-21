package com.but.rebloom.hobby.repository;

import com.but.rebloom.hobby.domain.HobbyWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyWeightRepository extends JpaRepository<HobbyWeight, Long> {
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

    // 취미 가중치 조회 - Social 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightSocial >= :h_w_social
                order by hobbyWeightSocial asc
                limit 1
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightSocial < :h_w_social
                order by hobbyWeightSocial desc
                limit 1
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightSocial - :h_w_social) asc
        limit 1
    """, nativeQuery = true)
    Optional<HobbyWeight> findByHobbyWeightSocial(@Param("h_w_social") double hobbyWeightSocial);

    // 취미 가중치 조회 - Learning 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightLearning >= :h_w_learning
                order by hobbyWeightLearning asc
                limit 1
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightLearning < :h_w_learning
                order by hobbyWeightLearning desc
                limit 1
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightLearning - :h_w_learning) asc
        limit 1
    """, nativeQuery = true)
    Optional<HobbyWeight> findByHobbyWeightLearning(@Param("h_w_learning") double hobbyWeightLearning);

    // 취미 가중치 조회 - Planning 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightPlanning >= :h_w_planning
                order by hobbyWeightPlanning asc
                limit 1
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightPlanning < :h_w_planning
                order by hobbyWeightPlanning desc
                limit 1
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightPlanning - :h_w_planning) asc
        limit 1
    """, nativeQuery = true)
    Optional<HobbyWeight> findByHobbyWeightPlanning(@Param("h_w_planning") double hobbyWeightPlanning);

    // 취미 가중치 조회 - Focus 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightFocus >= :h_w_focus
                order by hobbyWeightFocus asc
                limit 1
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightFocus < :h_w_focus
                order by hobbyWeightFocus desc
                limit 1
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightFocus - :h_w_focus) asc
        limit 1
    """, nativeQuery = true)
    Optional<HobbyWeight> findByHobbyWeightFocus(@Param("h_w_focus") double hobbyWeightFocus);

    // 취미 가중치 조회 - Creativity 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightCreativity >= :h_w_creativity
                order by hobbyWeightCreativity asc
                limit 1
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightCreativity < :h_w_creativity
                order by hobbyWeightCreativity desc
                limit 1
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightCreativity - :h_w_creativity) asc
        limit 1
    """, nativeQuery = true)
    Optional<HobbyWeight> findByHobbyWeightCreativity(@Param("h_w_creativity") double hobbyWeightCreativity);

    // 취미 가중치 조회 - Social 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightSocial >= :h_w_social
                order by hobbyWeightSocial asc
                limit 3
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightSocial < :h_w_social
                order by hobbyWeightSocial desc
                limit 3
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightSocial - :h_w_social) asc
        limit 3
    """, nativeQuery = true)
    List<HobbyWeight> findAllByHobbyWeightSocial(@Param("h_w_social") double hobbyWeightSocial);

    // 취미 가중치 조회 - Learning 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightLearning >= :h_w_learning
                order by hobbyWeightLearning asc
                limit 3
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightLearning < :h_w_learning
                order by hobbyWeightLearning desc
                limit 3
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightLearning - :h_w_learning) asc
        limit 3
    """, nativeQuery = true)
    List<HobbyWeight> findAllByHobbyWeightLearning(@Param("h_w_learning") double hobbyWeightLearning);

    // 취미 가중치 조회 - Planning 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightPlanning >= :h_w_planning
                order by hobbyWeightPlanning asc
                limit 3
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightPlanning < :h_w_planning
                order by hobbyWeightPlanning desc
                limit 3
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightPlanning - :h_w_planning) asc
        limit 3
    """, nativeQuery = true)
    List<HobbyWeight> findAllByHobbyWeightPlanning(@Param("h_w_planning") double hobbyWeightPlanning);

    // 취미 가중치 조회 - Focus 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightFocus >= :h_w_focus
                order by hobbyWeightFocus asc
                limit 3
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightFocus < :h_w_focus
                order by hobbyWeightFocus desc
                limit 3
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightFocus - :h_w_focus) asc
        limit 3
    """, nativeQuery = true)
    List<HobbyWeight> findAllByHobbyWeightFocus(@Param("h_w_focus") double hobbyWeightFocus);

    // 취미 가중치 조회 - Creativity 기준
    @Query(value = """
        with candidates as (
            (
                select *
                from HobbyWeight
                where hobbyWeightCreativity >= :h_w_creativity
                order by hobbyWeightCreativity asc
                limit 3
            )
            union all
            (
                select *
                from HobbyWeight
                where hobbyWeightCreativity < :h_w_creativity
                order by hobbyWeightCreativity desc
                limit 3
            )
        )
        select *
        from candidates
        order by abs(hobbyWeightCreativity - :h_w_creativity) asc
        limit 3
    """, nativeQuery = true)
    List<HobbyWeight> findAllByHobbyWeightCreativity(@Param("h_w_creativity") double hobbyWeightCreativity);
}