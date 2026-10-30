package com.pedrohrocha.borapay.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateEventRequestDto(

        @NotBlank(message = "Title is required")
        @Size(max = 255)
        String title,

        @Size(max = 255)
        String description,

        @NotBlank(message = "Location is required")
        @Size(max = 255)
        String location,

        @NotNull(message = "startTime is required")
        LocalDateTime startTime,

        @NotNull(message = "endTime is required")
        LocalDateTime endTime
) {
}
