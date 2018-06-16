package com.test.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty" ,"html:target/cucumber.html" , "json:target/cucumber-report.json" , "junit:target/cucumber.xml","rerun:target/rerun.txt"},
//        glue = {"classpath:com.test.hooks","classpath:com.test.applications//cu/web/steps"},
        glue = {"classpath:com.test.hooks","classpath:com/test/applications/cu/web/steps"},
        features = "classpath:com/test/features/",
        tags = "@Test01"
        )
public class TestRunner { }