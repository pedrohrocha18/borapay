package com.pedrohrocha.borapay.controller;

import com.pedrohrocha.borapay.dto.ApiResponse;
import com.pedrohrocha.borapay.dto.CreateUserRequestDto;
import com.pedrohrocha.borapay.dto.UserResponseDto;
import com.pedrohrocha.borapay.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse<UserResponseDto>> create(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto userResponseDto = userService.create(createUserRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User Created", userResponseDto));
    }
}
