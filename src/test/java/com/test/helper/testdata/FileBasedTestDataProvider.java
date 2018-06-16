package com.test.helper.testdata;


import com.test.exceptions.TestDataError;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;


public class FileBasedTestDataProvider implements TestDataProvider {

    private final Map<String, Map<String, Object>> data = new HashMap<String, Map<String, Object>>();

    public FileBasedTestDataProvider() {
        addTestData("customer-can-make-a-payment", TestDataKeys.TDC_CREDIT_CARD_NUMBER, "4111111111002484");
    }

    @Override
    public Map<String, Object> get(String key) {
        if(!this.data.containsKey(key)) {
            throw new TestDataError(format("Could not find test data for key %s", key));
        }

        return this.data.get(key);
    }

    private void addTestData(String scenarioKey, String dataKey, Object value) {
        Map<String, Object> localData =  new HashMap<String, Object>();
        localData.put(dataKey, value);
        this.data.put(scenarioKey, localData);
    }
}
