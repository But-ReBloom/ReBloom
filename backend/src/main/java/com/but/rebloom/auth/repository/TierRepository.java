package com.but.rebloom.auth.repository;

import com.but.rebloom.auth.domain.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long> {
    // tierPoint에 따른 티어 확인
    @Query("""
        select t.tierName
        from Tier t
        where t.tierMinPoint <= :tierPoint and t.tierMaxPoint >= :tierPoint
    """)
    Optional<String> searchTierByTierPoint(@Param("tierPoint") Integer tierPoint);
}
