package com.test.applications.cu.web.steps;

import com.google.inject.Inject;
import com.test.configuration.URLCollection;
import com.test.helper.BasePage;
import com.test.helper.utils.CommonFunctions;
import com.test.ui.CommonVariables;
import com.test.ui.Portal.*;
import com.test.ui.Portal.General.DocumentControl.CustomerSearchPage;
import com.test.ui.sppportal.pages.loginblock.LoginSPPPortal;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CUStepDefs {

    public String testScenarioName = null;
    public Scenario scenario;

    @Before
    public void getScenarioName(Scenario scenario) {
        this.scenario = scenario;
        testScenarioName = scenario.getName();
        System.out.println(testScenarioName);
    }

    @Inject
    private BasePage basePage;
    @Inject
    CommonVariables cmnvrbls;
    @Inject
    CommonFunctions cmnFunctn;
    @Inject
    PortalLoginPage portalLoginPage;
    @Inject
    PortalDashBoard portalDashBoard;
    @Inject
    DirectPortalLoginPage directPortalLoginPage;
    @Inject
    CustomerSearchGeneralPortalPage customerSearchGeneralPortalPage;
    @Inject
    CustomerSearchGeneralPortalPage customerSearchResultsGeneralPortalPage;
    @Inject
    CustomerRecordListGeneralPortalPage customerRecordListGeneralPortalPage;
    @Inject
    CustomerDocumentsGeneralPortalPage customerDocumentsGeneralPortalPage;
    @Inject
    CustomerDocumentsDetailsGeneralPortalPage customerDocumentsDetailsGeneralPortalPage;
    @Inject
    CustomerSearchPage customerSearchPage;
    @Inject
    URLCollection urlCollection;


//    Sales Portal Steps

    @Inject
    private LoginSPPPortal loginSPPPortal;
//    @Inject
//    private UKRACOutstandingApplicationsPage ukracOutstandingApplicationsPage;
//    @Inject
//    private UKRACOutstandingApplicationsDetailsPage ukracOutstandingApplicationsDetailsPage;
//    @Inject
//    private UKRACAWSDecisionLogEnquiryPage ukracawsDecisionLogEnquiryPage;
//    @Inject
//    private SABBAWSHealthcheckPAMANT sabbawsHealthcheckPAMANT;
//    @Inject
//    private SABBAWSHealthcheckPAMANTLegalRepsPage sabbawsHealthcheckPAMANTLegalRepsPage;
//    @Inject
//    private SABBAWSHealthcheckCARDUpgrade sabbawsHealthcheckCARDUpgrade;
//    @Inject
//    private ToDoListPage toDoListPage;
//    @Inject
//    private SABBAWSHealthcheckCARDUpgradeFacilitiesSelection sabbawsHealthcheckCARDUpgradeFacilitiesSelection;
//    @Inject
//    private SABBAWSHealthcheckDebitCardUpgradeCreditChecks sabbawsHealthcheckDebitCardUpgradeCreditChecks;
//    @Inject
//    private SABBAWSHealthcheckDebitCardUpgradeDecisioning sabbawsHealthcheckDebitCardUpgradeDecisioning;
//    @Inject
//    private SABBAWSHealthcheckOverdraftUpgrade sabbawsHealthcheckOverdraftUpgrade;
//    @Inject
//    private SABBAWSHealthcheckOverdtaftUpgradeFacilitiesSelection sabbawsHealthcheckOverdtaftUpgradeFacilitiesSelection;
//    @Inject
//    private SABBAWSHealthcheckOverdtaftUpgradeCreditChecks sabbawsHealthcheckOverdtaftUpgradeCreditChecks;
//    @Inject
//    private SABBAWSHealthcheckOverdtaftUpgradeDecisioning sabbawsHealthcheckOverdtaftUpgradeDecisioning;


    public int getRowNumber() {
        String scenarioNameDesc = testScenarioName;
        int rowNumber = getRowNumberfromXLS(scenarioNameDesc);
        return rowNumber;
    }

    public int getRowNumberfromXLS(String scenarioDesc) {
        String scenarioNumberString = cmnFunctn.getScenarioNumber(scenarioDesc);
        //Below line has to be chnaged for each Step Definition. We are searching for rowIndex of the TestData row in a particular Tab i.e SAPP in the Pre\Prod Sheet.
//        System.out.println("----------------------------  : "+scenarioNumberString);
        int rowIndex = cmnFunctn.getRowIndex(cmnvrbls.final_CUData, scenarioNumberString);
        System.out.println("-----------------: "+rowIndex);
        Assert.assertNotEquals(rowIndex, -1);
        return rowIndex;

    }

    @Then("^Customer exists on General Portal$")
    public void customerExists() throws Throwable {
        Assert.assertTrue(customerRecordListGeneralPortalPage.isRecordDisplayed());
        customerRecordListGeneralPortalPage.selectFirstCustomer();
        Assert.assertTrue(customerDocumentsGeneralPortalPage.isValidPageDisplayed());
        customerDocumentsGeneralPortalPage.selectFirstDocument();
        Assert.assertTrue(customerDocumentsDetailsGeneralPortalPage.isValidPageDisplayed());

    }

    @Then("^Customer Search screen is displayed with the list of Customer Name$")
    public void customerSearchScreenIsDisplayedWithTheListOfCustomerName() throws Throwable {
        Assert.assertTrue(customerSearchPage.isTableContainingAnEntry());
    }

    @Given("^I login onto Retail General Portal$")
    public void iLoginOntoRetailGeneralPortal() throws Throwable {
        int rowNumber = getRowNumber();
        portalLoginPage.markScenarioToNotDeleteCookies();
        urlCollection.loadSingleSignOnPortalURL();
        portalLoginPage.followWorkArounds();
        portalLoginPage.submitCredentials(rowNumber);
        portalLoginPage.donotacceptAlertIfExists();
        portalLoginPage.switchDriverToNewWindow();
        Assert.assertTrue(portalDashBoard.isValidPageDisplayed());
        portalLoginPage.switchAndCloseParentWindow();
        portalLoginPage.switchBackToNewWindow();
    }

    @Given("^I login with Direct Retail General Portal Customer Financial Sales URL$")
    public void iLoginWithDirectRetailGeneralPortalCustomerFinancialSalesURL() throws Throwable {
        int rowNumber = getRowNumber();
//        urlCollection.loadRetailGeneralPortalCFSales();
        Assert.assertTrue(directPortalLoginPage.isValidPageDisplayed());
        directPortalLoginPage.submitValidCredentials(rowNumber);
        Assert.assertTrue(customerSearchGeneralPortalPage.isValidPageDisplayed());
    }

    @When("^I perform Customer search on General Portal$")
    public void iPerformCustomerSearchOnGeneralPortal() throws Throwable {
        int rowNumber = getRowNumber();
        customerSearchGeneralPortalPage.submitCustomerSearchRequest(rowNumber);
        Assert.assertTrue(customerRecordListGeneralPortalPage.isRecordDisplayed());
    }

    @Given("^I login with Direct Retail General Portal Customer Financial Risk URL$")
    public void iLoginWithDirectRetailGeneralPortalCustomerFinancialRiskURL() throws Throwable {
        int rowNumber = getRowNumber();
//        urlCollection.loadDirectRetailGeneralPortalCustomerFinancialRiskURL();
        Assert.assertTrue(directPortalLoginPage.isValidPageDisplayed());
        directPortalLoginPage.submitValidCredentials(rowNumber);
        Assert.assertTrue(customerSearchGeneralPortalPage.isValidPageDisplayed());
    }

    @Given("^I login with Direct Retail General Portal Document Control Collection Review Documents URL$")
    public void iLoginWithDirectRetailGeneralPortalDocumentControlCollectionReviewDocumentsURL() throws Throwable {
        int rowNumber = getRowNumber();
//        urlCollection.loadDirectRetailGeneralPortalDCCollectionReviewURL();
        Assert.assertTrue(directPortalLoginPage.isValidPageDisplayed());
        directPortalLoginPage.submitValidCredentials(rowNumber);
        Assert.assertTrue(customerSearchPage.isValidPageDisplayed());
    }

    @Given("^I login with Direct Retail General Portal Document Control Enquiry Proof of Identification URL$")
    public void iLoginWithDirectRetailGeneralPortalDocumentControlEnquiryProofOfIdentificationURL() throws Throwable {
        int rowNumber = getRowNumber();
//        urlCollection.loadDirectRetailGeneralPortalDCEnquiryProofURL();
        Assert.assertTrue(directPortalLoginPage.isValidPageDisplayed());
        directPortalLoginPage.submitValidCredentials(rowNumber);
        Assert.assertTrue(customerSearchPage.isValidPageDisplayed());
    }



    @Given("^Login credentials of LCO Sales Portal Outstanding applications URL$")
    public void LoginCredentialsOfLCOSalesPortalOutstandingApplicationsURL() throws Throwable {
        loginSPPPortal.markScenarioToNotDeleteCookies();
        urlCollection.loadSingleSignOnPortalURL();
        Assert.assertTrue(loginSPPPortal.validateLoginPage());
    }

    @When("^I Login to Sales Portal Outstanding applications URL$")
    public void iLoginToSalesPortalOutstandingApplicationsURL() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.logindetails(rowNumber);
    }

    @Then("^I should see outstanding applications search criteria details$")
    public void iShouldSeeOutstandingApplicationsSearchCriteriaDetails() throws Throwable {
//        Assert.assertTrue(ukracOutstandingApplicationsPage.validateOutstandingApplicationPage());
    }

    @When("^I Login to Sales Portal Outstanding applications URL and search for data$")
    public void iLoginToSalesPortalOutstandingApplicationsURLAndSearchForData() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.logindetails(rowNumber);
