package com.but.rebloom.hobby.activity.create;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.usecase.DefaultActivityUseCase;
import com.but.rebloom.domain.hobby.usecase.HobbyValidationUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddActivityTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private HobbyRepository hobbyRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private HobbyValidationUseCase hobbyValidationUseCase;
    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    private DefaultActivityUseCase defaultActivityUseCase;

    @Test
    @DisplayName("활동 추가 테스트 - 성공")
    public void addActivitySuccessTest() {
        // Given
        AddActivityRequest addActivityRequest = new AddActivityRequest(
                1L,
                "useremail@email.com"
        );

        User mockUser = User.builder()
                .userEmail(addActivityRequest.getUserEmail())
                .build();

        Hobby mockHobby = Hobby.builder()
                .hobbyId(addActivityRequest.getHobbyId())
                .build();

        Activity mockActivity = Activity.builder()
                .hobby(mockHobby)
                .user(mockUser)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        doNothing().when(hobbyValidationUseCase)
                .checkExistActivityByEmailAndHobby_HobbyId(addActivityRequest.getUserEmail(), addActivityRequest.getHobbyId());
        when(hobbyRepository.findByHobbyId(addActivityRequest.getHobbyId()))
                .thenReturn(Optional.of(mockHobby));
        when(userRepository.findByUserEmail(addActivityRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(activityRepository.save(any(Activity.class)))
                .thenReturn(mockActivity);

        // When
        Activity activity = defaultActivityUseCase.addActivity(addActivityRequest);

        // Then
        assertThat(activity).isEqualTo(mockActivity);
    }

    @Test
    @DisplayName("활동 추가 테스트 - 취미 조회 실패로 실패")
    public void addActivityFailByHobbyNotFoundTest() {
        // Given
        AddActivityRequest addActivityRequest = new AddActivityRequest(
                1L,
                "useremail@email.com"
        );

        User mockUser = User.builder()
                .userEmail(addActivityRequest.getUserEmail())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        doNothing().when(hobbyValidationUseCase)
                .checkExistActivityByEmailAndHobby_HobbyId(addActivityRequest.getUserEmail(), addActivityRequest.getHobbyId());

        // When & Then
        assertThrows(HobbyNotFoundException.class,
                () -> defaultActivityUseCase.addActivity(addActivityRequest));
    }

    @Test
    @DisplayName("활동 추가 테스트 - 유저 조회 실패로 실패")
    public void addActivityFailByUserNotFoundTest() {
        // Given
        AddActivityRequest addActivityRequest = new AddActivityRequest(
                1L,
                "useremail@email.com"
        );

        User mockUser = User.builder()
                .userEmail(addActivityRequest.getUserEmail())
                .build();

        Hobby mockHobby = Hobby.builder()
                .hobbyId(addActivityRequest.getHobbyId())
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        doNothing().when(hobbyValidationUseCase)
                .checkExistActivityByEmailAndHobby_HobbyId(addActivityRequest.getUserEmail(), addActivityRequest.getHobbyId());
        when(hobbyRepository.findByHobbyId(addActivityRequest.getHobbyId()))
                .thenReturn(Optional.of(mockHobby));

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> defaultActivityUseCase.addActivity(addActivityRequest));
    }
}
