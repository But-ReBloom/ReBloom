package com.but.rebloom.config;

import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.common.usecase.ValidationUseCase;
import com.but.rebloom.hobby.repository.ActivityRepository;
import com.but.rebloom.hobby.usecase.HobbyValidationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    @Bean
    public ValidationUseCase validationUseCase(UserRepository userRepository, ActivityRepository activityRepository) {
        return new ValidationUseCase(userRepository, activityRepository);
    }

    @Bean
    public AuthValidationUseCase authValidationUseCase(ValidationUseCase validationUseCase, UserRepository userRepository) {
        return new AuthValidationUseCase(validationUseCase, userRepository);
    }

    @Bean
    public HobbyValidationUseCase hobbyValidationUseCase(ValidationUseCase validationUseCase, ActivityRepository activityRepository, UserRepository userRepository) {
        return new HobbyValidationUseCase(validationUseCase, activityRepository, userRepository);
    }
}
