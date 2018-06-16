package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;


public class CustomerDocumentsDetailsGeneralPortalPage {

    @Inject
    SeleniumHelper selHelper;

    @Inject
    ScrenshotUtils screnshotUtils;

    @Inject
    CommonVariables cmnVrbl;

    @Inject
    CommonFunctions cmnFnctn;

    String backLocator = "id:back";



    //This is a single page for Customer Search and Customer Records Page
    //This is single page used for following portal : General, Sales

    public boolean isValidPageDisplayed() {
         selHelper.waitTillClickableElementExist(backLocator);
         screnshotUtils.takeScreenShot();
         return selHelper.isClickableElementExisting(backLocator);
    }

}
