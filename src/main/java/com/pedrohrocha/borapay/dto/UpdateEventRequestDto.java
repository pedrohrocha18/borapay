package com.pedrohrocha.borapay.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record UpdateEventRequestDto(
        @Size(max = 255)
        @Pattern(regexp = "(?s).*\\S.*", message = "Must not be blank")
        String title,

        @Size(max = 255)
        String description,

        @Size(max = 255)
        @Pattern(regexp = "(?s).*\\S.*", message = "Must not be blank")
        String location,

        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}
