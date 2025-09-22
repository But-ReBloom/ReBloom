package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
}
