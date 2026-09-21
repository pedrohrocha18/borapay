package com.pedrohrocha.borapay.controller;

import com.pedrohrocha.borapay.dto.*;
import com.pedrohrocha.borapay.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<EventResponseDto>>> getEvents() {
        List<EventResponseDto> response = eventService.findAllEvents();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Events found", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponseDto>> getEventById(@PathVariable UUID id) {
        EventResponseDto response = eventService.findEventById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Event found", response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponseDto>> updateEvent(@PathVariable UUID id, @Valid @RequestBody UpdateEventRequestDto request) {
        EventResponseDto response = eventService.updateEvent(id, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Event updated", response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<EventResponseDto>> updateEventStatus(@PathVariable UUID id, @Valid @RequestBody UpdateEventStatusRequestDto status) {
        EventResponseDto response = eventService.updateEventStatus(id, status);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Event status updated", response));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<EventResponseDto>> createEvent(@Valid @RequestBody CreateEventRequestDto request) {
        EventResponseDto response = eventService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Event created successfully", response));
    }
}
