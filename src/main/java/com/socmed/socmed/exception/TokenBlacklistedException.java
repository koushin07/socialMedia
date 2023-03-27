package com.socmed.socmed.exception;

public class TokenBlacklistedException extends RuntimeException {
    public TokenBlacklistedException() {
        super();
    }

    public TokenBlacklistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenBlacklistedException(String message) {
        super(message);
    }

    public TokenBlacklistedException(Throwable cause) {
        super(cause);
    }
}
