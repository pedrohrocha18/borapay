package com.pedrohrocha.borapay.dto;

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

        @NotNull(message = "startDate is required")
        LocalDateTime startDateTime,

        @NotNull(message = "endDate is required")
        LocalDateTime endDateTime
) {
}
