package com.kpi.salon.exceptions;

public class InvalidUserDataException extends RuntimeException {

    public InvalidUserDataException() {
    }

    public InvalidUserDataException(String message) {
        super(message);
    }
}
