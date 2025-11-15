package com.but.rebloom.achievement.usecase;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.exception.AchievementNotFoundException;
import com.but.rebloom.achievement.repository.AchievementRepository;
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

    // 업적 조회 - 업적 제목
    public Achievement findAchievementByTitle(String achievementTitle) {
        return achievementRepository.findByAchievementTitle(achievementTitle)
                .orElseThrow(() -> new AchievementNotFoundException("존재하지 않는 업적 제목"));
    }

    // 업적 리스트 조회
    public List<Achievement> findAllAchievements() {
        return achievementRepository.findAll();
    }
}