//        Assert.assertTrue(ukracOutstandingApplicationsPage.validateOutstandingApplicationPage());
//        ukracOutstandingApplicationsPage.selectSarchOutstandingApplications(rowNumber);
    }

    @Then("^I should see outstanding applications search criteria results$")
    public void iShouldSeeOutstandingApplicationsSearchCriteriaResults() throws Throwable {
//        Assert.assertTrue(ukracOutstandingApplicationsDetailsPage.validateSearchResults());
    }

    @Given("^Login credentials of LCO Sales Portal decision log search URL$")
    public void loginCredentialsOfLCOSalesPortalDecisionLogSearchURL() throws Throwable {
//        loginSPPPortal.markScenarioToNotDeleteCookies();
//        urlCollection.loadDirectSalesUKRACDecisionLogSearchURL();
//        Assert.assertTrue(loginSPPPortal.validateLoginPage());
    }

    @When("^I Login to Sales Portal decision log search URL$")
    public void iLoginToSalesPortalDecisionLogSearchURL() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.logindetails(rowNumber);
}

    @Then("^I should see Decision log search criteria details$")
    public void iShouldSeeDecisionLogSearchCriteriaDetails() throws Throwable {
//        Assert.assertTrue(ukracawsDecisionLogEnquiryPage.validateDecisionLogSearchPage());
    }

    @Given("^Login credentials of LCO Sales Portal Participant Mgmt for Business applications URL$")
    public void loginCredentialsOfLCOSalesPortalParticipantMgmtForBusinessApplicationsURL() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.markScenarioToNotDeleteCookies();
