package com.but.rebloom.hobby.usecase;

import com.but.rebloom.auth.exception.UserNotFoundException;
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

        // 유저 점수
        double[] userScore = {
                answers.getSocialScore(),
                answers.getLearningScore(),
                answers.getPlanningScore(),
                answers.getFocusScore(),
                answers.getCreativityScore()
        };

        // 카테고리별 존재하는 인접 값과의 거리
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

        // 정렬은 오버헤드 때문에 조건으로 처리
        double minUserClosestScore = userScore[0];
        int index = 0;
        if (minUserClosestScore > userClosestScore[1]) {
            minUserClosestScore = userClosestScore[1];
            index = 1;
        } if (minUserClosestScore > userClosestScore[2]) {
            minUserClosestScore = userClosestScore[2];
            index = 2;
        } if (minUserClosestScore > userClosestScore[3]) {
            minUserClosestScore = userClosestScore[3];
            index = 3;
        } if (minUserClosestScore > userClosestScore[4]) {
            minUserClosestScore = userClosestScore[4];
            index = 4;
        }

        List<HobbyWeight> result;
        switch (index) {
            case 0:
                result = hobbyWeightRepository.findAllByHobbyWeightSocial(userScore[0]);
                break;
            case 1:
                result = hobbyWeightRepository.findAllByHobbyWeightLearning(userScore[1]);
                break;
            case 2:
                result = hobbyWeightRepository.findAllByHobbyWeightPlanning(userScore[2]);
                break;
            case 3:
                result = hobbyWeightRepository.findAllByHobbyWeightFocus(userScore[3]);
                break;
            case 4:
                result = hobbyWeightRepository.findAllByHobbyWeightCreativity(userScore[4]);
                break;
            default:
                throw new UserNotFoundException("잘못된 index값");
        }

        HobbyScore hobbyScore1 = averageAbsoluteDistance(userScore, result.get(0));
        HobbyScore hobbyScore2 = averageAbsoluteDistance(userScore, result.get(1));
        HobbyScore hobbyScore3 = averageAbsoluteDistance(userScore, result.get(2));

        HobbyScore min = hobbyScore1, mid = hobbyScore2, max = hobbyScore3;

        // 최소값 찾기
        if (mid.getDistance() < min.getDistance()) {
            HobbyScore tmp = min;
            min = mid;
            mid = tmp;
        }
        if (max.getDistance() < min.getDistance()) {
            HobbyScore tmp = min;
            min = max;
            max = tmp;
        }

        // mid와 max 비교
        if (max.getDistance() < mid.getDistance()) {
            HobbyScore tmp = mid;
            mid = max;
            max = tmp;
        }

        // 결과
        HobbyScore hobbyScoreResult1 = min;
        HobbyScore hobbyScoreResult2 = mid;
        HobbyScore hobbyScoreResult3 = max;

        return List.of(hobbyScoreResult1, hobbyScoreResult2, hobbyScoreResult3);
    }

    // 평균 거리 탐색
    private HobbyScore averageAbsoluteDistance(double[] userVector, HobbyWeight hobby) {
        return HobbyScore.builder()
                .hobbyWeight(hobby)
                .distance((Math.abs(hobby.getHobbyWeightSocial() - userVector[0]) +
                        Math.abs(hobby.getHobbyWeightLearning() - userVector[1]) +
                        Math.abs(hobby.getHobbyWeightPlanning() - userVector[2]) +
                        Math.abs(hobby.getHobbyWeightFocus() - userVector[3]) +
                        Math.abs(hobby.getHobbyWeightCreativity() - userVector[4])) / 5.0)
                .build();
    }

    // 질문 반환
    public List<InitialTest> getQuestions() {
        Random random = new Random();
        int num = random.nextInt(10) + 1;

        return initialTestRepository.findBySetNo(num, (num + 1) % 10);
    }
}
