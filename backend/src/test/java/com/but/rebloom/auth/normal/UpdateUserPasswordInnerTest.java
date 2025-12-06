package com.but.rebloom.auth.normal;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.DefaultUserUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserPasswordInnerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthValidationUseCase authValidationUseCase;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private DefaultUserUseCase defaultUserUseCase;

    @Test
    @DisplayName("유저 비밀번호 수정 테스트 - 성공")
    public void updateUserPasswordSuccessTest() {
        // Given
        String userEmail = "testemail@email.com";
        String userNewPassword = "testnewpassword";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userId("userId")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(passwordEncoder.encode(userNewPassword))
                .thenReturn(userNewPassword);
        doNothing().when(authValidationUseCase).checkUserPassword(userNewPassword);
        when(userRepository.findByUserEmail(userEmail))
                .thenReturn(Optional.of(mockUser));

        // When
        User user = defaultUserUseCase.updateUserPassword(userEmail, userNewPassword);

        // Then
        assertThat(user.getUserPassword()).isEqualTo(userNewPassword);
    }

    @Test
    @DisplayName("유저 비밀번호 수정 테스트 - 유저 조회 실패로 인한 실패")
    public void updateUserPasswordFailByUserNotFoundTest() {
        // Given
        String userEmail = "testemail@email.com";
        String userNewPassword = "testnewpassword";

        doNothing().when(authValidationUseCase).checkUserPassword(userNewPassword);

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> defaultUserUseCase.updateUserPassword(userEmail, userNewPassword));
    }
}
