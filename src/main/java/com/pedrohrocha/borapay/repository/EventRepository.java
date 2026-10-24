package com.pedrohrocha.borapay.repository;

import com.pedrohrocha.borapay.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
