package com.test.configuration;

import com.google.inject.Provider;
import com.test.exceptions.StopTestException;

public class ConfigurationProvider implements Provider<Configuration> {
    @Override
    public Configuration get() {
        try {
            return Configuration.getConfiguration();
        } catch (StopTestException e) {
            e.printStackTrace();
        }

        return null;
    }
}
