package com.test.exceptions;


public class TestDataError extends RuntimeException {
    /**
     * Constructs a new error with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TestDataError(String message) {
        super(message);
    }
}
