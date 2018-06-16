//package com.test.ui.sppportal.pages.cardssection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.support.FindBy;
//
//import java.util.Iterator;
//import java.util.Set;
//
//
//public class SABBAWSHealthcheckDebitCardUpgradeCreditChecks extends BasePage {
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//
//    public String CardUpgradeText = null;
//
//
//    @FindBy(xpath = ".//div[@id='botones']/span[2]/div/span[3]")
//    private PageElement headerText;
//
//    @FindBy(css = "u[id*='AccessKeycontinue']")
//    private PageElement continueBtn;
//
//    @FindBy(css = "u[id*='AccessKeyok']")
//    private PageElement oKBtn;
//
//    @FindBy(css = "u[id*='AccessKeybotOk']")
//    private PageElement popupCloseWindowKBtn;
//
//
//    public boolean sppDebitCardUpgradeCreditChecksPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        seleniumHelper.waitforElementVisible(headerText);
//        CardUpgradeText = headerText.getText();
//        System.out.println(CardUpgradeText);
//        return seleniumHelper.isTextExistingonPage(CardUpgradeText);
//    }
//
//    public void clickonContinueBtn() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(continueBtn);
//        boolean value = seleniumHelper.safeCheck(continueBtn);
//        if (value) {
//            seleniumHelper.clickUsingJavascript(continueBtn);
//        }
//    }
//
//    public void clickonEmployeeOKBtn() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(oKBtn);
//        boolean value = seleniumHelper.safeCheck(oKBtn);
//        if (value) {
//            seleniumHelper.clickUsingJavascript(oKBtn);
//        }
//    }
//
//
//    public void closeAlert() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        String subWindowHandler = null;
//        Set<String> handles = driver.getWindowHandles(); // get all window handles
//        System.out.println( handles.size());
//        Iterator<String> iterator = handles.iterator();
//        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
//        while (iterator.hasNext()) {
//            subWindowHandler = iterator.next();
////            System.out.println("--------" + subWindowHandler + "------------------------");
//        }
//        driver.switchTo().window(subWindowHandler); // switch to popup window
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popupCloseWindowKBtn);
//        driver.switchTo().window(parentWindowHandler);
//    }
//
//    public void completeDebitCardUpgradeCreditChecks(int index) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueBtn();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonEmployeeOKBtn();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        closeAlert();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueBtn();
//        seleniumHelper.waitForJSandJQueryToLoad();
//    }
//
//
//}
