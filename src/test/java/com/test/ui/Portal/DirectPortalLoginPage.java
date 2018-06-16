package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.BasePage;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;

public class DirectPortalLoginPage extends BasePage {

    String nameLoc = "name:usuario";
    String passwordLoc = "name:password";
    String SendLoc= "css:input.opLogonStandardButton[value='Send']";

    String PAGE_STRING = "Please enter your logon information and press \"Send\"";

    @Inject
    SeleniumHelper selHelper;

    @Inject
    private CommonVariables cmnvrbl;

    @Inject
    private CommonFunctions cmnFunctn;

    @Inject
    private ScrenshotUtils screnshotUtils;

    public boolean isValidPageDisplayed() {
        selHelper.waitTillClickableElementExist(passwordLoc);
        selHelper.waitTillClickableElementExist(SendLoc);
        selHelper.waitTillTextExist(PAGE_STRING);
        screnshotUtils.takeScreenShot();
        return selHelper.isTextExistingonPage(PAGE_STRING);
    }



    public void submitValidCredentials(int rowNumber) {
        selHelper.enterText(nameLoc,cmnvrbl.final_CUData.get(rowNumber).get("CustomerID"));
        selHelper.enterText(passwordLoc,cmnvrbl.final_CUData.get(rowNumber).get("Password"));
        setCurrentWindowHandle();
        selHelper.enter(SendLoc);

    }
    private void setCurrentWindowHandle() {
        cmnvrbl.parentWindowHandle=selHelper.getCurrentWindowHandle();
    }

}
