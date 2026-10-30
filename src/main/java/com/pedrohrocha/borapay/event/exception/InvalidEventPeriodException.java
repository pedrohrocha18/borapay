package com.pedrohrocha.borapay.event.exception;

public class InvalidEventPeriodException extends RuntimeException {
    public InvalidEventPeriodException() {
        super("The event end date and time must be after the start date and time.");
    }

    public InvalidEventPeriodException(String message) {
        super(message);
    }
}
