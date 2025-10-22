package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.jwt.JwtUtil;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCurrentUserUseCase {
    // 이메일 추출
    private final JwtUtil jwtUtil;
    // 디비 접근
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = jwtUtil.getCurrentUserEmail();

        return userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("유저 조회 실패"));
    }
}
