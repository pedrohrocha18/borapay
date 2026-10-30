package com.pedrohrocha.borapay.event.service;

import com.pedrohrocha.borapay.event.dto.CreateEventRequestDto;
import com.pedrohrocha.borapay.event.dto.EventResponseDto;
import com.pedrohrocha.borapay.event.dto.UpdateEventRequestDto;
import com.pedrohrocha.borapay.event.enums.EventStatus;
import com.pedrohrocha.borapay.event.entity.Event;
import com.pedrohrocha.borapay.event.exception.EventNotFoundOrNotActive;
import com.pedrohrocha.borapay.event.exception.InvalidEventPeriodException;
import com.pedrohrocha.borapay.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public EventResponseDto create(CreateEventRequestDto request) {
        if (!request.endTime().isAfter(request.startTime())) {
            throw new InvalidEventPeriodException();
        }

        Event event = toEntity(request);

        Event savedEvent = eventRepository.save(event);

        return toDto(savedEvent);
    }

    public EventResponseDto getEventById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(EventNotFoundOrNotActive::new);

        return toDto(event);
    }

    public Page<EventResponseDto> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(this::toDto);
    }

    public EventResponseDto update(UUID id, UpdateEventRequestDto request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(EventNotFoundOrNotActive::new);

        if (request.title() != null) event.setTitle(request.title());
        if (request.description() != null) event.setDescription(request.description());
        if (request.location() != null) event.setLocation(request.location());

        LocalDateTime startTime = request.startTime() != null
                ? request.startTime()
                : event.getStartTime();

        LocalDateTime endTime = request.endTime() != null
                ? request.endTime()
                : event.getEndTime();

        if (!endTime.isAfter(startTime)) {
            throw new InvalidEventPeriodException();
        }

        Event updatedEvent = eventRepository.save(event);

        return toDto(updatedEvent);
    }

    private Event toEntity(CreateEventRequestDto request) {
        return new Event(
                null,
                request.title(),
                request.description(),
                request.location(),
                request.startTime(),
                request.endTime(),
                EventStatus.PENDING
        );
    }

    private EventResponseDto toDto(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getStartTime(),
                event.getEndTime(),
                event.getEventStatus()
        );
    }
}
