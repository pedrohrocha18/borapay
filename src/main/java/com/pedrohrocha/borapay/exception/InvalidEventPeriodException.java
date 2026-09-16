package com.pedrohrocha.borapay.exception;

public class InvalidEventPeriodException extends RuntimeException {
    public InvalidEventPeriodException() {
        super("Invalid event period");
    }

    public InvalidEventPeriodException(String message) {
        super(message);
    }
}
