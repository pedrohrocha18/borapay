package com.pedrohrocha.borapay.dto;

import com.pedrohrocha.borapay.enums.EventStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateEventStatusRequestDto(
        @NotNull(message = "Status is required")
        EventStatus status
) {
}
