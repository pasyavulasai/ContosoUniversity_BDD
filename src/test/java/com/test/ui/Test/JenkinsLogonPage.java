package com.test.ui.Test;


import com.google.inject.Inject;
import com.test.helper.BasePage;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;
import org.openqa.selenium.WebDriver;

public class JenkinsLogonPage extends BasePage {

    @Inject
    WebDriver driver;

    @Inject
    SeleniumHelper seleniumHelper;

    @Inject
    private ScrenshotUtils screnshotUtils;

    @Inject
    private CommonVariables cmnvrbl;

    String userIDLocator = "id:j_username";
    String passwordLocator = "name:j_password";
    String loginButtonLocator= "id:yui-gen1-button";




    public boolean isValidPageDisplayed() {
         seleniumHelper.waitTillClickableElementExist(loginButtonLocator);
         return seleniumHelper.isClickableElementExisting(loginButtonLocator);
    }

    public void submitValidCredentials() throws InterruptedException {
        seleniumHelper.enterText(userIDLocator,cmnvrbl.testData.readProperty("username"));
        seleniumHelper.enterText(passwordLocator,cmnvrbl.testData.readProperty("password"));
        screnshotUtils.takeScreenShot();
        seleniumHelper.enter(loginButtonLocator);
        screnshotUtils.takeScreenShot();

    }

}
