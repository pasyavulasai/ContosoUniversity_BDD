package com.test.hooks;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.test.configuration.Configuration;
import com.test.context.Device;
import com.test.context.DeviceContext;
import com.test.exceptions.StopTestException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class CucumberHooks {

    @Inject
    public DeviceContext deviceContext;

    @Inject
    public Device device;

    @Inject
    @Named("test.application")
    private String applicationName;

    protected String scenarioName;

    private String setUpSessionModifier = null;

    private int count = 0;

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) throws Exception {
        System.out.printf("Cucumber hook intance: %s", this);
        List<String> tags = new ArrayList<String>(scenario.getSourceTagNames());
        scenarioName = getScenarioName(tags);

        if (applicationName.contains("mobile")) {
            Configuration.getConfiguration().setApplicationMode("MOBILE");
        }else if (applicationName.contains("api")) {
            Configuration.getConfiguration().setApplicationMode("API");
        }

        testLevelSetUp(scenarioName, tags);
        this.deviceContext.device = this.device.getDeviceType(
                Configuration.getConfiguration().getApplicatonMode());

    }

    @After
    public void afterAll() throws Exception{
        System.out.println("This will run after all the scenario ");
        count ++;
    }

    /**
     * Method to return read the tags in the scenario and return the first tag as Scenario Name
     *
     * @param
     * @return
     */
    public static String getScenarioName(List<String> tags) throws Exception {
        System.out.println("*** ***");
        System.out.println("tag size = " + tags.size());
        String scenarioName = "";
        for (int x = 0; x < tags.size(); x++) {
            System.out.println("tag name = " + tags.get(x));
            if (tags.get(x).contains("Test")) {
                scenarioName = tags.get(x);
            }
        }
        scenarioName = scenarioName.replace("@", "");
        Configuration.getConfiguration().setTestCaseName(scenarioName);
        return scenarioName;
    }

    public void testLevelSetUp(String scenario_name, List<String> tags)
            throws StopTestException, MalformedURLException {
        scenarioName = scenario_name;
        // Parse tags.
        for (String tag : tags) {
            String strippedTag = tag.replace("@", "");
            if (tag.equals("@Mobile")) {
                Configuration.getConfiguration().setApplicationMode("MOBILE");
            }else if (tag.equals("@API")) {
                Configuration.getConfiguration().setApplicationMode("API");
            }
        }
        setUpSessionModifier = Configuration.getConfiguration().getSetUpModifier();
    }
}
