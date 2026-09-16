package com.pedrohrocha.borapay.repository;

import com.pedrohrocha.borapay.dto.EventResponseDto;
import com.pedrohrocha.borapay.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
