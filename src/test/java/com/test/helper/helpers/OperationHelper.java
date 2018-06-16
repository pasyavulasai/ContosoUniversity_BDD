package com.test.helper.helpers;

import com.test.exceptions.RetryException;

import java.util.logging.Logger;

class OperationHelper {

    private static final Logger LOG = Logger.getLogger(OperationHelper.class.getName());

    public static void withRetry(int maxAttempts, Runnable runnable) {
        withRetry(maxAttempts, 500, runnable);
    }

    public static void withRetry(int maxAttempts, int delay, Runnable runnable) {
        Exception exceptionThrown = null;
        int count = 0;

        while(count < maxAttempts) {
            try {
                runnable.run();
                return;
            } catch (Exception var6) {
                LOG.fine(String.format("[%s] Retrying %s%n because of %s %n", count, runnable, var6));
                exceptionThrown = var6;
                sleep(delay);
                ++count;
            }
        }

        if (exceptionThrown != null) {
            throw new RetryException(exceptionThrown);
        }
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep((long)ms);
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }
}
