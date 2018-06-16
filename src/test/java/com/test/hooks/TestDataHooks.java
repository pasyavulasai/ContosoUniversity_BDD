package com.test.hooks;

import com.google.inject.Inject;
import com.test.helper.ScenarioTagsHelper;
import com.test.helper.testdata.TestDataHelper;
import com.test.helper.testdata.TestDataProvider;
import cucumber.api.java.Before;

import java.util.List;
import java.util.Map;


public class TestDataHooks {

    private static final String TAG_PREFIX = "testdata-";

    @Inject
    private ScenarioTagsHelper scenarioTagsHelper;

    @Inject
    private TestDataHelper testDataHelper;

    @Inject
    private TestDataProvider testDataProvider;

    @Before
    public void getTestData() {
        String testDataTag = null;
        List<String> tags = scenarioTagsHelper.getTags();
        for(String tag : tags) {
            if(tag.startsWith(TAG_PREFIX)) {
                testDataTag = tag;
            }
        }

        // If we didn't find a tag we'll exit
        if(testDataTag == null) {
            return;
        }
        String testDataKey = testDataTag.replace(TAG_PREFIX, "");
        Map<String, Object> data = testDataProvider.get(testDataKey);
        testDataHelper.putAll(data);
    }
}
