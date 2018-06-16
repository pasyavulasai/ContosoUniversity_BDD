package com.test.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
/**
 * Created by PASYA on 31/10/2016.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty" ,"html:target/cucumber.html", "json:target/cucumber-json-report.json", "junit:target/cucumber.xml"},
        glue = {"classpath:com.test.hooks","classpath:com/test/applications/cu/web/steps"},
        features = "classpath:com/test/features/",
        tags = "@Test01"
        )
public class ParallelTestRunner {
}
