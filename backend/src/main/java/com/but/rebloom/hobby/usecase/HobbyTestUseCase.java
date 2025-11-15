package com.but.rebloom.hobby.usecase;

import com.but.rebloom.hobby.domain.HobbyScore;
import com.but.rebloom.hobby.domain.HobbyWeight;
import com.but.rebloom.hobby.domain.InitialTest;
import com.but.rebloom.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.hobby.repository.HobbyWeightRepository;
import com.but.rebloom.hobby.repository.InitialTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HobbyTestUseCase {
    // 디비 이용
    private final InitialTestRepository initialTestRepository;
    private final HobbyWeightRepository hobbyWeightRepository;

    // 결과에 따른 취미 탐색
    public List<HobbyScore> findUserHobbies(UserAnswerRequest answers) {
        List<HobbyWeight> hobbies = hobbyWeightRepository.findAllHobbyWeight();

        double[] userScore = {
                answers.getSocialScore(),
                answers.getLearningScore(),
                answers.getPlanningScore(),
                answers.getFocusScore(),
                answers.getCreativityScore()
        };

        return hobbies.stream()
                .map(h -> new HobbyScore(h, averageAbsoluteDistance(userScore, h)))
                .sorted(Comparator.comparingDouble(HobbyScore::getDistance))
                .limit(3)
                .toList();
    }

    // 평균 거리 탐색
    private double averageAbsoluteDistance(double[] userVector, HobbyWeight hobby) {
        return (Math.abs(hobby.getHobbyWeightSocial() - userVector[0]) +
                Math.abs(hobby.getHobbyWeightLearning() - userVector[1]) +
                Math.abs(hobby.getHobbyWeightPlanning() - userVector[2]) +
                Math.abs(hobby.getHobbyWeightFocus() - userVector[3]) +
                Math.abs(hobby.getHobbyWeightCreativity() - userVector[4])) / 5.0;
    }

    // 질문 반환
    public List<InitialTest> getQuestions() {
        Random random = new Random();
        int num = random.nextInt(10) + 1;

        return initialTestRepository.findBySetNo(num, (num + 1) % 10);
    }
}
