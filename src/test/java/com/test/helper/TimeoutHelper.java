package com.test.helper;

import java.util.concurrent.*;

public class TimeoutHelper {
    public static void withTimeout(Integer timeout, Runnable runnable) throws TimeoutException {
        ExecutorService scheduledExecutorService = Executors.newSingleThreadExecutor();
        try {
            scheduledExecutorService.submit(runnable).get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // Ignore execution exception
        }
    }

    private TimeoutHelper() {

    }
}