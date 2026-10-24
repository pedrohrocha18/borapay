package com.pedrohrocha.borapay.exception;

import com.pedrohrocha.borapay.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEventPeriodException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleInvalidEventPeriodException(InvalidEventPeriodException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.error(e.getMessage()));
    }
}
