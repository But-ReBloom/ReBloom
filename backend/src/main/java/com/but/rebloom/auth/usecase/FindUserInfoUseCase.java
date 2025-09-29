package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.FindEmailRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserInfoUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 예외 호출
    private final ValidationUseCase validationUseCase;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;

    // 이메일 찾기
    public User findUserIdByIdAndPw(FindEmailRequest findEmailRequest) {
        String userId = findEmailRequest.getUserId();
        String userPassword = findEmailRequest.getPassword();

        validationUseCase.checkUserId(userId);
        validationUseCase.checkUserPassword(userPassword);

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        User user = optionalUser.orElseThrow(() ->
                new UserNotFoundException("이메일이 조회되지 않음"));

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new UserNotFoundException("유저가 조회되지 않음");
        }

        return user;
    }
}
