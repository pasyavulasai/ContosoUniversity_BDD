package com.test.ui.Portal.General.DocumentControl;

import com.google.inject.Inject;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;


public class CustomerSearchPage {

    @Inject
    SeleniumHelper selHelper;

    @Inject
    ScrenshotUtils screnshotUtils;

    @Inject
    CommonFunctions cmnFnctn;

    @Inject
    CommonVariables cmnVrbl;

    String searchLocator = "id:buscar";
    String detailsLoc = "id:Details";
    String statusLoc = "id:status";
    String customerRecordLocator = "xpath://tbody//td[1]//div/div";



    public boolean isValidPageDisplayed() {
        selHelper.waitTillClickableElementExist(detailsLoc);
        selHelper.waitTillClickableElementExist(searchLocator);
        selHelper.waitTillClickableElementExist(statusLoc);
        screnshotUtils.takeScreenShot();
        return selHelper.isClickableElementExisting(detailsLoc) && selHelper.isClickableElementExisting(searchLocator) && selHelper.isClickableElementExisting(statusLoc);
    }


    public boolean isTableContainingAnEntry() {
        selHelper.isElementContainingValue(customerRecordLocator);
        String customerName= selHelper.getElementText(customerRecordLocator);
        System.out.println(selHelper.getElementText(customerRecordLocator));

        return cmnFnctn.isStringNotEqualTo(customerName, cmnVrbl.threeDashes);
    }
}
