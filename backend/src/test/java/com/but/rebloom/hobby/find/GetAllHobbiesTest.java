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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllHobbiesTest {
    @Mock
    private HobbyRepository hobbyRepository;
    @InjectMocks
    private DefaultHobbyUseCase defaultHobbyUseCase;

    @Test
    @DisplayName("취미 전체 조회 테스트 - 성공")
    public void getAllHobbiesSuccessTest() {
        // Given
        Hobby mockHobby = Hobby.builder().build();
        List<Hobby> mockHobbies = List.of(mockHobby);

        when(hobbyRepository.findAllHobby())
                .thenReturn(mockHobbies);

        // When
        List<Hobby> hobbies = defaultHobbyUseCase.getAllHobbies();

        // Then
        assertThat(hobbies.size()).isEqualTo(mockHobbies.size());
    }

    @Test
    @DisplayName("취미 전체 조회 테스트 - 취미 조회 실패로 실패")
    public void getAllHobbiesFailByHobbyNotFoundTest() {
        // Given & When & Then
        assertThrows(HobbyNotFoundException.class,
                () -> defaultHobbyUseCase.getAllHobbies());
    }
}
