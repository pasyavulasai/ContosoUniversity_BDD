package com.test.applications.taas_browser_health_checks;

import com.google.inject.Inject;
import com.test.helper.NavigationHelper;
import com.test.ui.taas.pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class TaaSBrowserHealthCheckStepDefinitions {

    @Inject
    LoginPage loginPage;

    @Inject
    NavigationHelper navigationHelper;

    @Inject
    WebDriver driver;


    @Given("^I open a browser1$")
    public void I_open_a_browser_one() throws Throwable {
        System.out.println("Starting browser \n");

    }

    @When("^I navigate to confluence website")
    public void i_navigate_to_a_Website() throws Throwable {
        System.out.println("Navigate to Confluence Login page \n");
        navigationHelper.navigateTo("https://confluence.almuk.santanderuk.corp/login.action/");
        Thread.sleep(10000);
    }

    @And("^I click on Confluence link")
    public void I_click_on_link() throws Throwable {
        System.out.println("Searching for Username Field \n");
        loginPage.verifyLabel();
    }

    @Then("^I should be at Confluence Home Page")
    public void I_should_go_to_Home_page() throws Throwable {
        System.out.println("Clicking on desired result \n");
        loginPage.clickConfluence();
    }

    @Given("^I open a browser2$")
    public void I_open_a_browser_two() throws Throwable {
        System.out.println("Starting browser \n");
    }

}
