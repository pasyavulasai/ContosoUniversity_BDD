package com.test.exceptions;

public class RetryException extends RuntimeException {
    public RetryException(Exception exceptionThrown) {
        super(exceptionThrown);
    }
}
