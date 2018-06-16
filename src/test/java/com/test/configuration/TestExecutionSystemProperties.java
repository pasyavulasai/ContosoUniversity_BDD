package com.test.configuration;

import java.io.IOException;
import java.util.Properties;

public class TestExecutionSystemProperties {
    private final Properties properties;

    public TestExecutionSystemProperties() {
        properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("com.test.defaults.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.putAll(System.getProperties());
    }

    public Properties getProperties() {
        return this.properties;
    }
}
