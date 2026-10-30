package com.pedrohrocha.borapay.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateEventRequestDto(
        @Size(max = 255)
        String title,

        @Size(max = 255)
        String description,

        @Size(max = 255)
        String location,

        LocalDateTime startTime,

        LocalDateTime endTime
) {
}
