package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.dto.request.SendVerificationEmailRequest;
import com.but.rebloom.auth.dto.request.VerifyCodeRequest;
import com.but.rebloom.common.exception.WrongVerifiedCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailUseCase {
    // 이메일 전송용
    private JavaMailSender mailSender;
    // 이메일-인증코드 저장용
    private final Map<String, String> codeMap = new HashMap<>();

    // 생성자 DI
    public EmailUseCase(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // 인증 코드 생성
    private String generateCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    // 인증 코드 전송
    public String sendVerificationEmail(SendVerificationEmailRequest emailRequest) {
        String code = generateCode();
        codeMap.put(emailRequest.getUserEmail(), code);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailRequest.getUserEmail());
        message.setSubject("[이메일 인증 코드]");
        message.setText("ReBloom 인증코드: " + code);

        mailSender.send(message);
        return code;
    }

    // 인증 코드 검증
    public void verifyCode(VerifyCodeRequest verifyCodeRequest) {
        if (!codeMap.get(verifyCodeRequest.getEmail()).equals(verifyCodeRequest.getCode())) {
            throw new WrongVerifiedCodeException("잘못된 인증 코드");
        }
    }
}
