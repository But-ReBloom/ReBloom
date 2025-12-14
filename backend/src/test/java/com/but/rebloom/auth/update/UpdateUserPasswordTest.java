package com.but.rebloom.auth.update;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.UpdateUserPasswordRequest;
import com.but.rebloom.domain.auth.usecase.DefaultUserUseCase;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.auth.usecase.UpdateUserInfoUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UpdateUserPasswordTest {
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @Mock
    private DefaultUserUseCase defaultUserUseCase;
    @InjectMocks
    private UpdateUserInfoUseCase updateUserInfoUseCase;

    @Test
    @DisplayName("유저 아이디 수정 테스트 - 성공")
    public void updateUserIdSuccessTest() {
        // Given
        UpdateUserPasswordRequest updateUserPasswordRequest = new UpdateUserPasswordRequest(
                "testPassword123!@"
        );

        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userId("testid123")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        User updatedUser = User.builder()
                .userEmail("testemail@email.com")
                .userId("testid123")
                .userPassword("testPassword123!@")
                .userProvider(Provider.SELF)
                .build();

        when(findCurrentUserUseCase.getCurrentUser()).thenReturn(mockUser);
        when(defaultUserUseCase.updateUserPassword(mockUser.getUserEmail(),
                updateUserPasswordRequest.getUpdateUserPassword()))
                .thenReturn(updatedUser);

        // When
        User user = updateUserInfoUseCase.updateUserPassword(updateUserPasswordRequest);

        // Then
        assertThat(user.getUserPassword()).isEqualTo(updateUserPasswordRequest.getUpdateUserPassword());
    }
}
