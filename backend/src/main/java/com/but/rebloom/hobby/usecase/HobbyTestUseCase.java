package com.but.rebloom.hobby.usecase;

import com.but.rebloom.common.exception.AuthenticationException;
import com.but.rebloom.hobby.domain.HobbyWeight;
import com.but.rebloom.hobby.domain.InitialTest;
import com.but.rebloom.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.hobby.repository.HobbyWeightRepository;
import com.but.rebloom.hobby.repository.InitialTestRepository;
import com.mysql.cj.exceptions.WrongArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HobbyTestUseCase {
    // 디비 이용
    private final InitialTestRepository initialTestRepository;
    private final HobbyWeightRepository hobbyWeightRepository;

    // 유저 취향 테스트시 관련 함수 호출 (컨트롤러에서 SRP 위배 방지)
    public List<Map<HobbyWeight, Double>> findUserHobbies(List<UserAnswerRequest> answers) {
        double[] userScore = calculateUserScore(answers);
        return calculateRecommendations(userScore);
    }

    // 취향 테스트 - 유저 결과 추출
    public double[] calculateUserScore(List<UserAnswerRequest> answers) {
        double[] userScore = new double[5];

        for (UserAnswerRequest answer : answers) {
            // JPA 함수로 추출하여 최적화
            InitialTest test = initialTestRepository.findBySetNoAndCategory(answer.getSetNo(), answer.getCategory())
                    .orElseThrow(() -> new WrongArgumentException("잘못된 입력값"));

            // 점수 계산
            if (test != null) {
                userScore[0] += test.getWeight1() * answer.getAnswerValue();
                userScore[1] += test.getWeight2() * answer.getAnswerValue();
                userScore[2] += test.getWeight3() * answer.getAnswerValue();
                userScore[3] += test.getWeight4() * answer.getAnswerValue();
                userScore[4] += test.getWeight5() * answer.getAnswerValue();
            }
        }

        return userScore;
    }

    // 결과에 따른 취미 탐색
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

    // 평균 거리 탐색
    private double averageAbsoluteDistance(double[] userVector, HobbyWeight hobby) {
        double sum = 0;
        sum += Math.abs(hobby.getWeight1() - userVector[0]);
        sum += Math.abs(hobby.getWeight2() - userVector[1]);
        sum += Math.abs(hobby.getWeight3() - userVector[2]);
        sum += Math.abs(hobby.getWeight4() - userVector[3]);
        sum += Math.abs(hobby.getWeight5() - userVector[4]);
        return sum / 5.0;
    }

    // 질문 반환
    public List<InitialTest> getQuestions() {
        Random random = new Random();
        int num = random.nextInt(10) + 1;

        return initialTestRepository.findBySetNo(num, (num + 1) % 10)
                .orElseThrow(() -> new AuthenticationException("질문 세트 번호 이상"));
    }
}
