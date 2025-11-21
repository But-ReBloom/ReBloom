package com.but.rebloom.hobby.usecase;

import com.but.rebloom.hobby.domain.HobbyScore;
import com.but.rebloom.hobby.domain.HobbyWeight;
import com.but.rebloom.hobby.domain.InitialTest;
import com.but.rebloom.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.hobby.exception.HobbyNotFoundException;
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

        double[] userClosestScore = {
                Math.abs(hobbyWeightRepository.findByHobbyWeightSocial(userScore[0])
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"))
                        .getHobbyWeightSocial() - userScore[0]),
                Math.abs(hobbyWeightRepository.findByHobbyWeightLearning(userScore[1])
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"))
                        .getHobbyWeightLearning() - userScore[1]),
                Math.abs(hobbyWeightRepository.findByHobbyWeightPlanning(userScore[2])
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"))
                        .getHobbyWeightPlanning() - userScore[2]),
                Math.abs(hobbyWeightRepository.findByHobbyWeightFocus(userScore[3])
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"))
                        .getHobbyWeightFocus() - userScore[3]),
                Math.abs(hobbyWeightRepository.findByHobbyWeightCreativity(userScore[4])
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"))
                        .getHobbyWeightCreativity() - userScore[4])
        };

        double minUserClosestScore = userScore[0];
        int index = 0;
        if (minUserClosestScore > userClosestScore[1]) {
            minUserClosestScore = userClosestScore[1];
            index = 1;
        } else if (minUserClosestScore < userClosestScore[2]) {
            minUserClosestScore = userClosestScore[2];
            index = 2;
        } else if (minUserClosestScore < userClosestScore[3]) {
            minUserClosestScore = userClosestScore[3];
            index = 3;
        } else if (minUserClosestScore < userClosestScore[4]) {
            minUserClosestScore = userClosestScore[4];
            index = 4;
        }

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
