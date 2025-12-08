package com.but.rebloom.userchannel.find;

import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.exception.UserChannelNotFoundException;
import com.but.rebloom.domain.channel.repository.UserChannelRepository;
import com.but.rebloom.domain.channel.usecase.VerifyUserUseCase;
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
public class GetApplyUsersByUserEmailAndChannelIdTest {
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @DisplayName("유저의 신청 채널 조회 테스트 - 성공")
    public void getApplyUsersByUserEmailAndChannelIdSuccessTest() {
        // Given
        String userEmail = "test@test.com";
        Long channelId = 1L;

        UserChannel mockUserChannel = UserChannel.builder().build();

        when(userChannelRepository.findByChannel_ChannelIdAndUser_UserEmail(channelId, userEmail))
                .thenReturn(Optional.of(mockUserChannel));

        // When
        UserChannel userChannel = verifyUserUseCase.getApplyUsersByUserEmailAndChannelId(userEmail, channelId);

        // Then
        assertThat(userChannel).isEqualTo(mockUserChannel);
    }

    @Test
    @DisplayName("유저의 신청 채널 조회 테스트 - 유저 채널 조회 실패로 실패")
    public void getApplyUsersByUserEmailAndChannelIdFailByUserChannelNotFoundTest() {
        // Given
        String userEmail = "test@test.com";
        Long channelId = 1L;

        // When & Then
        assertThrows(UserChannelNotFoundException.class,
                () -> verifyUserUseCase.getApplyUsersByUserEmailAndChannelId(userEmail, channelId));
    }
}
