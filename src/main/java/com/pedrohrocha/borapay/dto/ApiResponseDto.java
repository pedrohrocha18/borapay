package com.pedrohrocha.borapay.dto;

import java.time.LocalDateTime;

public record ApiResponseDto<T>(
        boolean success,
        String message,
        T data,
        LocalDateTime time
) {

    public static <T> ApiResponseDto<T> success(String message, T data) {
        return new ApiResponseDto<>(true, message, data, LocalDateTime.now());
    }

    public static <T> ApiResponseDto<T> error(String message) {
        return new ApiResponseDto<>(false, message, null, LocalDateTime.now());
    }
}