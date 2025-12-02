package com.but.rebloom.auth;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.FindEmailRequest;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.FindUserInfoUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserEmailByIdAndPwTest {
    @Mock
    private AuthValidationUseCase authValidationUseCase;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private FindUserInfoUseCase findUserInfoUseCase;

    @Test
    @DisplayName("아이디, 비번을 통한 이메일 조회 - 성공")
    public void findUserEmailByIdAndPwSuccessTest() {
        // Given
        FindEmailRequest findEmailRequest = new FindEmailRequest(
                "testid123",
                "testPassword123!"
        );

        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        doNothing().when(authValidationUseCase).checkUserId(findEmailRequest.getUserId());
        doNothing().when(authValidationUseCase).checkUserPassword(findEmailRequest.getUserPassword());
        when(userRepository.findByUserId(findEmailRequest.getUserId()))
                .thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(findEmailRequest.getUserPassword(), mockUser.getUserPassword()))
                .thenReturn(true);

        // When
        User user = findUserInfoUseCase.findUserEmailByIdAndPw(findEmailRequest);

        // Then
        Assertions.assertThat(user.getUserEmail()).isEqualTo("testemail@email.com");
    }
}
