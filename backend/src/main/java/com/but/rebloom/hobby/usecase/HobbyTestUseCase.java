package com.but.rebloom.hobby.usecase;

import com.but.rebloom.hobby.domain.HobbyWeight;
import com.but.rebloom.hobby.domain.InitialTest;
import com.but.rebloom.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.hobby.repository.HobbyWeightRepository;
import com.but.rebloom.hobby.repository.InitialTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HobbyTestUseCase {
    private final InitialTestRepository initialTestRepository;
    private final HobbyWeightRepository hobbyWeightRepository;

    public List<Map<HobbyWeight, Double>> findUserHobbies(List<UserAnswerRequest> answers) {
        double[] userVector = calculateUserVector(answers);
        return calculateRecommendations(userVector);
    }

    public double[] calculateUserVector(List<UserAnswerRequest> answers) {
        double[] userVector = new double[5];

        for (UserAnswerRequest answer : answers) {
            InitialTest test = initialTestRepository.findBySetNoAndCategory(
                    answer.getSetNo(),
                    answer.getCategory()
            );

            if (test != null) {
                userVector[0] += test.getWeight1() * answer.getAnswerValue();
                userVector[1] += test.getWeight2() * answer.getAnswerValue();
                userVector[2] += test.getWeight3() * answer.getAnswerValue();
                userVector[3] += test.getWeight4() * answer.getAnswerValue();
                userVector[4] += test.getWeight5() * answer.getAnswerValue();
            }
        }

        return userVector;
    }

    public List<Map<HobbyWeight, Double>> calculateRecommendations(double[] userVector) {
        List<HobbyWeight> hobbies = hobbyWeightRepository.findAll();

        return hobbies.stream()
                .map(h -> {
                    double distance = averageAbsoluteDistance(userVector, h);
                    Map<HobbyWeight, Double> map = new HashMap<>();
                    map.put(h, distance);
                    return map;
                })
                .sorted(Comparator.comparingDouble(m -> m.values().iterator().next()))
                .limit(3)
                .collect(Collectors.toList());
    }

    private double averageAbsoluteDistance(double[] userVector, HobbyWeight hobby) {
        double sum = 0;
        sum += Math.abs(hobby.getWeight1() - userVector[0]);
        sum += Math.abs(hobby.getWeight2() - userVector[1]);
        sum += Math.abs(hobby.getWeight3() - userVector[2]);
        sum += Math.abs(hobby.getWeight4() - userVector[3]);
        sum += Math.abs(hobby.getWeight5() - userVector[4]);
        return sum / 5.0;
    }

    public List<InitialTest> getQuestions() {
        return initialTestRepository.findAll();
    }
}
