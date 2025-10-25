package com.but.rebloom.common.usecase;

import com.but.rebloom.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.IllegalArgumentException;
import com.but.rebloom.hobby.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationUseCase {
    // 디비 이용
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    // 널 값 확인 - 한 요소
    public <T> void checkNull(T element) {
        if (element == null || element.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("빈 값이 존재");
        }
    }

    // 이메일 확인
    public void checkUserEmail(String userEmail) {
        if (!userEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("이메일 오류");
        }
    }

    // 존재하는지 확인
    public void checkExistAccount(String userEmail, String userId) {
        if (userRepository.existsByUserEmail(userEmail) ||
                userRepository.existsByUserId(userId)) {
            throw new AlreadyUsingUserException("이미 존재함");
        }
    }

    // 존재하는지 확인
    public void checkExistAccountByUserEmail(String userEmail) {
        if (userRepository.existsByUserEmail(userEmail)) {
            throw new AlreadyUsingUserException("이미 존재함");
        }
    }

    // 존재하는지 확인
    public void checkExistAccountByUserId(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new AlreadyUsingUserException("이미 존재함");
        }
    }

    // 존재하는 유저인지 확인
    public void checkNotExistAccountByUserEmail(String userEmail) {
        if (!userRepository.existsByUserEmail(userEmail)) {
            throw new AlreadyUsingUserException("존재하지 않는 이메일");
        }
    }
}
