package com.socmed.socmed.exception;

public class PasswordConfirmationException extends RuntimeException {

    public PasswordConfirmationException() {
        super();
    }

    public PasswordConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordConfirmationException(String message) {
        super(message);
    }

    public PasswordConfirmationException(Throwable cause) {
        super(cause);
    }
}
