package com.pedrohrocha.borapay.exception;

public class EventCanceledException extends RuntimeException {
    public EventCanceledException() {
        super("Cannot change the status of a canceled event. Create a new event.");
    }

    public EventCanceledException(String message) {
        super(message);
    }
}
