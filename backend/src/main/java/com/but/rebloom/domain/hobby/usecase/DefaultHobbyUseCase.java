package com.but.rebloom.domain.hobby.usecase;

import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultHobbyUseCase {
    // 디비 접근
    private final HobbyRepository hobbyRepository;

    // 전체 취미 조회
    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAllHobby();
    }

    // 취미 조회 - 아이디
    public Hobby getHobbyById(Long hobbyId) {
        return hobbyRepository.findById(hobbyId)
                .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"));
    }
}
