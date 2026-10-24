package com.pedrohrocha.borapay.service;

import com.pedrohrocha.borapay.dto.CreateEventRequestDto;
import com.pedrohrocha.borapay.dto.EventResponseDto;
import com.pedrohrocha.borapay.enums.EventStatus;
import com.pedrohrocha.borapay.event.entity.Event;
import com.pedrohrocha.borapay.exception.InvalidEventPeriodException;
import com.pedrohrocha.borapay.repository.EventRepository;
import lombok.RequiredArgsConstructor;
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
