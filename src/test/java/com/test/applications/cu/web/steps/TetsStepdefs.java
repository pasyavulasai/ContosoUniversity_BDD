package com.test.applications.cu.web.steps;

import com.google.inject.Inject;
import com.test.configuration.PropertyConfiguration;
import com.test.ui.Test.JenkinsDashboard;
import com.test.ui.Test.JenkinsLogonPage;
import com.test.ui.Test.TestPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;

public class TetsStepdefs {

    @Inject
    PropertyConfiguration propertyConfiguration;

    public String testScenarioName = null;

    @Inject
    TestPage testPage;

    @Inject
    JenkinsLogonPage jenkinsLogonPage;

    @Inject
    JenkinsDashboard jenkinsDashboard;

    @Given("^Login to Jenkins Customised Job$")
    public void loginToJenkinsCustomisedJob() throws Throwable {
        testPage.hitCustomisedURL();
        Assert.assertTrue(jenkinsLogonPage.isValidPageDisplayed());
        jenkinsLogonPage.submitValidCredentials();
        Assert.assertTrue(jenkinsDashboard.isValidPageDisplayed());
    }


    @And("^capture report Screenshots$")
    public void captureReportScreenshots() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        jenkinsDashboard.takeReportScreenshots();


    }
}
