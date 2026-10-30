package com.pedrohrocha.borapay.event.controller;

import com.pedrohrocha.borapay.dto.ApiResponseDto;
import com.pedrohrocha.borapay.event.dto.CreateEventRequestDto;
import com.pedrohrocha.borapay.event.dto.EventResponseDto;
import com.pedrohrocha.borapay.event.dto.UpdateEventRequestDto;
import com.pedrohrocha.borapay.event.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping()
    public ResponseEntity<ApiResponseDto<Page<EventResponseDto>>> getEvents(Pageable pageable) {
        Page<EventResponseDto> events = eventService.getEvents(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.success("Events retrieved successfully", events));
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<EventResponseDto>> createEvent(@Valid @RequestBody CreateEventRequestDto request) {
        EventResponseDto response = eventService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success("Event created successfully", response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDto<EventResponseDto>> updateEvent(@PathVariable UUID id, @RequestBody UpdateEventRequestDto request) {
        EventResponseDto response = eventService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.success("Event updated successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<EventResponseDto>> getById(@PathVariable UUID id) {
        EventResponseDto response = eventService.getEventById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.success("Event found successfully", response));
    }
}
