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
//public class SABBAWSHealthcheckCARDUpgrade extends BasePage {
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//    @Inject
//    private UICommonFunctions uiCommonFunctions;
//
//    public String CASEID = null;
//    public String CardUpgradeText = null;
//    public String ACCOUNTNUMBER = null;
//
//
//    @FindBy(xpath = ".//div[@id='main_cabecera_1_text']/span[1]")
//    private PageElement headerText;
//
//    @FindBy(xpath = ".//table[@id='main_datosContextoFijoV2_1_table']/tbody/tr[1]/td[2]")
//    private PageElement caseId;
//
//    @FindBy(xpath = ".//table[@id='main_tabla_1_ROWS_TABLE']/tbody/tr[2]/td[2]/div/div")
//    private PageElement accountHolderName;
//
//    @FindBy(css = "u[id*='AccessKeycontinue']")
//    private PageElement continueBtn;
//
//    public String getACCOUNTNO(int rowIndex) {
//        pageFactory.invalidate(this);
//        ACCOUNTNUMBER = cmnVrbl.final_SABBData.get(rowIndex).get("AccountNo").trim();
//        System.out.println(ACCOUNTNUMBER);
//        return ACCOUNTNUMBER;
//    }
//
//
//    public void getCaseID() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(caseId);
//        boolean value = seleniumHelper.safeCheck(caseId);
//        if (value) {
//            String[] myList = uiCommonFunctions.stringSplitMethod(caseId.getText(), "/");
//            CASEID = myList[0];
//            System.out.println("Card upgrade Case id is : "+CASEID);
//        }
//    }
//
//    public void clickonAccountHolder() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(accountHolderName);
//        boolean value = seleniumHelper.safeCheck(accountHolderName);
//        if (value) {
//            accountHolderName.click();
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
//    public void completeCardUpgradePage() {
//        pageFactory.invalidate(this);
//        getCaseID();
//        clickonAccountHolder();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueButton();
//        seleniumHelper.waitForPageToLoad();
//    }
//
//
//}
