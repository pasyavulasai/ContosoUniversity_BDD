package com.test.exceptions;

public class UnexpectedPageException extends RuntimeException {
    public UnexpectedPageException(String message) {
        super(message);
    }
}

