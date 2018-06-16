package com.test.applications.cu.web.steps;

import com.google.inject.Inject;
import com.test.configuration.URLCollection;
import com.test.helper.BasePage;
import com.test.helper.utils.CommonFunctions;
import com.test.ui.CommonVariables;
import com.test.ui.CustomerFacingPages.*;
import com.test.ui.Portal.CustomerSearchGeneralPortalPage;
import com.test.ui.Portal.DirectPortalLoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CCARDStepDefs {

    public String testScenarioName = null;
    public Scenario scenario;

    @Before
    public void getScenarioName(Scenario scenario) {
        this.scenario=scenario;
        testScenarioName = scenario.getName();
        //System.out.println(testScenarioName);
    }

    @Inject
    BasePage basePage;
    @Inject
    CommonVariables cmnvrbls;
    @Inject
    CommonFunctions cmnFunctn;
    @Inject
    ApplicationForAZeroCreditCard applicationForAZeroCreditCard;
    @Inject
    AboutYouPage aboutYouPage;
    @Inject
    DirectPortalLoginPage directPortalLoginPage;
    @Inject
    CustomerSearchGeneralPortalPage customerSearchGeneralPortalPage;
    @Inject
    ContactDetailsPage contactDetailsPage;
    @Inject
    EmploymentFinancePage employmentFinancePage;
    @Inject
    CardOptionsPage cardOptionsPage;
    @Inject
    ApplicationSummaryPage applicationSummaryPage;
    @Inject
    ApplicationReferenceConfirmationPage applicationReferenceConfirmationPage;
    @Inject
    URLCollection urlCollection;


    public int getRowNumber() {
        String scenarioNameDesc = testScenarioName;
        int rowNumber = getRowNumberfromXLS(scenarioNameDesc);
        return rowNumber;
    }

    public int getRowNumberfromXLS(String scenarioDesc) {
        String scenarioNumberString = cmnFunctn.getScenarioNumber(scenarioDesc);
        //Below line has to be chnaged for each Step Definition. We are searching for rowIndex of the TestData row in a particular Tab i.e SAPP in the Pre\Prod Sheet.
        int rowIndex= cmnFunctn.getRowIndex(cmnvrbls.final_CCARDData, scenarioNumberString);
        Assert.assertNotEquals(rowIndex,-1);
        return rowIndex;

    }



    @Given("^I have reached on the Summary screen$")
    public void ihaveReachedOnTheSummaryScreen() throws Throwable {
//        urlCollection.loadOLACCPublicApplication();
        Assert.assertTrue(applicationForAZeroCreditCard.isValidPageDisplayed());
        applicationForAZeroCreditCard.submitDefaultValidDetails();
        Assert.assertTrue(aboutYouPage.isValidPageDisplayed());
        aboutYouPage.submitDefaultValidDetails();
        Assert.assertTrue(contactDetailsPage.isValidPageDisplayed());
        contactDetailsPage.submitDefaultValidDetails();
        Assert.assertTrue(employmentFinancePage.isValidPageDisplayed());
        employmentFinancePage.submitDefaultValidDetails();
        Assert.assertTrue(cardOptionsPage.isValidPageDisplayed());
        cardOptionsPage.submitDefaultValidDetails();
        Assert.assertTrue(applicationSummaryPage.isValidPageDisplayed());





    }

    @Given("^step 4$")
    public void stepnew() throws Throwable {
        int rowNumber= getRowNumber();
        urlCollection.loadTest2Url();
        Assert.assertTrue(directPortalLoginPage.isValidPageDisplayed());
        directPortalLoginPage.submitValidCredentials(rowNumber);
        Assert.assertTrue(customerSearchGeneralPortalPage.isValidPageDisplayed());
    }

    @When("^I confirm application submission$")
    public void iConfirmApplicationSubmission() throws Throwable {
        Assert.assertTrue(applicationSummaryPage.isValidPageDisplayed());
        applicationSummaryPage.markAllDocumentsAsReadAndSubmit();
        Assert.assertTrue(applicationReferenceConfirmationPage.isValidPageDisplayed());
    }

    @Then("^application number is created$")
    public void applicationNumberIsCreated() throws Throwable {
        Assert.assertTrue(applicationReferenceConfirmationPage.isValidPageDisplayed());
    }
}