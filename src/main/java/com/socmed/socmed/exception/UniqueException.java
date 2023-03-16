package com.socmed.socmed.exception;

public class UniqueException extends RuntimeException {
    public UniqueException() {
        super();
    }

    public UniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueException(String message) {
        super(message);
    }

    public UniqueException(Throwable cause) {
        super(cause);
    }
}
