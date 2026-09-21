package com.pedrohrocha.borapay.service;

import com.pedrohrocha.borapay.dto.CreateEventRequestDto;
import com.pedrohrocha.borapay.dto.EventResponseDto;
import com.pedrohrocha.borapay.entity.Event;
import com.pedrohrocha.borapay.enums.EventStatus;
import com.pedrohrocha.borapay.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void shouldCreateEventWhenPeriodIsValid() {
        CreateEventRequestDto request = toCreateEventRequestDto();

        when(eventRepository.save(any(Event.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EventResponseDto response = eventService.create(request);
        assertEquals(EventStatus.INACTIVE, response.status());
        assertEquals(request.title(), response.title());
        assertEquals(request.startDateTime(), response.startDateTime());
        assertEquals(request.endDateTime(), response.endDateTime());
        assertEquals(EventStatus.INACTIVE, response.status());
        verify(eventRepository, times(1)).save(any(Event.class));
    }


    private Event toEvent(CreateEventRequestDto dto) {
        Event event = new Event();

        event.setTitle(dto.title());
        event.setDescription(dto.description());
        event.setLocation(dto.location());
        event.setStartDateTime(dto.startDateTime());
        event.setEndDateTime(dto.endDateTime());
        event.setStatus(EventStatus.INACTIVE);

        return event;
    }

    private CreateEventRequestDto toCreateEventRequestDto() {
        LocalDateTime start = LocalDateTime.of(2026, 10, 10, 18, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 10, 23, 0);

        return new CreateEventRequestDto(
                "Festival",
                "Festival de música",
                "Patos de Minas",
                start,
                end
        );
    }
}