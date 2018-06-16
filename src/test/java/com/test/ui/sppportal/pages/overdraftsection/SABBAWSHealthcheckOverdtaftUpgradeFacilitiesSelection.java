//package com.test.ui.sppportal.pages.overdraftsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.CommonVariables;
//import org.openqa.selenium.support.FindBy;
//
//
//public class SABBAWSHealthcheckOverdtaftUpgradeFacilitiesSelection extends BasePage {
//    @Inject
//    private CommonVariables commonVariables;
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//
//
//    public String ODUpgradeText = null;
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
//
//    public boolean sppODFacilitiesSelectionPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        seleniumHelper.waitforElementVisible(headerText);
//        ODUpgradeText = headerText.getText();
//        System.out.println(ODUpgradeText);
//        return seleniumHelper.isTextExistingonPage(ODUpgradeText);
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
//    public void completeOverdraftRequestorUpgradeFacilitiesSelection(int index) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
////        enterOverdraftRequesteAmt(commonVariables.sabbAWSHealthcheck.get(index).get("ODAmount").trim());
////        selectODRequestedPeriod(commonVariables.sabbAWSHealthcheck.get(index).get("ODPeriod").trim());
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueButton();
//        seleniumHelper.waitForJSandJQueryToLoad();
//    }
//
//
//
//}
