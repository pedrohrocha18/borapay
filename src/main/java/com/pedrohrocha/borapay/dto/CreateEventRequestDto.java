package com.pedrohrocha.borapay.dto;

import java.time.LocalDateTime;

public record CreateEventRequestDto(
        String title,
        String description,
        String location,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
