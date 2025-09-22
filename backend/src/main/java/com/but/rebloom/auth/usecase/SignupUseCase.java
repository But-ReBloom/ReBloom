package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUseCase validationUseCase;

    public void signup(SignupRequest signupRequest) {
        validationUseCase.checkNull(signupRequest);

        String userEmail = signupRequest.getUserEmail();
        String userId = signupRequest.getUserId();
        String userPassword = signupRequest.getUserPassword();
        String userName = signupRequest.getUserName();

        validationUseCase.checkUserEmail(userEmail);
        validationUseCase.checkUserId(userId);
        validationUseCase.checkUserPassword(userPassword);
        validationUseCase.checkUserName(userName);

        validationUseCase.checkExistAccount(userEmail, userId);

        User user = User.builder()
                .userEmail(userEmail)
                .userId(userId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userName(userName)
                .build();

        userRepository.save(user);
    }
}
