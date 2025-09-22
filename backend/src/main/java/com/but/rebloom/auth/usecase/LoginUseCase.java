package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.LoginRequest;
import com.but.rebloom.auth.jwt.JwtTokenProvider;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    private final UserRepository userRepository;
    private final ValidationUseCase validationUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(LoginRequest loginRequest) {
        validationUseCase.checkNull(loginRequest);

        String userEmail = loginRequest.getUserEmail();
        String userPassword = loginRequest.getUserPassword();

        validationUseCase.checkUserEmail(userEmail);
        validationUseCase.checkUserPassword(userPassword);

        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("유저가 조회되지 않음");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new UserNotFoundException("유저 조회 실패");
        }

        return jwtTokenProvider.generateToken(String.valueOf(user.getUserEmail()));
    }
}
