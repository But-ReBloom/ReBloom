package com.but.rebloom.auth;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.SignupRequest;
import com.but.rebloom.domain.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.SignupUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignupTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthValidationUseCase authValidationUseCase;
    @Mock
    private DefaultUserAchievementUseCase defaultUserAchievementUseCase;
    @InjectMocks
    private SignupUseCase signupUseCase;

    @Test
    @DisplayName("회원가입 테스트 - 성공")
    public void signupSuccessTest() {
        // Given
        SignupRequest signupRequest = new SignupRequest(
                "testuseremail@email.com",
                "testuser123",
                "testPassword123!",
                "testname",
                Provider.SELF
        );

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        doNothing().when(authValidationUseCase).checkNull(any(SignupRequest.class));
        doNothing().when(defaultUserAchievementUseCase).createDefaultUserAchievement(anyString());

        User savedUser = User.builder()
                .userEmail(signupRequest.getUserEmail())
                .userId(signupRequest.getUserId())
                .userPassword("encodedPassword")
                .userName(signupRequest.getUserName())
                .userProvider(signupRequest.getUserProvider())
                .build();
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // When
        User user = signupUseCase.signup(signupRequest);

        // Then
        assertThat(user.getUserEmail()).isEqualTo(signupRequest.getUserEmail());
        assertThat(user.getUserId()).isEqualTo(signupRequest.getUserId());
        assertThat(user.getUserName()).isEqualTo(signupRequest.getUserName());
        assertThat(user.getUserProvider()).isEqualTo(signupRequest.getUserProvider());
    }

    @Test
    @DisplayName("회원가입 테스트 - 이메일 중복 실패")
    public void signupFailByEmailTest() {
        // Given
        SignupRequest signupRequest = new SignupRequest(
                "hjbin_25@dgsw.hs.kr",
                "testuser123",
                "testPassword123!",
                "testname",
                Provider.SELF
        );

        when(userRepository.existsByUserEmail(signupRequest.getUserEmail()))
                .thenReturn(true);

        // When & Then
        assertThrows(AlreadyUsingUserException.class,
                () -> signupUseCase.signup(signupRequest));
    }
}
