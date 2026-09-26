package com.pedrohrocha.borapay.service;

import com.pedrohrocha.borapay.dto.CreateUserRequestDto;
import com.pedrohrocha.borapay.dto.UserResponseDto;
import com.pedrohrocha.borapay.entity.User;
import com.pedrohrocha.borapay.enums.UserRole;
import com.pedrohrocha.borapay.exception.UserAlreadyExistsException;
import com.pedrohrocha.borapay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto create(CreateUserRequestDto request) {
        boolean userAlreadyExists = userRepository.existsByEmail(request.email());

        if (userAlreadyExists) {
            throw new UserAlreadyExistsException();
        }

        User user = toEntity(request);

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    private User toEntity(CreateUserRequestDto request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(UserRole.USER);

        return user;
    }

    private UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
