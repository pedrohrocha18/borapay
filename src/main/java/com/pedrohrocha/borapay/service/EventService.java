package com.pedrohrocha.borapay.service;

import com.pedrohrocha.borapay.dto.CreateEventRequestDto;
import com.pedrohrocha.borapay.dto.EventResponseDto;
import com.pedrohrocha.borapay.dto.UpdateEventRequestDto;
import com.pedrohrocha.borapay.entity.Event;
import com.pedrohrocha.borapay.enums.EventStatus;
import com.pedrohrocha.borapay.exception.EventNotFoundException;
import com.pedrohrocha.borapay.exception.InvalidEventPeriodException;
import com.pedrohrocha.borapay.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public EventResponseDto updateEvent(UUID id, UpdateEventRequestDto updateEventRequestDto) {
        boolean titleIsNotNull = updateEventRequestDto.title() != null;
        boolean descriptionIsNotNull = updateEventRequestDto.description() != null;
        boolean locationIsNotNull = updateEventRequestDto.location() != null;
        boolean startDateTimeIsNotNull = updateEventRequestDto.startDateTime() != null;
        boolean endDateTimeIsNotNull = updateEventRequestDto.endDateTime() != null;

        Event event = eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        if (titleIsNotNull) event.setTitle(updateEventRequestDto.title());
        if (descriptionIsNotNull) event.setDescription(updateEventRequestDto.description());
        if (locationIsNotNull) event.setLocation(updateEventRequestDto.location());
        if (startDateTimeIsNotNull) event.setStartDateTime(updateEventRequestDto.startDateTime());
        if (endDateTimeIsNotNull) event.setEndDateTime(updateEventRequestDto.endDateTime());

        if (!event.getEndDateTime().isAfter(event.getStartDateTime())) {
            throw new InvalidEventPeriodException();
        }

        eventRepository.save(event);

        return toResponse(event);
    }

    public List<EventResponseDto> findAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EventResponseDto findEventById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        return toResponse(event);
    }

    public EventResponseDto create(CreateEventRequestDto createEventRequestDto) {
        Event event = new Event();

        event.setTitle(createEventRequestDto.title());
        event.setDescription(createEventRequestDto.description());
        event.setLocation(createEventRequestDto.location());
        event.setStartDateTime(createEventRequestDto.startDateTime());
        event.setEndDateTime(createEventRequestDto.endDateTime());
        event.setStatus(EventStatus.INACTIVE);

        if (!createEventRequestDto.endDateTime()
                .isAfter(createEventRequestDto.startDateTime())) {
            throw new InvalidEventPeriodException();
        }

        Event savedEvent = eventRepository.save(event);

        return toResponse(savedEvent);
    }

    private EventResponseDto toResponse(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                event.getStatus()
        );
    }
}
