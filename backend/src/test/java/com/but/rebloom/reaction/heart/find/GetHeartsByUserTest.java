package com.but.rebloom.reaction.heart.find;

import com.but.rebloom.domain.reaction.domain.Heart;
import com.but.rebloom.domain.reaction.exception.HeartNotFoundException;
import com.but.rebloom.domain.reaction.repository.HeartRepository;
import com.but.rebloom.domain.reaction.usecase.HeartUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetHeartsByUserTest {
    @Mock
    private HeartRepository heartRepository;
    @InjectMocks
    private HeartUseCase heartUseCase;

    @Test
    @DisplayName("하트 조회 테스트 - 성공")
    public void getHeartsByUserSuccessTest() {
        // Given
        String userId = "userid";

        Heart mockHeart = Heart.builder().build();
        List<Heart> mockHearts = List.of(mockHeart);

        when(heartRepository.findByUser_UserId(userId))
                .thenReturn(List.of(mockHeart));

        // When
        List<Heart> hearts = heartUseCase.getHeartsByUser(userId);

        // Then
        assertThat(hearts.size()).isEqualTo(mockHearts.size());
    }

    @Test
    @DisplayName("하트 조회 테스트 - 하트 조회 실패로 인한 실패")
    public void getHeartsByUserFailByHeartNotFoundTest() {
        // Given
        String userId = "userid";

        // When & Then
        assertThrows(HeartNotFoundException.class,
                () -> heartUseCase.getHeartsByUser(userId));
    }
}
