package com.test.helper.testdata;

import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.HashMap;
import java.util.Map;


@ScenarioScoped
public class TestDataHelper {
    private final Map<String, Object> data = new HashMap<String, Object>();

    public Object get(String key) {
        return this.data.get(key);
    }

    public boolean containsKey(String key) {
        return this.data.containsKey(key);
    }

    public void putAll(Map<String, Object> data) {
        this.data.putAll(data);
    }
}
