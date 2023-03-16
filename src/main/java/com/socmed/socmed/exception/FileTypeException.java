package com.socmed.socmed.exception;

public class FileTypeException extends RuntimeException{

    public FileTypeException() {
        super();
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }
}