//        urlCollection.loadDirectSalesPAMANTBURL(sabbawsHealthcheckPAMANT.getACCOUNTNO(rowNumber));
//        Assert.assertTrue(loginSPPPortal.validateLoginPage());
    }

    @When("^I login to PamantB URL and remove the Legal Rep$")
    public void iLoginToPamantBURLAndRemoveTheLegalRep() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.logindetails(rowNumber);
//        Assert.assertTrue(sabbawsHealthcheckPAMANT.validateApplicationDataPage());
//        sabbawsHealthcheckPAMANT.completeHoldingRelationship(rowNumber);
//        Assert.assertTrue(sabbawsHealthcheckPAMANTLegalRepsPage.validateAccountHoldersPage());
//        sabbawsHealthcheckPAMANTLegalRepsPage.completeHoldingRelationship();
//        sabbawsHealthcheckPAMANTLegalRepsPage.clickonRemoveHolder();
    }


    @Then("^I should see my legal rep get removed from my business bank account$")
    public void iShouldSeeMyLegalRepGetRemovedFromMyBusinessBankAccount() throws Throwable {
//        sabbawsHealthcheckPAMANTLegalRepsPage.clickonRemoveHolder();
    }


    @Given("^Login credentials of LCO Sales Portal Card Upgrade for Business applications URL$")
    public void loginCredentialsOfLCOSalesPortalCardUpgradeForBusinessApplicationsURL() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.markScenarioToNotDeleteCookies();
