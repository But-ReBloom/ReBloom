package com.but.rebloom.common.usecase;

import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.hobby.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationUseCase {
    // 디비 이용
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
}
