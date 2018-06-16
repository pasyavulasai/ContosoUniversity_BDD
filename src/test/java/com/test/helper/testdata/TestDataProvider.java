package com.test.helper.testdata;

import java.util.Map;

public interface TestDataProvider {
    Map<String, Object> get(String key);
}
