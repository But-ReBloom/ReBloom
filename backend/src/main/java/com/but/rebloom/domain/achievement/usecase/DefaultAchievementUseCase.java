package com.but.rebloom.domain.achievement.usecase;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.domain.achievement.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAchievementUseCase {
    // 디비 접근
    private final AchievementRepository achievementRepository;

    // 업적 조회 - 업적 아이디
    public Achievement findAchievementById(Long achievementId) {
        return achievementRepository.findByAchievementId(achievementId)
                .orElseThrow(() -> new AchievementNotFoundException("존재하지 않는 업적 아이디"));
    }

    // 업적 리스트 조회
    public List<Achievement> findAllAchievements() {
        List<Achievement> achievements =  achievementRepository.findAll();

        if (achievements.isEmpty()) {
            throw new AchievementNotFoundException("업적 조회 실패");
        }

        return achievements;
    }
}
