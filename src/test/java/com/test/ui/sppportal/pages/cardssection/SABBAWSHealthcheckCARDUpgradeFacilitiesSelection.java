//package com.test.ui.sppportal.pages.cardssection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.CommonVariables;
//import com.test.ui.sppportal.pages.loginblock.LoginSPPPortal;
//import org.openqa.selenium.support.FindBy;
//
//
//public class SABBAWSHealthcheckCARDUpgradeFacilitiesSelection extends BasePage {
//    @Inject
//    private CommonVariables commonVariables;
//    @Inject
//    private LoginSPPPortal loginSPPPortal;
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//    @Inject
//    private com.test.ui.sppportal.pages.commonfunctions.UICommonFunctions UICommonFunctions;
//
//    public String PAMANTPAGEText = null;
//
//    @FindBy(xpath = ".//div[@id='main_cabecera_2_text']/span[3]")
//    private PageElement headerText;
//
//    @FindBy(xpath = ".//input[@id='inputText1']")
//    private PageElement odRequested;
//
//    @FindBy(id = "select1")
//    private PageElement odPeriod;
//
//    @FindBy(css = "a[id*='continue']")
//    private PageElement continueBtn;
//
//    @FindBy(xpath = ".//table[@id='main_tabla_3_ROWS_TABLE']/tbody/tr[1]/td[3]/div/div/div/select")
//    private PageElement cardRequested;
//
//    public boolean sppFacilitiesSelectionPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        seleniumHelper.waitforElementVisible(headerText);
//        PAMANTPAGEText = headerText.getText();
//        System.out.println(PAMANTPAGEText);
//        return seleniumHelper.isTextExistingonPage(PAMANTPAGEText);
//    }
//
//    public void enterOverdraftRequesteAmt(String amt) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(odRequested);
//        boolean value = seleniumHelper.safeCheck(odRequested);
//        if (value) {
//            seleniumHelper.enterText(odRequested,amt);
//        }
//    }
//
//    public void selectODRequestedPeriod(String prd) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(odPeriod);
//        boolean value = seleniumHelper.safeCheck(odPeriod);
//        if (value) {
//            seleniumHelper.selectByMatchingVisibleText(odPeriod,prd);
//        }
//    }
//
//    public void clickonContinueButton() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(continueBtn);
//        boolean value = seleniumHelper.safeCheck(continueBtn);
//        if (value) {
//            seleniumHelper.enterUsingJS(continueBtn);
//        }
//    }
//
//
//    public void completeCardRequestorUpgradeFacilitiesSelection(int index) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueButton();
//        seleniumHelper.waitForJSandJQueryToLoad();
//    }
//
//
//    public void selectCardRequested(String crd) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(cardRequested);
//        boolean value = seleniumHelper.safeCheck(cardRequested);
//        if (value) {
//            seleniumHelper.selectByMatchingVisibleText(cardRequested,crd);
//        }
//    }
//
//
//    public void completecardDetails(int index) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueButton();
//    }
//
//
//}
