package com.but.rebloom.global.config;

import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.global.usecase.ValidationUseCase;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyValidationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    @Bean
    public ValidationUseCase validationUseCase(UserRepository userRepository) {
        return new ValidationUseCase(userRepository);
    }

    @Bean
    public AuthValidationUseCase authValidationUseCase(ValidationUseCase validationUseCase) {
        return new AuthValidationUseCase(validationUseCase);
    }

    @Bean
    public HobbyValidationUseCase hobbyValidationUseCase(ValidationUseCase validationUseCase, ActivityRepository activityRepository) {
        return new HobbyValidationUseCase(validationUseCase, activityRepository);
    }
}
