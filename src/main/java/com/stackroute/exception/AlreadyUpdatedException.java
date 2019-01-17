package com.stackroute.exception;

public class AlreadyUpdatedException extends Exception {
    private String message;

    public AlreadyUpdatedException() {
    }

    public AlreadyUpdatedException(String message) {
        super(message);
        this.message = message;
    }
}
