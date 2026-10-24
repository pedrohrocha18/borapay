package com.pedrohrocha.borapay.exception;

import com.pedrohrocha.borapay.enums.EventStatus;

public class InvalidEventPeriodException extends RuntimeException {
    public InvalidEventPeriodException() {
        super("The event end date and time must be after the start date and time.");
    }

    public InvalidEventPeriodException(String message) {
        super(message);
    }
}
