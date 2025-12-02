package com.but.rebloom.auth;

import com.but.rebloom.domain.auth.domain.VerificationPurpose;
import com.but.rebloom.domain.auth.dto.request.SendVerificationEmailRequest;
import com.but.rebloom.global.usecase.EmailSenderUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailSendTest {
    @Mock
    private JavaMailSender javaMailSender;
    @InjectMocks
    private EmailSenderUseCase emailSenderUseCase;

    @Test
    @DisplayName("이메일 전송 테스트 - 성공")
    public void sendEmailSuccessTest() {
        // Given
        SendVerificationEmailRequest emailRequest = new SendVerificationEmailRequest(
                "testemail@email.com",
                VerificationPurpose.SIGN_UP
        );

        String to = "test@test.com";
        String subject = "알림";
        String text = "내용입니다.";

        // When
        emailSenderUseCase.sendEmail(to, subject, text);

        // Then
        verify(javaMailSender, times(1))
                .send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("이메일 전송 테스트 - 런타임으로 인한 실패")
    public void sendEmailFailByRuntimeTest() {
        // Given
        SendVerificationEmailRequest emailRequest = new SendVerificationEmailRequest(
                "testemail@email.com",
                VerificationPurpose.SIGN_UP
        );

        String to = null;
        String subject = "알림";
        String text = "내용입니다.";

        doThrow(new RuntimeException("모종의 오류"))
                .when(javaMailSender).send(any(SimpleMailMessage.class));

        // When & Then
        assertThrows(RuntimeException.class,
                () -> emailSenderUseCase.sendEmail(to, subject, text));
    }
}
