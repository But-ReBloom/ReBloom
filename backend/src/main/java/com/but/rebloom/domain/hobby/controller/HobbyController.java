package com.but.rebloom.domain.hobby.controller;

import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.dto.response.GetAllHobbyResponse;
import com.but.rebloom.domain.hobby.dto.response.GetHobbyResponse;
import com.but.rebloom.domain.hobby.usecase.DefaultHobbyUseCase;
import com.but.rebloom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby")
public class HobbyController {
    // 함수 호출
    private final DefaultHobbyUseCase defaultHobbyUseCase;

    // 전체 취미 검색
    @GetMapping("/find/all")
    public ResponseEntity<ApiResponse<GetAllHobbyResponse>> findAll() {
        List<Hobby> hobbies = defaultHobbyUseCase.getAllHobbies();
        return ResponseEntity.ok(ApiResponse.success(GetAllHobbyResponse.from(hobbies)));
    }

    // 취미 검색 - 아이디
    @GetMapping("/find/{hobbyId}")
    public ResponseEntity<ApiResponse<GetHobbyResponse>> findAll(@PathVariable Long hobbyId) {
        Hobby hobby = defaultHobbyUseCase.getHobbyById(hobbyId);
        return ResponseEntity.ok(ApiResponse.success(GetHobbyResponse.from(hobby)));
    }
}
