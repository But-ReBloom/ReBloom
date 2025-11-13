package com.but.rebloom.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class ApiResponse<T> {
    private T data;

    @NotNull(message = "성공여부는 필수 입력갑입니다.")
    private Boolean success;

    private String message;

    private ErrorResponse error;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(null)
                .error(null)
                .build();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .error(null)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorResponse error) {
        return ApiResponse.<T>builder()
                .success(false)
                .data(null)
                .message(null)
                .error(error)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorResponse error, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .data(null)
                .message(message)
                .error(error)
                .build();
    }
}
