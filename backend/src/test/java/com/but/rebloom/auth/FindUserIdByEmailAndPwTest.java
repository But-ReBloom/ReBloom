package com.but.rebloom.auth;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.FindIdRequest;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.FindUserInfoUseCase;
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
public class FindUserIdByEmailAndPwTest {
    @Mock
    private AuthValidationUseCase authValidationUseCase;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private FindUserInfoUseCase findUserInfoUseCase;

    @Test
    @DisplayName("이메일, 비번을 통한 아이디 조회 - 성공")
    public void findUserIdByEmailAndPwSuccessTest() {
        // Given
        FindIdRequest findIdRequest = new FindIdRequest(
                "testemail@email.com",
                "testPassword123!"
        );

        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userId("testid123")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        doNothing().when(authValidationUseCase).checkUserEmail(findIdRequest.getUserEmail());
        doNothing().when(authValidationUseCase).checkUserPassword(findIdRequest.getUserPassword());
        when(userRepository.findByUserEmail(findIdRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(findIdRequest.getUserPassword(), mockUser.getUserPassword()))
                .thenReturn(true);

        // When
        User user = findUserInfoUseCase.findUserIdByEmailAndPw(findIdRequest);

        // Then
        assertThat(user.getUserId()).isEqualTo("testid123");
    }

    @Test
    @DisplayName("아이디, 비번을 통한 이메일 조회 - 유저 조회 실패로 인한 실패")
    public void findUserIdByEmailAndPwFailByUserNotFoundTest() {
        // Given
        FindIdRequest findIdRequest = new FindIdRequest(
                "testemail@email.com",
                "testPassword123!"
        );

        doNothing().when(authValidationUseCase).checkUserEmail(findIdRequest.getUserEmail());
        doNothing().when(authValidationUseCase).checkUserPassword(findIdRequest.getUserPassword());

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> findUserInfoUseCase.findUserIdByEmailAndPw(findIdRequest));
    }
}
