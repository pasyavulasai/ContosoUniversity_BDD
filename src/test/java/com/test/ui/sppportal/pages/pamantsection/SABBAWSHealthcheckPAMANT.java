//package com.test.ui.sppportal.pages.pamantsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ExcelUtils;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.CommonVariables;
//import com.test.ui.sppportal.pages.commonfunctions.UICommonFunctions;
//import org.junit.Assert;
//import org.openqa.selenium.support.FindBy;
//
//
//public class SABBAWSHealthcheckPAMANT extends BasePage {
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//    @Inject
//    private UICommonFunctions uiCommonFunctions;
//    @Inject
//    protected CommonVariables cmnVrbl;
//    @Inject
//    private ExcelUtils excelUtils;
//
//    public String ACCOUNTNUMBER = null;
//    public String CASEID = null;
//    public String TIMESTAMP = null;
//    public String PAMANTPAGEText = null;
//
//
//    @FindBy(xpath = ".//*[@id='main_cabecera_1_cabecera']/div")
//    private PageElement headerText;
//
//    @FindBy(xpath = ".//*[@id='main_cabecera_2_text']/span[1]")
//    private PageElement accountDetailsText;
//
//    @FindBy(xpath = ".//*[@id='main_cabecera_3_text']/span[1]")
//    private PageElement accountDetailsTableHeaderText;
//
//    @FindBy(xpath = ".//*[@id='main_cabecera_4_text']/span[1]")
//    private PageElement accountFacilitiesText;
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
//    @FindBy(xpath = ".//div[@id='main_cabecera_2_text']/span[1]")
//    private PageElement accountHoldersHeaderText;
//
//    public String PAGE_STRING = "Account Facilities";
//
//
//    public String getACCOUNTNO(int rowIndex) {
//        pageFactory.invalidate(this);
//        ACCOUNTNUMBER = cmnVrbl.final_SABBData.get(rowIndex).get("AccountNo").trim();
//        System.out.println(ACCOUNTNUMBER);
//        return ACCOUNTNUMBER;
//    }
//
//    public boolean validateApplicationDataPage() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        System.out.println(headerText.getText());
//        Assert.assertEquals("'ContractManagement' page Header Text is displaying", "Contract Management - Change Holding Relationship Selection", headerText.getText());
//        System.out.println(accountDetailsText.getText());
//        Assert.assertEquals("'Account Details' is displaying", "Account Details", accountDetailsText.getText());
//        System.out.println(accountDetailsTableHeaderText.getText());
//        Assert.assertEquals("Account Holders is displaying", "Account Holders", accountDetailsTableHeaderText.getText());
//        System.out.println(accountFacilitiesText.getText());
//        Assert.assertEquals("Please enter your logon information and press \"Send\" is displaying", PAGE_STRING, accountFacilitiesText.getText());
//        return seleniumHelper.isTextExistingonPage(PAGE_STRING);
//    }
//
//
//    public String sppPAMANTPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementVisible(headerText);
//        PAMANTPAGEText = headerText.getText();
//        System.out.println(PAMANTPAGEText);
//        return PAMANTPAGEText;
//    }
//
//    public String sppProdPAMANTPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementVisible(accountHoldersHeaderText);
//        String PAMANTPAGEText1 = accountHoldersHeaderText.getText();
//        System.out.println(accountHoldersHeaderText);
//        return PAMANTPAGEText1;
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
//            System.out.println("PAMANT Case id is : " + CASEID);
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
//    public void completeHoldingRelationship(int rowNum) throws Exception {
//        pageFactory.invalidate(this);
//        getCaseID();
////        updateCASEIDtoEXCEL(rowNum);
//        clickonAccountHolder();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.waitForJSandJQueryToLoad();
//        clickonContinueButton();
//    }
//
//
//}
