package com.but.rebloom.hobby.hobbytest;

import com.but.rebloom.domain.hobby.domain.InitialTest;
import com.but.rebloom.domain.hobby.exception.InitialTestNotFoundException;
import com.but.rebloom.domain.hobby.repository.InitialTestRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetQuestionsTest {
    @Mock
    private InitialTestRepository initialTestRepository;
    @InjectMocks
    private HobbyTestUseCase hobbyTestUseCase;

    @Test
    @DisplayName("질문지 조회 테스트 - 성공")
    public void getQuestionsSuccessTest() {
        // Given
        InitialTest mockInitialTest = InitialTest.builder().build();
        List<InitialTest> mockInitialTests = List.of(mockInitialTest);

        when(initialTestRepository.findBySetNo(anyInt(), anyInt()))
                .thenReturn(mockInitialTests);

        // When
        List<InitialTest> initialTests = hobbyTestUseCase.getQuestions();

        // Then
        assertThat(initialTests.size()).isEqualTo(mockInitialTests.size());
    }

    @Test
    @DisplayName("질문지 조회 테스트 - 초기 테스트 조회 실패로 실패")
    public void getQuestionsFailByInitialTestNotFoundTest() {
        // Given & When & Then
        assertThrows(InitialTestNotFoundException.class,
                () -> hobbyTestUseCase.getQuestions());
    }
}
