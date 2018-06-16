package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.BasePage;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;
import org.openqa.selenium.WebElement;

public class PortalDashBoard extends BasePage {


    String toolsLocator = "xpath://div[contains(.,'Tools')]";
    String documentEnquiriesLocator ="xpath://div[contains(.,'Document Enquiries')]";

    @Inject
    SeleniumHelper selHelper;

    @Inject
    private CommonVariables cmnvrbl;

    @Inject
    private CommonFunctions cmnFunctn;

    @Inject
    private ScrenshotUtils screnshotUtils;



    public boolean isValidPageDisplayed() {

        selHelper.waitForPageToLoad();
        WebElement element = selHelper.getElementUsingTagNameAndAttribute("frame","title","Left Menu General");

        selHelper.switchFrameByElementName(element);
        selHelper.waitTillClickableElementExist(toolsLocator);
        selHelper.waitTillClickableElementExist(documentEnquiriesLocator);
        screnshotUtils.takeScreenShot();
        return selHelper.isClickableElementExisting(toolsLocator);
        //return true;
    }
}
