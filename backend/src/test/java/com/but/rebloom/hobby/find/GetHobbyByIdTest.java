package com.but.rebloom.hobby.find;

import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.usecase.DefaultHobbyUseCase;
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
public class GetHobbyByIdTest {
    @Mock
    private HobbyRepository hobbyRepository;
    @InjectMocks
    private DefaultHobbyUseCase defaultHobbyUseCase;

    @Test
    @DisplayName("취미 조회 테스트 - 성공")
    public void getHobbyByIdSuccessTest() {
        // Given
        Long hobbyId = 1L;

        Hobby mockHobby = Hobby.builder().build();

        when(hobbyRepository.findByHobbyId(hobbyId))
                .thenReturn(Optional.of(mockHobby));

        // When
        Hobby hobby = defaultHobbyUseCase.getHobbyById(hobbyId);

        // Then
        assertThat(hobby).isEqualTo(mockHobby);
    }

    @Test
    @DisplayName("취미 조회 테스트 - 취미 조회 실패로 실패")
    public void getHobbyByIdFailByHobbyNotFoundTest() {
        // Given
        Long hobbyId = 1L;

        // When & Then
        assertThrows(HobbyNotFoundException.class,
                () -> defaultHobbyUseCase.getHobbyById(hobbyId));
    }
}
