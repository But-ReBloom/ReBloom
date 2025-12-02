package com.but.rebloom.auth;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.SignupRequest;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.SignupUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
    public void signupTest() {
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
        Assertions.assertEquals("testuseremail@email.com", user.getUserEmail());
        Assertions.assertEquals("testuser123", user.getUserId());
        Assertions.assertEquals("testname", user.getUserName());
        Assertions.assertEquals(Provider.SELF, user.getUserProvider());
    }
}
