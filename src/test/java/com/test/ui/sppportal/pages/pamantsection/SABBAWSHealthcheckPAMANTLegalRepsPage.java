//package com.test.ui.sppportal.pages.pamantsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.CommonVariables;
//import com.test.ui.sppportal.pages.loginblock.LoginSPPPortal;
//import org.junit.Assert;
//import org.openqa.selenium.support.FindBy;
//
//
//public class SABBAWSHealthcheckPAMANTLegalRepsPage extends BasePage {
//
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
//    public String PAMANTNAME = null;
//    public String PAMANTDOB = null;
//
//
//    @FindBy(xpath = ".//*[@id='main_cabecera_2_text']")
//    private PageElement headerText;
//
//    @FindBy(xpath = ".//*[@id='main_cabecera_1_text']/span[1]")
//    private PageElement accountDetailsText;
//
//    @FindBy(xpath = ".//table[@id='main_tabla_uno_ROWS_TABLE']/tbody/tr[3]/td[1]/div/div")
//    private PageElement legalRepName;
//
//    @FindBy(xpath = ".//table[@id='main_tabla_uno_ROWS_TABLE']/tbody/tr[3]/td[2]/div/div")
//    private PageElement legalRepDOB;
//
//    @FindBy(css = "u[id*='AccessKeyaddLr']")
//    private PageElement addLegalRepBtn;
//
//    @FindBy(css = "u[id*='AccessKeyremove']")
//    private PageElement removeHolderBtn;
//
//    @FindBy(css = "u[id*='AccessKeyedit']")
//    private PageElement editHolderBtn;
//
//    @FindBy(css = "a[id*='continue']")
//    private PageElement continueBtn;
//
//    @FindBy(css = "u[id*='AccessKeyok']")
//    private PageElement alertOkBtn;
//
//    @FindBy(xpath = ".//table[@id='main_tabla_uno_ROWS_TABLE']/tbody/tr[1]/td[1]/div/div")
//    private PageElement ownerName;
//
//    @FindBy(xpath = ".//table[@id='main_tabla_1_ROWS_SCROLL']/tbody/tr[1]/td[1]/div/div")
//    private PageElement legalRepHolderName;
//
//    @FindBy(css = "u[id*='AccessKeyselect']")
//    private PageElement selectBtn;
//
//    @FindBy(id = "selectHolder")
//    private PageElement holderGrp;
//
//    @FindBy(id = "selectParticipation")
//    private PageElement legalRepType;
//
//    @FindBy(id = "selectSignature")
//    private PageElement signatureRules;
//
//    @FindBy(css = "u[id*='AccessKeyok']")
//    private PageElement okBtn;
//
//
//    public String PAGE_STRING = "Account Holders";
//
//    public boolean validateAccountHoldersPage() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        System.out.println(headerText.getText());
//        Assert.assertEquals("'Contract Management - Customer Holding Relationship Selection' page Header Text is displaying", "Contract Management - Customer Holding Relationship Selection", headerText.getText());
//        System.out.println(accountDetailsText.getText());
//        Assert.assertEquals("'Account Holders' is displaying", "Account Holders", accountDetailsText.getText());
//        return seleniumHelper.isTextExistingonPage(PAGE_STRING);
//    }
//
//
//
//    public String sppPAMANTHolderPageLoad() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        seleniumHelper.waitforElementVisible(headerText);
//        PAMANTPAGEText = headerText.getText();
//        System.out.println(PAMANTPAGEText);
//        return PAMANTPAGEText;
//    }
//
//
//    public void getPAMANTNAME() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(legalRepName);
//        boolean value = seleniumHelper.safeCheck(legalRepName);
//        if (value) {
//            PAMANTNAME = legalRepName.getText();
//            System.out.println("PAMANT name is : " + PAMANTNAME);
//        }
//    }
//
//    public void getPAMANTDOB() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(legalRepDOB);
//        boolean value = seleniumHelper.safeCheck(legalRepDOB);
//        if (value) {
//            PAMANTDOB = legalRepDOB.getText();
//            System.out.println("PAMANT DOB is : " + PAMANTDOB);
//        }
//    }
//
//    public void validateBtnsbeforeSelectingHodler() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        Assert.assertEquals(addLegalRepBtn.getAttribute("disabled").equals("disabled"),true);
//        Assert.assertEquals(removeHolderBtn.getAttribute("disabled").equals("disabled"),true);
//        Assert.assertEquals(editHolderBtn.getAttribute("disabled").equals("disabled"),true);
//
//    }
//
//
//    public void clickonAccountHolder() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(legalRepName);
//        boolean value = seleniumHelper.safeCheck(legalRepName);
//        if (value) {
//            legalRepName.click();
//        }
//    }
//
//    public void clickonAccountOwner() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(ownerName);
//        boolean value = seleniumHelper.safeCheck(ownerName);
//        if (value) {
//            ownerName.click();
//        }
//    }
//
//    public void clickonLegalRep() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(legalRepHolderName);
//        boolean value = seleniumHelper.safeCheck(legalRepHolderName);
//        if (value) {
//            legalRepHolderName.click();
//        }
//    }
//
//    public void clickonSelectBtn() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(selectBtn);
//        boolean value = seleniumHelper.safeCheck(selectBtn);
//        if (value) {
//            seleniumHelper.clickUsingJavascript(selectBtn);
//        }
//    }
//
//    public void selectHolderGroup(String grp) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(holderGrp);
//        boolean value = seleniumHelper.safeCheck(holderGrp);
//        if (value) {
//            seleniumHelper.selectByMatchingVisibleText(holderGrp,grp);
//        }
//    }
//
//    public void selectLegalRepType(String lrt) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(legalRepType);
//        boolean value = seleniumHelper.safeCheck(legalRepType);
//        if (value) {
//            seleniumHelper.selectByMatchingVisibleText(legalRepType,lrt);
//        }
//    }
//
//    public void selectSignatureRules(String sr) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(signatureRules);
//        boolean value = seleniumHelper.safeCheck(signatureRules);
//        if (value) {
//            seleniumHelper.selectByMatchingVisibleText(signatureRules,sr);
//        }
//    }
//
// public void clickonOKBtn() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(okBtn);
//        boolean value = seleniumHelper.safeCheck(okBtn);
//        if (value) {
//            seleniumHelper.clickUsingJavascript(okBtn);
//        }
//    }
//
//    public void clickonAddLegalRepHolder(int index) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementClickable(addLegalRepBtn);
//        boolean value = seleniumHelper.safeCheck(addLegalRepBtn);
//        if (value) {
//           seleniumHelper.clickUsingJavascript(addLegalRepBtn);
//           seleniumHelper.waitForJSandJQueryToLoad();
//            clickonAccountOwner();
//            clickonLegalRep();
//            clickonSelectBtn();
//            seleniumHelper.waitForJSandJQueryToLoad();
//            seleniumHelper.waitForJSandJQueryToLoad();
//            screenshotUtils.takeScreenShot();
//            clickonOKBtn();
//            seleniumHelper.waitForJSandJQueryToLoad();
//            screenshotUtils.takeScreenShot();
//            clickonContinueButton();
//        }
//    }
//
//    public void clickonRemoveHolder() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        boolean value = seleniumHelper.safeCheck(removeHolderBtn);
//        if (value) {
//            seleniumHelper.clickUsingJavascript(removeHolderBtn);
//            seleniumHelper.waitForJSandJQueryToLoad();
//            UICommonFunctions.popUpHandlerPageElement(alertOkBtn);
//            seleniumHelper.waitForJSandJQueryToLoad();
//            screenshotUtils.takeScreenShot();
//            clickonContinueButton();
//            seleniumHelper.waitForJSandJQueryToLoad();
//        }
//    }
//
//
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
//    public void completeHoldingRelationship() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        sppPAMANTHolderPageLoad();
//        getPAMANTNAME();
//        getPAMANTDOB();
////        validateBtnsbeforeSelectingHodler();
//        clickonAccountHolder();
////        validateBtnsafterSelectingHodler();
////        seleniumHelper.waitForJSandJQueryToLoad();
////        screenshotUtils.stepScreens();
////        seleniumHelper.waitForJSandJQueryToLoad();
////        clickonContinueButton();
//    }
//
//
//
//
//}
