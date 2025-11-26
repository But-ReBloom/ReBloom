package com.but.rebloom.domain.auth.usecase;

import com.but.rebloom.domain.auth.domain.TierName;
import com.but.rebloom.domain.auth.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultTierUseCase {
    // 디비 접근
    private final TierRepository tierRepository;

    // enum 타입으로 티어명 반환
    public Optional<TierName> getTierEnumByPoint(Integer point) {
        return tierRepository.searchTierByTierPoint(point)
                .map(TierName::valueOf);
    }
}
