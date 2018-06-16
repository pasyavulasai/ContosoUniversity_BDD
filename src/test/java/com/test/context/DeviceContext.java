package com.test.context;

import com.google.inject.Inject;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class DeviceContext {

    @Inject
    public Device device;

    public boolean isMobile() {
        if (this.device == null || this.device.type == null) {
            return false;
        }

        return this.device.type.equalsIgnoreCase(Device.Type.Mobile.toString());
    }

    public boolean isAPI() {
        if (this.device == null || this.device.type == null) {
            return false;
        }

        return this.device.type.equalsIgnoreCase(Device.Type.API.toString());
    }
}
