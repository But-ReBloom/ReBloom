package com.but.rebloom.auth;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.domain.VerificationPurpose;
import com.but.rebloom.domain.auth.dto.request.VerifyCodeRequest;
import com.but.rebloom.domain.auth.exception.WrongVerifiedCodeException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.EmailUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.when;

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
        Assertions.assertThat(user.getUserEmail())
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
        org.junit.jupiter.api.Assertions.assertThrows(WrongVerifiedCodeException.class,
                () -> emailUseCase.verifyCode(verifyCodeRequest));
    }
}
