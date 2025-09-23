package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.dto.request.SendVerificationEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailUseCase {
    private JavaMailSender mailSender;
    private final Map<String, String> codeMap = new HashMap<>();

    // 생성자 DI
    public EmailUseCase(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private String generateCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

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

    public Boolean verifyCode(String email, String inputCode) {
        if (codeMap.get(email).equals(inputCode)) {
            return true;
        } else {
            return false;
        }
    }
}
