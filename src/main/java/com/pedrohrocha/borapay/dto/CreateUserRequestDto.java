package com.pedrohrocha.borapay.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateUserRequestDto(
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must have at most 255 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format invalid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(
                min = 8,
                max = 255,
                message = "Password must contain between 8 and 255 characters"
        )
        String password
) {
}