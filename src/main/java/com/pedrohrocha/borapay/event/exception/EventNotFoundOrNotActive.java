package com.pedrohrocha.borapay.event.exception;

public class EventNotFoundOrNotActive extends RuntimeException {
    public EventNotFoundOrNotActive() {
        super("Event Not Found Or Not Active");
    }

    public EventNotFoundOrNotActive(String message) {
        super(message);
    }
}
