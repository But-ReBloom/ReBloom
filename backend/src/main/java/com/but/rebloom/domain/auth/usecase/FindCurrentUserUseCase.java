package com.but.rebloom.domain.auth.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.jwt.JwtUtil;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
