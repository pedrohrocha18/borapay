package com.pedrohrocha.borapay.event.repository;

import com.pedrohrocha.borapay.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
