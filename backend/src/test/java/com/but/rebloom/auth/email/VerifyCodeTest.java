package com.but.rebloom.auth.email;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.domain.VerificationPurpose;
import com.but.rebloom.domain.auth.dto.request.VerifyCodeRequest;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.exception.WrongVerifiedCodeException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.EmailUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VerifyCodeTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EmailUseCase emailUseCase;

    @Test
    @DisplayName("코드 인증 테스트 - 성공")
    public void verifyCodeSuccessTest() {
        // Given
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest(
                "testemail@email.com",
                "123456",
                VerificationPurpose.SIGN_UP
        );

        EmailUseCase.CodeInfo mockCodeInfo = new EmailUseCase.CodeInfo(
                "123456",
                LocalDateTime.now()
        );

        emailUseCase
                .getCodeMap()
                .computeIfAbsent(verifyCodeRequest.getUserEmail(), k -> new HashMap<>())
                .put(VerificationPurpose.SIGN_UP, mockCodeInfo);

        User mockUser = User.builder()
                .userEmail(verifyCodeRequest.getUserEmail())
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(userRepository.findByUserEmail(verifyCodeRequest.getUserEmail()))
                .thenReturn(Optional.of(mockUser));

        // When
        User user = emailUseCase.verifyCode(verifyCodeRequest);

        // Then
        assertThat(user.getUserEmail())
                .isEqualTo(verifyCodeRequest.getUserEmail());
    }

    @Test
    @DisplayName("코드 인증 테스트 - 잘못된 인증코드로 인한 실패")
    public void verifyCodeFailByWrongVerifyCodeTest() {
        // Given
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest(
                "testemail@email.com",
                "123456",
                VerificationPurpose.SIGN_UP
        );

        EmailUseCase.CodeInfo mockCodeInfo = new EmailUseCase.CodeInfo(
                "234567",
                LocalDateTime.now()
        );

        emailUseCase
                .getCodeMap()
                .computeIfAbsent(verifyCodeRequest.getUserEmail(), k -> new HashMap<>())
                .put(VerificationPurpose.SIGN_UP, mockCodeInfo);

        // When & Then
        assertThrows(WrongVerifiedCodeException.class,
                () -> emailUseCase.verifyCode(verifyCodeRequest));
    }

    @Test
    @DisplayName("코드 인증 테스트 - 인증코드 만료로 인한 실패")
    public void verifyCodeFailByExpiredCodeTest() {
        // Given
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest(
                "testemail@email.com",
                "123456",
                VerificationPurpose.SIGN_UP
        );

        EmailUseCase.CodeInfo mockCodeInfo = new EmailUseCase.CodeInfo(
                "123456",
                LocalDateTime.now().minus(1, ChronoUnit.DAYS)
        );

        emailUseCase
                .getCodeMap()
                .computeIfAbsent(verifyCodeRequest.getUserEmail(), k -> new HashMap<>())
                .put(VerificationPurpose.SIGN_UP, mockCodeInfo);

        // When & Then
        assertThrows(WrongVerifiedCodeException.class,
                () -> emailUseCase.verifyCode(verifyCodeRequest));
    }

    @Test
    @DisplayName("코드 인증 테스트 - 유저 조회 실패로 인한 실패")
    public void verifyCodeFailByUserNotFoundTest() {
        // Given
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest(
                "testemail@email.com",
                "123456",
                VerificationPurpose.SIGN_UP
        );

        EmailUseCase.CodeInfo mockCodeInfo = new EmailUseCase.CodeInfo(
                "123456",
                LocalDateTime.now()
        );

        emailUseCase
                .getCodeMap()
                .computeIfAbsent(verifyCodeRequest.getUserEmail(), k -> new HashMap<>())
                .put(VerificationPurpose.SIGN_UP, mockCodeInfo);

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> emailUseCase.verifyCode(verifyCodeRequest));
    }
}
