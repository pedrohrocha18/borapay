package com.pedrohrocha.borapay.event.dto;

import com.pedrohrocha.borapay.event.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponseDto(
        UUID eventId,
        String title,
        String description,
        String location,
        LocalDateTime startTime,
        LocalDateTime endTime,
        EventStatus eventStatus
) {
}
