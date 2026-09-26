package com.pedrohrocha.borapay.dto;

import com.pedrohrocha.borapay.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        UserRole role,
        LocalDateTime createdAt
) {
}