package com.but.rebloom.hobby.activity.reset;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import com.but.rebloom.domain.hobby.usecase.DefaultActivityUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ResetActivityTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    private DefaultActivityUseCase defaultActivityUseCase;

    @Test
    @DisplayName("활동 초기화 테스트 - 성공")
    public void testResetActivitySuccessTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("user@email.com")
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        doNothing().when(activityRepository).deleteActivityByUser_UserEmail(anyString());

        // When & Then
        defaultActivityUseCase.resetActivity();
    }
}
