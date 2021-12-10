package com.maxsaldanha.os.services.exceptions;

public class ObjectNotfoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNotfoundException(String message) {
        super(message);
    }

    public ObjectNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
