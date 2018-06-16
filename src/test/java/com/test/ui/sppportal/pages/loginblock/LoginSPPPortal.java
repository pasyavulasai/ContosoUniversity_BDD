package com.test.ui.sppportal.pages.loginblock;

import com.google.inject.Inject;
import com.test.helper.BasePage;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.extension.PageElement;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;

public class LoginSPPPortal extends BasePage {
    @Inject
    private ScrenshotUtils screnshotUtils;
    @Inject
    private SeleniumHelper seleniumHelper;

    @FindBy(css = ".title")
    private PageElement userIdentificationTitle;

    @FindBy(css = "#identificacion>p")
    private PageElement userloginInformation;

    @FindBy(xpath = ".//*[@id='identificacion']/div[1]/label")
    private PageElement userIdLbl;

    @FindBy(xpath = ".//*[@id='identificacion']/div[2]/label")
    private PageElement pwdLbl;

    @FindBy(name = "usuario")
    private PageElement userId;

    @FindBy(name = "password")
    private PageElement pwd;

    @FindBy(partialLinkText = "Have you forgotten your password or has it been locked?")
    private PageElement forgotPasswordLink;

    @FindBy(xpath = ".//*[@id='form_auth']/div[3]/span[1]/input")
    private PageElement sendBtn;

    String PAGE_STRING = "Please enter your logon information and press \"Send\"";

    public boolean validateLoginPage()
    {
        pageFactory.invalidate(this);
        System.out.println(userIdentificationTitle.getText());
        Assert.assertEquals("'User Identification' is displaying","User Identification",userIdentificationTitle.getText());
        System.out.println(userloginInformation.getText());
        Assert.assertEquals("Please enter your logon information and press \"Send\" is displaying",PAGE_STRING,userloginInformation.getText());
        System.out.println(userIdLbl.getText());
        Assert.assertEquals("User: label is displaying","User:",userIdLbl.getText());
        System.out.println(pwdLbl.getText());
        Assert.assertEquals("Password: label is displaying","Password:",pwdLbl.getText());
        System.out.println(forgotPasswordLink.getText());
        Assert.assertEquals("Have you forgotten your password or has it been locked? is displaying","Have you forgotten your password or has it been locked?",forgotPasswordLink.getText());
        System.out.println(userId.isDisplayed());
        Assert.assertEquals("User name textbox is displaying",true,userId.isDisplayed());
        System.out.println(pwd.isDisplayed());
        Assert.assertEquals("Password textbox is displaying",true,pwd.isDisplayed());
        System.out.println(sendBtn.isDisplayed());
        Assert.assertEquals("Send button is displaying",true,sendBtn.isDisplayed());
        return seleniumHelper.isTextExistingonPage(PAGE_STRING);
    }

    public void enterUserId(String uid) {
        pageFactory.invalidate(this);
        seleniumHelper.waitforElementClickable(userId);
        boolean value = seleniumHelper.safeCheck(userId);
        if (value) {
            seleniumHelper.enterText(userId, uid);
        }
    }

    public void enterPassword(String pd) {
        pageFactory.invalidate(this);
        seleniumHelper.waitforElementClickable(pwd);
        boolean value = seleniumHelper.safeCheck(pwd);
        if (value) {
            seleniumHelper.enterText(pwd, pd);
        }
    }

    public void clickonSendBtn() {
        pageFactory.invalidate(this);
        seleniumHelper.waitforElementVisible(sendBtn);
        boolean value = seleniumHelper.safeCheck(sendBtn);
        if (value) {
            sendBtn.click();
        }
    }

    public void logindetails(int rowIndex) {
        pageFactory.invalidate(this);
        seleniumHelper.waitForPageToLoad();
        String userName = cmnVrbl.final_CUData.get(rowIndex).get("CustomerID").trim();
        String password = cmnVrbl.final_CUData.get(rowIndex).get("Password").trim();
        System.out.println("Username is "+userName);
        System.out.println("Password is "+password);
        seleniumHelper.waitForPageToLoad();
        enterUserId(userName);
        enterPassword(password);
        screnshotUtils.takeScreenShot();
        clickonSendBtn();
    }

    public void markScenarioToNotDeleteCookies() {
        System.setProperty("NotDeleteCookiesScenario","true");
    }


}
