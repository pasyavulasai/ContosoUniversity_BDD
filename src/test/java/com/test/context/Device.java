package com.test.context;

import cucumber.runtime.java.guice.ScenarioScoped;

/**
 * Device DO
 */
@ScenarioScoped
public class Device {

    public String type;

    public Device getDeviceType(String deviceType) {
        Device device = new Device();
        device.type = (deviceType.equalsIgnoreCase(Type.Mobile.toString()) ? Type.Mobile.toString() : Type.Desktop.toString());
        return device;
    }

    public enum Type {
        Mobile,Desktop,API
    }

}
