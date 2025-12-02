package com.but.rebloom.auth.normal;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.AuthValidationUseCase;
import com.but.rebloom.domain.auth.usecase.DefaultUserUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserIdInnerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthValidationUseCase authValidationUseCase;
    @InjectMocks
    private DefaultUserUseCase defaultUserUseCase;

    @Test
    @DisplayName("유저 아이디 수정 테스트 - 성공")
    public void updateUserIdSuccessTest() {
        // Given
        String userEmail = "testemail@email.com";
        String userNewId = "testnewid";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userId("userId")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        doNothing().when(authValidationUseCase).checkUserId(userNewId);
        when(userRepository.existsByUserId(userNewId))
                .thenReturn(false);
        when(userRepository.findByUserEmail(userEmail))
                .thenReturn(Optional.of(mockUser));

        // When
        User user = defaultUserUseCase.updateUserId(userEmail, userNewId);

        // Then
        assertThat(user.getUserId()).isEqualTo(userNewId);
    }

    @Test
    @DisplayName("유저 아이디 수정 테스트 - 성공")
    public void updateUserIdFailByAlreadyUsingUserTest() {
        // Given
        String userEmail = "testemail@email.com";
        String userNewId = "testnewid";

        doNothing().when(authValidationUseCase).checkUserId(userNewId);
        when(userRepository.existsByUserId(anyString()))
                .thenReturn(true);

        // When & Then
        assertThrows(AlreadyUsingUserException.class,
                () -> defaultUserUseCase.updateUserId(userEmail, userNewId));
    }
}
