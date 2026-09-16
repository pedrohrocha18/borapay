package com.pedrohrocha.borapay.dto;

import com.pedrohrocha.borapay.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponseDto(
        UUID id,
        String title,
        String description,
        String location,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        EventStatus status
) {
}
