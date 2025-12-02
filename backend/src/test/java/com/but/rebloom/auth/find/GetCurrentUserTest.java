package com.but.rebloom.auth.find;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.jwt.JwtUtil;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCurrentUserTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private FindCurrentUserUseCase findCurrentUserUseCase;

    @Test
    @DisplayName("현재 유저 조회 테스트 - 성공")
    public void findCurrentUserSuccessTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(jwtUtil.getCurrentUserEmail()).thenReturn(mockUser.getUserEmail());
        when(userRepository.findByUserEmail(mockUser.getUserEmail()))
                .thenReturn(Optional.of(mockUser));

        // When
        User user = findCurrentUserUseCase.getCurrentUser();

        // Then
        assertThat(user).isEqualTo(mockUser);
    }

    @Test
    @DisplayName("현재 유저 조회 테스트 - 유저 조회 실패로 인한 실")
    public void findCurrentUserFailByUserNotFoundTest() {
        // Given
        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(jwtUtil.getCurrentUserEmail()).thenReturn(mockUser.getUserEmail());

        // When & Then
        assertThrows(UserNotFoundException.class,
                () -> findCurrentUserUseCase.getCurrentUser());
    }
}
