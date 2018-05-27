package com.kpi.salon.exceptions;

public class RequestFailException extends RuntimeException {
    public RequestFailException(String message) {
        super(message);
    }

    public RequestFailException() {}
}
