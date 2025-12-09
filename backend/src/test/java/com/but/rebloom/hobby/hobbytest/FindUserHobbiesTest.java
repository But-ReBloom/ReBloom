package com.but.rebloom.hobby.hobbytest;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.exception.ChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.ChannelRepository;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import com.but.rebloom.domain.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserHobbiesTest {
    @Mock
    private HobbyRepository hobbyRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private HobbyTestUseCase hobbyTestUseCase;

    @Test
    @DisplayName("유저 취미 탐색 테스트 - 성공")
    public void findUserHobbiesSuccessTest() {
        // Given
        UserAnswerRequest answers = new UserAnswerRequest(
                1.0,
                2.0,
                0.0,
                -1.0,
                -2.0
        );

        Hobby mockHobby1 = Hobby.builder()
                .hobbyWeightLearning(1.0)
                .hobbyWeightPlanning(2.0)
                .hobbyWeightCreativity(3.0)
                .hobbyWeightFocus(4.0)
                .hobbyWeightSocial(5.0)
                .build();
        Hobby mockHobby2 = Hobby.builder()
                .hobbyWeightLearning(1.0)
                .hobbyWeightPlanning(2.0)
                .hobbyWeightCreativity(3.0)
                .hobbyWeightFocus(4.0)
                .hobbyWeightSocial(5.0)
                .build();
        Hobby mockHobby3 = Hobby.builder()
                .hobbyWeightLearning(1.0)
                .hobbyWeightPlanning(2.0)
                .hobbyWeightCreativity(3.0)
                .hobbyWeightFocus(4.0)
                .hobbyWeightSocial(5.0)
                .build();
        List<Hobby> mockHobbies = List.of(mockHobby1, mockHobby2, mockHobby3);

        Channel mockChannel1 = Channel.builder().build();
        Channel mockChannel2 = Channel.builder().build();
        Channel mockChannel3 = Channel.builder().build();
        List<Channel> mockChannels = List.of(mockChannel1, mockChannel2, mockChannel3);

        User mockUser = User.builder()
                .userCreativityScore(0.0)
                .userLearningScore(0.0)
                .userPlanningScore(0.0)
                .userSocialScore(0.0)
                .userFocusScore(0.0)
                .scoreUpdatedAt(null)
                .build();

        when(hobbyRepository.findByCategoryAndLimit(anyString(), anyDouble(), anyInt()))
                .thenReturn(mockHobbies);
        when(channelRepository.findByChannelLinkedHobby(mockHobby1.getHobbyId()))
                .thenReturn(mockChannels);
        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(userRepository.save(any(User.class)))
                .thenReturn(mockUser);

        // When
        Map<List<HobbyScore>, List<Channel>> testResult = hobbyTestUseCase.findUserHobbies(answers);

        // Then
        assertThat(testResult).isNotNull();

        Map.Entry<List<HobbyScore>, List<Channel>> entry = testResult.entrySet().iterator().next();
        List<HobbyScore> hobbyScores = entry.getKey();
        List<Channel> channels = entry.getValue();

        assertThat(hobbyScores.size()).isEqualTo(3);
        assertThat(channels.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("유저 취미 탐색 테스트 - 취미 조회 실패로 실패")
    public void findUserHobbiesFailByHobbyNotFoundTest() {
        // Given
        UserAnswerRequest answers = new UserAnswerRequest(
                1.0,
                2.0,
                0.0,
                -1.0,
                -2.0
        );

        // When & Then
        assertThrows(NoSuchElementException.class,
                () -> hobbyTestUseCase.findUserHobbies(answers));
    }

    @Test
    @DisplayName("유저 취미 탐색 테스트 - 채널 조회 실패로 실패")
    public void findUserHobbiesFailByChannelNotFoundTest() {
        // Given
        UserAnswerRequest answers = new UserAnswerRequest(
                1.0,
                2.0,
                0.0,
                -1.0,
                -2.0
        );

        Hobby mockHobby1 = Hobby.builder()
                .hobbyWeightLearning(1.0)
                .hobbyWeightPlanning(2.0)
                .hobbyWeightCreativity(3.0)
                .hobbyWeightFocus(4.0)
                .hobbyWeightSocial(5.0)
                .build();
        Hobby mockHobby2 = Hobby.builder()
                .hobbyWeightLearning(1.0)
                .hobbyWeightPlanning(2.0)
                .hobbyWeightCreativity(3.0)
                .hobbyWeightFocus(4.0)
                .hobbyWeightSocial(5.0)
                .build();
        Hobby mockHobby3 = Hobby.builder()
                .hobbyWeightLearning(1.0)
                .hobbyWeightPlanning(2.0)
                .hobbyWeightCreativity(3.0)
                .hobbyWeightFocus(4.0)
                .hobbyWeightSocial(5.0)
                .build();
        List<Hobby> mockHobbies = List.of(mockHobby1, mockHobby2, mockHobby3);

        when(hobbyRepository.findByCategoryAndLimit(anyString(), anyDouble(), anyInt()))
                .thenReturn(mockHobbies);

        // When & Then
        assertThrows(ChannelNotFoundException.class,
                () -> hobbyTestUseCase.findUserHobbies(answers));
    }
}
