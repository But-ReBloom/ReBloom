package com.but.rebloom.auth.login;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.LoginRequest;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.LoginUseCase;
import com.but.rebloom.global.exception.IllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class LoginTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthValidationUseCase authValidationUseCase;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;
    @InjectMocks
    private LoginUseCase loginUseCase;

    @Test
    @DisplayName("로그인 테스트 - 성공")
    public void loginSuccessTest() {
        // Given
        LoginRequest loginRequest = new LoginRequest(
                "testemail@email.com",
                "userPasswor123!",
                Provider.SELF
        );

        doNothing().when(authValidationUseCase).checkNull(any(LoginRequest.class));
        doNothing().when(authValidationUseCase).checkUserEmail(anyString());
        doNothing().when(authValidationUseCase).checkUserPassword(anyString());
        doNothing().when(defaultUserAchievementUseCase).updateUserAchievementProgress(anyString(), anyString(), any(Float.class));

        User mockUser = User.builder()
                .userEmail(loginRequest.getUserEmail())
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(userRepository.findByUserEmail(loginRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(loginRequest.getUserPassword(), mockUser.getUserPassword())).thenReturn(true);

        // When
        User user = loginUseCase.login(loginRequest);

        // Then
        assertThat(user.getUserEmail()).isEqualTo(loginRequest.getUserEmail());
        assertThat(user.getUserProvider()).isEqualTo(loginRequest.getUserProvider());
        assertThat(user.getUserStreak()).isEqualTo(1);
    }

    @Test
    @DisplayName("로그인 테스트 - 존재하지 않는 유저로 인한 실패")
    public void loginFailByNotFoundUserTest() {
        // Given
        LoginRequest loginRequest = new LoginRequest(
                "testemail@email.com",
                "userPasswor123!",
                Provider.SELF
        );

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> loginUseCase.login(loginRequest));
    }

    @Test
    @DisplayName("로그인 테스트 - 잘못된 로그인 환경으로 인한 실패")
    public void loginFailByWrongLoginSituationTest() {

        // Given
        LoginRequest loginRequest = new LoginRequest(
                "testemail@email.com",
                "userPasswor123!",
                Provider.DODAM
        );

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> loginUseCase.login(loginRequest));
    }
}
