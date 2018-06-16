package com.test.ui.taas.pages;

import com.test.extension.PageElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(className = "list-title")
    public PageElement allUpdates;

    @FindBy(className = "aui-header-logo-device")
    public PageElement confluenceLogo;

    public void verifyLabel(){
        this.allUpdates.isDisplayed();
    }

    public void clickConfluence(){
        this.confluenceLogo.click();
    }
}
