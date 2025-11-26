package com.but.rebloom.domain.hobby.repository;

import com.but.rebloom.domain.hobby.domain.HobbyWeight;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class CustomHobbyWeightRepositoryImpl implements CustomHobbyWeightRepository {
    private final EntityManager entityManager;

    private static final Set<String> ALLOWED_CATEGORIES = Set.of(
            "h_w_social",
            "h_w_learning",
            "h_w_planning",
            "h_w_focus",
            "h_w_creativity"
    );

    @Override
    public List<HobbyWeight> findByCategoryAndLimit(String category, double value, int limit) {
        if (!ALLOWED_CATEGORIES.contains(category)) {
            throw new IllegalArgumentException("잘못된 카테고리");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("리미트 수는 양의 정수여야합니다.");
        }

        String sqlQuery = String.format("""
            with candidates as (
                (
                 select *
                 from hobbies
                 where %s >= :value
                 order by %s asc
                 limit :limit
                )
                union all
                (
                 select *
                 from hobbies
                 where %s < :value
                 order by %s desc
                 limit :limit
                )
            )
            select *
            from candidates
            order by abs(%s - :value) asc
            limit :limit
            """, category, category, category, category, category);

        List<HobbyWeight> results = entityManager.createNativeQuery(sqlQuery, HobbyWeight.class)
                .setParameter("value", value)
                .setParameter("limit", limit)
                .getResultList();

        return results;
    }
}
