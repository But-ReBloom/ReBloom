package com.but.rebloom.auth.update;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.ChangeActivityRequest;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.auth.usecase.UpdateUserInfoUseCase;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UpdateUserCurrentActivityTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UpdateUserInfoUseCase updateUserInfoUseCase;

    @Test
    @DisplayName("현재 유저 활동 변경 테스트 - 성공")
    public void updateUserCurrentActivitySuccessTest() {
        // Given
        ChangeActivityRequest changeActivityRequest = new ChangeActivityRequest(
                1000L
        );

        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userId("testid123")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        Activity mockActivity = Activity.builder()
                .user(mockUser)
                .activityId(1000L)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(activityRepository.findById(1000L))
                .thenReturn(Optional.of(mockActivity));
        when(userRepository.save(mockUser))
                .thenReturn(mockUser);

        // When
        User user = updateUserInfoUseCase.updateUserCurrentActivity(changeActivityRequest);

        // Then
        assertThat(user.getUserCurrentActivity()).isEqualTo(mockActivity);
    }

    @Test
    @DisplayName("현재 유저 활동 변경 테스트 - 활동 조회 실패로 인한 실패")
    public void updateUserCurrentActivityFailByActivityNotFoundTest() {
        // Given
        ChangeActivityRequest changeActivityRequest = new ChangeActivityRequest(
                1000L
        );

        // When & Then
        assertThrows(ActivityNotFoundException.class,
                () -> updateUserInfoUseCase.updateUserCurrentActivity(changeActivityRequest));
    }
}
