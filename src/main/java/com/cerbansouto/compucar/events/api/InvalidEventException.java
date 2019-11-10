package com.cerbansouto.compucar.events.api;

public class InvalidEventException extends Exception {
    public InvalidEventException() {
    }

    public InvalidEventException(String message) {
        super(message);
    }

    public InvalidEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
