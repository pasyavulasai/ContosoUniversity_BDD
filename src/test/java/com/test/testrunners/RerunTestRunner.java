package com.test.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty" ,"html:target/cucumber-rerun.html" , "json:target/cucumber-rerun-report.json" , "junit:target/cucumber-rerun.xml","rerun:target/rerun.txt"},
        glue = {"classpath:com.test.hooks","classpath:com.test.applications/"},
        features = "@target/rerun.txt" )
public class RerunTestRunner { }