//        urlCollection.loadDirectSalesPortalBusinessCARDUpgrade(sabbawsHealthcheckCARDUpgrade.getACCOUNTNO(rowNumber));
//        Assert.assertTrue(loginSPPPortal.validateLoginPage());
    }

    @When("^I login to Sales portal Business banking Card Upgrade URL$")
    public void iLoginToSalesPortalBusinessBankingCardUpgradeURL() throws Throwable {
        int rowNumber = getRowNumber();
//        sabbawsHealthcheckCARDUpgrade.completeCardUpgradePage();
//        toDoListPage.todoListData();
//        Assert.assertTrue(sabbawsHealthcheckCARDUpgradeFacilitiesSelection.sppFacilitiesSelectionPageLoad());
//        sabbawsHealthcheckCARDUpgradeFacilitiesSelection.completeCardRequestorUpgradeFacilitiesSelection(rowNumber);
//        Assert.assertTrue(sabbawsHealthcheckDebitCardUpgradeCreditChecks.sppDebitCardUpgradeCreditChecksPageLoad());
//        sabbawsHealthcheckDebitCardUpgradeCreditChecks.completeDebitCardUpgradeCreditChecks(rowNumber);
//        toDoListPage.todoListData();
    }

    @Then("^I should see my Business banking Card get Upgraded$")
    public void iShouldSeeMyBusinessBankingCardGetUpgraded() throws Throwable {
        int rowNumber = getRowNumber();
//        Assert.assertTrue(sabbawsHealthcheckDebitCardUpgradeDecisioning.sppDebitCardUpgradeDecisioningPageLoad());
//        sabbawsHealthcheckDebitCardUpgradeDecisioning.completeDebitCardUpgradeDecisioningDetails(rowNumber);
    }

    @Given("^Login credentials of LCO Sales Portal Overdraft Upgrade for Business applications URL$")
    public void loginCredentialsOfLCOSalesPortalOverdraftUpgradeForBusinessApplicationsURL() throws Throwable {
        int rowNumber = getRowNumber();
//        loginSPPPortal.markScenarioToNotDeleteCookies();
//        urlCollection.loadDirectSalesPortalBusinessOverdraftUpgrade(sabbawsHealthcheckOverdraftUpgrade.getACCOUNTNO(rowNumber));
//        Assert.assertTrue(loginSPPPortal.validateLoginPage());
    }

    @When("^I login to Sales portal Business banking Overdraft Upgrade$")
    public void iLoginToSalesPortalBusinessBankingOverdraftUpgrade() throws Throwable {
        int rowNumber = getRowNumber();
//        Assert.assertTrue(sabbawsHealthcheckOverdraftUpgrade.sppOverdraftRequestorUpgradePageLoad());
//        sabbawsHealthcheckOverdraftUpgrade.completeOverdraftUpgradePage();
//        toDoListPage.todoListData();
//        Assert.assertTrue(sabbawsHealthcheckOverdtaftUpgradeFacilitiesSelection.sppODFacilitiesSelectionPageLoad());
//        sabbawsHealthcheckOverdtaftUpgradeFacilitiesSelection.completeOverdraftRequestorUpgradeFacilitiesSelection(rowNumber);
//        Assert.assertTrue(sabbawsHealthcheckOverdtaftUpgradeCreditChecks.sppOverdraftUpgradeCreditChecksPageLoad());
//        sabbawsHealthcheckOverdtaftUpgradeCreditChecks.completeOverdraftUpgradeCreditChecks(rowNumber);
//        toDoListPage.todoListData();
    }

    @Then("^I should see my Business banking Overdraft get Upgraded$")
    public void iShouldSeeMyBusinessBankingOverdraftGetUpgraded() throws Throwable {
        int rowNumber = getRowNumber();
//        Assert.assertTrue(sabbawsHealthcheckOverdtaftUpgradeDecisioning.sppDebitOverdraftUpgradeDecisioningPageLoad());
//        sabbawsHealthcheckOverdtaftUpgradeDecisioning.completeOverdraftUpgradeDecisioningDetails(rowNumber);
    }



    @When("^I enter the username \"(.*?)\" and password \"(.*?)\"$")
    public void i_enter_the_username_and_password(String UserName, String Password) throws Throwable {
        System.out.println("sdfdsf sdf");
//            browserSetup.LoginUserValidation(UserName,Password);
    }

    @Then("^I will see the error message \"(.*?)\"$")
    public void i_will_see_the_error_message(String expectedErrorMessage) throws Throwable {
        System.out.println("sdfdsf sdf");
//           Assert.assertEquals(browserSetup.returnText(".//form/div[3]/div[2]/span"),expectedErrorMessage);
    }

    @Then("^I will see the password error message \"(.*?)\"$")
    public void i_will_see_the_password_error_message(String expectedErrorMessage) throws Throwable {
//            Assert.assertEquals(browserSetup.returnText(".//div[3]/div[2]/div[1]/ul/li"),expectedErrorMessage);
    }

    @Then("^I will remain on the \"([^\"]*)\" page$")
    public void i_will_remain_on_the_page(String pageTitle) throws Throwable {
//            browserSetup.pageChecker(pageTitle);
        // System.out.println("------------page title is --------- :"+pageTitle);
//            browserSetup.tearDown();
    }

    @When("^I click on \"([^\"]*)\" menu tab with xpath \"([^\"]*)\"$")
    public void i_click_on_menu_tab_with_xpath(String arg1, String arg2) throws Throwable {
//        int rowNumber = getRowNumber();
//        System.out.println("This is About test row number : "+rowNumber);
        System.out.println("------------ "+arg1 +" --------- :"+arg2);
//            browserSetup.clickMainMenuOption(arg2,arg1);
    }

    @Then("^I will be on the \"([^\"]*)\" page$")
    public void i_will_be_on_the_page(String arg1) throws Throwable {
        //System.out.println("------------page title is --------- :" + arg1);
//            browserSetup.pageChecker(arg1);
//            browserSetup.tearDown();
    }

    @Then("^I should see the \"([^\"]*)\" text with xpath \"([^\"]*)\"$")
    public void i_should_see_the_text_with_xpath(String arg1, String arg2) throws Throwable {
//            System.out.println("------------page title is --------- :" + arg1  +"__________________--"+arg2);
//            browserSetup.contentCheck(arg2,arg1);
    }

    @When("^I click on Login link with \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_click_on_Login_link_with(String identifier, String linkName) {
//            browserSetup.clickOnLink(identifier,linkName);
    }

    @Then("^I should see the data \"([^\"]*)\" text with id \"([^\"]*)\"$")
    public void webTable(String arg1, String arg2) throws Throwable {

        int rowNumber = getRowNumber();
        portalLoginPage.markScenarioToNotDeleteCookies();
        urlCollection.loadSingleSignOnPortalURL();
//            browserSetup.handle_Dynamic_Webtable(arg2,arg1);
    }

    @Given("^I launch the contoso university website$")
    public void i_launch_the_contoso_university_website() throws Throwable {
        portalLoginPage.markScenarioToNotDeleteCookies();
        urlCollection.loadSingleSignOnPortalURL();
//        setUpWebDriver();
    }

    @Then("^I should see username \"([^\"]*)\" at the right top of the page$")
    public void i_should_see_username_at_the_right_top_of_the_page(String username) throws Throwable {
//            browserSetup.checkUserNameOnLogin("HeadLoginView_HeadLoginName",username);
    }

    @Then("^I close the Browser$")
    public void i_close_the_Browser() throws Throwable {
//            browserSetup.tearDown();
    }

    @When("^I click on \"([^\"]*)\" menu tab with submenu with xpath \"([^\"]*)\"$")
    public void i_click_on_menu_tab_with_submenu_with_xpath(String arg1, String arg2) throws Throwable {
//            browserSetup.clickOnSubMenuOption(arg1,arg2);
    }

    @When("^I enter the \"([^\"]*)\" to textbox with xpath \"([^\"]*)\"$")
    public void i_enter_the_to_textbox_with_xpath(String arg1, String arg2) throws Throwable {
//            browserSetup.enterText(arg1, arg2);
    }

    @When("^I click on link xpath \"([^\"]*)\"$")
    public void i_click_on_link_xpath(String arg1) throws Throwable {
//            browserSetup.submitLink(arg1);
    }

}