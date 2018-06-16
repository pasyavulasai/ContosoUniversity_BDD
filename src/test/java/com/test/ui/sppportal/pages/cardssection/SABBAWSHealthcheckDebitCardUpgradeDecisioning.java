//package com.test.ui.sppportal.pages.cardssection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.sppportal.pages.commonfunctions.UICommonFunctions;
//import org.openqa.selenium.support.FindBy;
//
//
//public class SABBAWSHealthcheckDebitCardUpgradeDecisioning extends BasePage {
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//    @Inject
//    private UICommonFunctions uiCommonFunctions;
//
//    public String CardUpgradeText = null;
//    public String CONDITIONALOFFERSTATUS = null;
//
//    @FindBy(xpath = ".//div[@id='main_cabecera_2_text']/span[3]")
//    private PageElement headerText;
//
//    @FindBy(xpath = ".//div[@id='main_newAccount_ProductDecisioning_1.label9_repaint']/span")
//    private PageElement conditionalOfferStatus;
//
//    public boolean sppDebitCardUpgradeDecisioningPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        seleniumHelper.waitforElementVisible(headerText);
//        CardUpgradeText = headerText.getText();
//        System.out.println(CardUpgradeText);
//        return seleniumHelper.isTextExistingonPage(CardUpgradeText);
//    }
//
//    public void getConditionalOfferStatusText() throws Exception {
//        pageFactory.invalidate(this);
//        CONDITIONALOFFERSTATUS = uiCommonFunctions.getApplicaitonStatus(conditionalOfferStatus);
//        System.out.println("Card upgrade status is : "+CONDITIONALOFFERSTATUS);
//    }
//
//    public void completeDebitCardUpgradeDecisioningDetails(int index) throws Exception {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        getConditionalOfferStatusText();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//
//    }
//
//
//}
