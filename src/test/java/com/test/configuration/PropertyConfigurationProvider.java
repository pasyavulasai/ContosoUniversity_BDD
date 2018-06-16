package com.test.configuration;

import com.google.inject.Provider;
import com.test.exceptions.StopTestException;

public class PropertyConfigurationProvider implements Provider<PropertyConfiguration> {
    @Override
    public PropertyConfiguration get() {
        try {
            return PropertyConfiguration.getPropertyConfiguration();
        } catch (StopTestException e) {
            e.printStackTrace();
        }

        return null;
    }
}
