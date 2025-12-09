package com.but.rebloom.channel.userchannel.find;

import com.but.rebloom.domain.auth.domain.Role;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.domain.VerifyStatus;
import com.but.rebloom.domain.channel.exception.UserChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.channel.usecase.VerifyUserUseCase;
import com.but.rebloom.global.exception.NoAuthorityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetApplyUsersByUserEmailTest {
    @Mock
    private UserChannelRepository userChannelRepository;
    @Mock
    private FindCurrentUserUseCase findCurrentUserUseCase;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("유저의 신청 목록 조회 테스트 - 성공")
    public void getApplyUsersByUserEmailSuccessTest() {
        // Given
        String userEmail = "test@test.com";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userRole(Role.USER)
                .build();

        UserChannel mockUserChannel = UserChannel.builder()
                .user(mockUser)
                .userChannelVerifyStatus(VerifyStatus.WAITING)
                .build();
        List<UserChannel> mockUserChannels = List.of(mockUserChannel);

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);
        when(userChannelRepository.findByUser_UserEmailAndUserChannelVerifyStatus(userEmail, VerifyStatus.WAITING))
                .thenReturn(mockUserChannels);

        // When
        List<UserChannel> userChannels = verifyUserUseCase.getApplyUsersByUserEmail(userEmail);

        // Then
        assertThat(userChannels.size()).isEqualTo(mockUserChannels.size());
    }

    @Test
    @DisplayName("유저의 신청 목록 조회 테스트 - 권한 부족으로 실패")
    public void getApplyUsersByUserEmailFailByNoAuthorityTest() {
        // Given
        String userEmail = "test@test.com";

        User mockUser = User.builder()
                .userEmail("user@test.com")
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(NoAuthorityException.class,
                () -> verifyUserUseCase.getApplyUsersByUserEmail(userEmail));
    }

    @Test
    @DisplayName("유저의 신청 목록 조회 테스트 - 유저 채널 조회 실패로 실패")
    public void getApplyUsersByUserEmailFailByUserChannelNotFoundTest() {
        // Given
        String userEmail = "test@test.com";

        User mockUser = User.builder()
                .userEmail(userEmail)
                .userRole(Role.USER)
                .build();

        when(findCurrentUserUseCase.getCurrentUser())
                .thenReturn(mockUser);

        // When & Then
        assertThrows(UserChannelNotFoundException.class,
                () -> verifyUserUseCase.getApplyUsersByUserEmail(userEmail));
    }
}
