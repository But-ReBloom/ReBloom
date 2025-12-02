package com.but.rebloom.auth;

import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.UpdateUserIdRequest;
import com.but.rebloom.domain.auth.usecase.DefaultUserUseCase;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.auth.usecase.UpdateUserInfoUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserIdTest {
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
        UpdateUserIdRequest updateUserIdRequest = new UpdateUserIdRequest(
                "testid1234"
        );

        User mockUser = User.builder()
                .userEmail("testemail@email.com")
                .userId("testid123")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        User updatedUser = User.builder()
                .userEmail("testemail@email.com")
                .userId("testid1234")
                .userPassword("userPasswor123!")
                .userProvider(Provider.SELF)
                .build();

        when(findCurrentUserUseCase.getCurrentUser()).thenReturn(mockUser);
        when(defaultUserUseCase.updateUserId(mockUser.getUserEmail(),
                updateUserIdRequest.getUpdateUserId()))
                .thenReturn(updatedUser);

        // When
        User user = updateUserInfoUseCase.updateUserId(updateUserIdRequest);

        // Then
        assertThat(user.getUserId()).isEqualTo(updateUserIdRequest.getUpdateUserId());
    }
}
