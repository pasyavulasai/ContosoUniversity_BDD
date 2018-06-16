package com.test.helper;

public class Result {

    // Successes: 0, Failures: 0, Exceptions: 2

    private String testName;

    private String successes;

    private String failures;

    private String exceptions;

    private String errorMessage;

    private String environment;

    private String sessionID;

    public Result() {
        this.testName = "0";
        this.successes = "0";
        this.failures = "0";
        this.exceptions = "0";
        this.errorMessage = "0";
        this.environment = "unknown";
        this.sessionID = "unknown";
    }

    public void setTestName(String testName) {
        this.testName = testName.trim();
    }

    public void setSuccesses(String successes) {
        this.successes = successes.trim();
    }

    public void setFailures(String failures) {
        this.failures = failures.trim();
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions.trim();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage.trim();
    }

    public void setEnvironemnt(String environment) {
        this.environment = environment;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getTestName() {
        return this.testName;
    }

    public String getSuccesses() {
        return this.successes;
    }

    public String getFailures() {
        return this.failures;
    }

    public String getExceptions() {
        return this.exceptions;

    }

    public String getErrorMessage() {
        return this.errorMessage;

    }

    public String getEnvironemnt() {
        return this.environment;

    }

    public String getSessionID() {
        return this.sessionID;

    }

}
