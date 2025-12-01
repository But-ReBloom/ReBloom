package com.but.rebloom.domain.hobby.usecase;

import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import com.but.rebloom.domain.hobby.domain.InitialTest;
import com.but.rebloom.domain.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.repository.InitialTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HobbyTestUseCase {
    // 디비 이용
    private final InitialTestRepository initialTestRepository;
    private final HobbyRepository hobbyRepository;
    private final ChannelRepository channelRepository;

    // 결과에 따른 취미 탐색
    public Map<List<HobbyScore>, List<Channel>> findUserHobbies(UserAnswerRequest answers) {
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
                Math.abs(hobbyRepository
                        .findByCategoryAndLimit("h_w_social", userScore[0], 1)
                                .getFirst()
                        .getHobbyWeightSocial() - userScore[0]),
                Math.abs(hobbyRepository
                        .findByCategoryAndLimit("h_w_learning", userScore[1], 1)
                        .getFirst()
                        .getHobbyWeightLearning() - userScore[1]),
                Math.abs(hobbyRepository
                        .findByCategoryAndLimit("h_w_planning", userScore[2], 1)
                        .getFirst()
                        .getHobbyWeightPlanning() - userScore[2]),
                Math.abs(hobbyRepository
                        .findByCategoryAndLimit("h_w_focus", userScore[3], 1)
                        .getFirst()
                        .getHobbyWeightFocus() - userScore[3]),
                Math.abs(hobbyRepository
                        .findByCategoryAndLimit("h_w_creativity", userScore[4], 1)
                        .getFirst()
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

        List<Hobby> result;
        switch (index) {
            case 0:
                result = hobbyRepository.findByCategoryAndLimit("h_w_social", userScore[0], 3);
                break;
            case 1:
                result = hobbyRepository.findByCategoryAndLimit("h_w_learning", userScore[1], 3);
                break;
            case 2:
                result = hobbyRepository.findByCategoryAndLimit("h_w_planning", userScore[2], 3);
                break;
            case 3:
                result = hobbyRepository.findByCategoryAndLimit("h_w_focus", userScore[3], 3);
                break;
            case 4:
                result = hobbyRepository.findByCategoryAndLimit("h_w_creativity", userScore[4], 3);
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

        List<Channel> channels = channelRepository
                .findByChannelLinkedHobby(hobbyScoreResult1.getHobby().getHobbyId());

        if (channels.isEmpty()) {
            channels = channelRepository
                    .findByChannelLinkedHobby(hobbyScoreResult2.getHobby().getHobbyId());
        } if (channels.isEmpty()) {
            channels = channelRepository
                    .findByChannelLinkedHobby(hobbyScoreResult3.getHobby().getHobbyId());
        }

        return Map.of(List.of(hobbyScoreResult1, hobbyScoreResult2, hobbyScoreResult3), channels);
    }

    // 평균 거리 탐색
    private HobbyScore averageAbsoluteDistance(double[] userVector, Hobby hobby) {
        return HobbyScore.builder()
                .hobby(hobby)
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
