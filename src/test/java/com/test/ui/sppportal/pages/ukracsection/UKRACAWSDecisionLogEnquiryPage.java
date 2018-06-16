//package com.test.ui.sppportal.pages.ukracsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import org.junit.Assert;
//import org.openqa.selenium.support.FindBy;
//
//public class UKRACAWSDecisionLogEnquiryPage extends BasePage {
//
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//
//    public String DLEText = "Decision Log Enquiry";
//
//    @FindBy(xpath = ".//*[@id='main_titGeneral_REPAINT']/div[2]")
//    private PageElement titleText;
//
//    @FindBy(xpath = ".//*[@id='main_cabBuscador_text']/span[1]")
//    private PageElement subTitleText;
//
//    @FindBy(xpath = ".//*[@id='main.cabBuscador#subtitle']")
//    private PageElement subTitleText1;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitObligatorios_repaint'] span")
//    private PageElement pleaseNoteText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitCaseID_repaint'] span")
//    private PageElement labelCaseIdText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitUWSalaryNum_repaint'] span")
//    private PageElement labelUWSalText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitOrganisation_repaint'] span")
//    private PageElement labelOrgText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitCentreCode_repaint'] span")
//    private PageElement labelCCText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitSubmissionDate_repaint'] span")
//    private PageElement labelSubDateText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitApplicationID_repaint'] span")
//    private PageElement labelAppIdText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitCustomerID_repaint'] span")
//    private PageElement labelCustIdText;
//
//    @FindBy(css = "div[id*='main_formDecisionLogSearch.lblLitProduct_repaint'] span")
//    private PageElement labelProductText;
//
//    @FindBy(css = "u[id*='AccessKeyclear']")
//    private PageElement clearBtn;
//
//    @FindBy(css = "u[id*='AccessKeysearch']")
//    private PageElement searchBtn;
//
//    public boolean validateDecisionLogSearchPage() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        String title = titleText.getText();
//        Assert.assertTrue(title.equals("Decision Log Enquiry"));
//        Assert.assertTrue(subTitleText.getText().equals("Decision Log Search Criteria"));
//        Assert.assertTrue(subTitleText1.getText().equals("Click on the icon to hide the search options"));
//        Assert.assertTrue(pleaseNoteText.getText().equals("Please note, at least one of the fields marked (*) is mandatory."));
//        Assert.assertTrue(labelCaseIdText.getText().equals("(*) Case ID:"));
//        Assert.assertTrue(labelUWSalText.getText().equals("UW Salary No.:"));
//        Assert.assertTrue(labelOrgText.getText().equals("Organisation:"));
//        Assert.assertTrue(labelCCText.getText().equals("(*) Centre Code:"));
//        Assert.assertTrue(labelSubDateText.getText().equals("Submission Date:"));
//        Assert.assertTrue(labelAppIdText.getText().equals("Application ID:"));
//        Assert.assertTrue(labelCustIdText.getText().equals("Customer ID:"));
//        Assert.assertTrue(labelProductText.getText().equals("Product:"));
//        Assert.assertTrue(clearBtn.isDisplayed());
//        Assert.assertTrue(searchBtn.isDisplayed());
//        screenshotUtils.takeScreenShot();
//        return seleniumHelper.isTextExistingonPage(DLEText);
//
//    }
//}
