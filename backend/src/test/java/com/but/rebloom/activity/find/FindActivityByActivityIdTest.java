package com.but.rebloom.activity.find;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import com.but.rebloom.domain.hobby.usecase.DefaultActivityUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindActivityByActivityIdTest {
    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private DefaultActivityUseCase defaultActivityUseCase;

    @Test
    @DisplayName("활동 조회 테스트 - 성공")
    public void findActivityByActivityIdSuccessTest() {
        // Given
        Long activityId = 1L;

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Activity mockActivity = Activity.builder()
                .user(mockUser)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(activityRepository.findByActivityId(activityId))
                .thenReturn(Optional.of(mockActivity));

        // When
        Activity activity = defaultActivityUseCase.findActivityByActivityId(activityId);

        // Then
        assertThat(activity).isEqualTo(mockActivity);
    }

    @Test
    @DisplayName("활동 조회 테스트 - 활동 조회 실패로 실패")
    public void findActivityByActivityIdFailByActivityNotFoundTest() {
        // Given
        Long activityId = 1L;

        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ActivityNotFoundException.class,
                () -> defaultActivityUseCase.findActivityByActivityId(activityId));
    }
}
