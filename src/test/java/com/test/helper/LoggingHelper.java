package com.test.helper;

import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;

/**
 * Class holding utility methods useful for debugging and logging.
 */
public class LoggingHelper {

    /**
     * Method prints a line to the console prefixed with "INFO: ".
     * @param message info message to be printed
     */
    public static void printInfo(String message) {
        print(message, "INFO");
    }

    /**
     * Method prints a line to the console prefixed with "ERROR: ".
     * Lines beginning with "ERROR: " are usually highlighted by an IDE's console, to make them highly visible.
     * @param message error message to be printed
     */
    public static void printError(String message) {
        print(message, "ERROR");
    }

    /**
     * Method prints a line to the console prefixed with "WARNING: ".
     * Lines beginning with "WARNING: " are usually highlighted by an IDE's console, to make them more visible.
     * @param message warning message to be printed
     */
    public static void printWarning(String message) {
        print(message, "WARNING");
    }

    /**
     * Method prints a line to the console prefixed with "DEBUG: " when debugging is enabled.
     * Lines begin with "DEBUG: ".
     * @param message error message to be printed
     */
    public static void printDebug(String message) {
        printDebug(message, "DEBUG");
    }

    /**
     * Method prints a line to the console prefixed with what is specified by the {@code prefix} parameter when debugging is enabled.
     * @param message the message to be printed to console
     * @param prefix the prefix to be added, or "DEBUG :" if {@code null} is passed in
     */
    public static void printDebug(String message, String prefix) {
        try {
            if(Configuration.getConfiguration().isDebug() || Boolean.valueOf(
                    System.getProperty(
                            "debug.enabled"))) {
                print(message, (null != prefix) ? prefix : "DEBUG");
            }
        } catch (StopTestException e) {
            print("An error occurred trying to read the autotest-framework configuration: " +
                    e.getMessage(), "ERROR");
        }
    }

    /**
     * Method prints a line to the console prefixed with what is specified by the {@code prefix} parameter.
     * @param message the message to be printed to console
     * @param prefix the prefix to be added, or "INFO :" if {@code null} is passed in
     */
    public static void print(String message, String prefix) {
        System.out.println(((null != prefix) ? prefix : "INFO") + ": " + message);
    }
}
