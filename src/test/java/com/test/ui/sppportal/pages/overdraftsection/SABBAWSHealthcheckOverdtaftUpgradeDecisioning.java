//package com.test.ui.sppportal.pages.overdraftsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.CommonVariables;
//import com.test.ui.sppportal.pages.commonfunctions.UICommonFunctions;
//import com.test.ui.sppportal.pages.loginblock.LoginSPPPortal;
//import org.openqa.selenium.support.FindBy;
//
//
//public class SABBAWSHealthcheckOverdtaftUpgradeDecisioning extends BasePage {
//    @Inject
//    private CommonVariables commonVariables;
//    @Inject
//    private LoginSPPPortal loginSPPPortal;
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
//    public boolean sppDebitOverdraftUpgradeDecisioningPageLoad() {
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
//        System.out.println("Overdraft upgrade status is : "+CONDITIONALOFFERSTATUS);
//
//    }
//
//    public void completeOverdraftUpgradeDecisioningDetails(int index) throws Exception {
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
