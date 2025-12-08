package com.but.rebloom.hobby.activity.find;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindActivityOrderByActivityRecentAscTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    private DefaultActivityUseCase defaultActivityUseCase;

    @Test
    @DisplayName("활동 전체 조회 테스트 - 성공")
    public void findActivityOrderByActivityRecentAscSuccessTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        Activity mockActivity = Activity.builder().build();
        List<Activity> mockActivities = List.of(mockActivity);

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(activityRepository.findByUser_UserEmailOrderByActivityRecentAsc(mockUser.getUserEmail()))
                .thenReturn(mockActivities);

        // When
        List<Activity> activities = defaultActivityUseCase.findActivityOrderByActivityRecentAsc();

        // Then
        assertThat(activities.size()).isEqualTo(mockActivities.size());
    }

    @Test
    @DisplayName("활동 전체 조회 테스트 - 활동 조회 실패로 실패")
    public void findActivityOrderByActivityRecentAscFailByActivityNotFoundTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(ActivityNotFoundException.class,
                () -> defaultActivityUseCase.findActivityOrderByActivityRecentAsc());
    }
}
