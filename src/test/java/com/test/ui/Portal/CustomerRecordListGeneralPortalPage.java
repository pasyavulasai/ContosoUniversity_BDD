package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;


public class CustomerRecordListGeneralPortalPage {

    @Inject
    SeleniumHelper selHelper;

    @Inject
    ScrenshotUtils screnshotUtils;

    @Inject
    CommonVariables cmnVrbl;

    @Inject
    CommonFunctions cmnFnctn;

    String customerTypeSearchLoc = "id:typeSearchPersonal";
    String newButtonLocator = "id:new";
    String backButtonlocator = "id:back";
    String customerRecordLocator = "xpath://tbody//td[1]//div";
    String selectLocator = "id:select";



    public boolean isValidPageDisplayed() {
         selHelper.waitTillClickableElementExist(customerTypeSearchLoc);
         screnshotUtils.takeScreenShot();
         return selHelper.isClickableElementExisting(customerTypeSearchLoc);
    }


    public boolean isRecordDisplayed() {
        selHelper.waitTillClickableElementExist(newButtonLocator);
        selHelper.waitTillClickableElementExist(backButtonlocator);
        screnshotUtils.takeScreenShot();
        selHelper.isElementContainingValue(customerRecordLocator);
        String customerName= selHelper.getElementText(customerRecordLocator);
        System.out.println(selHelper.getElementText(customerRecordLocator));

        return cmnFnctn.isStringNotEqualTo(customerName, cmnVrbl.threeDashes);
    }

    public void selectFirstCustomer() {
        selHelper.enter(customerRecordLocator);
        selHelper.waitTillClickableElementExist(selectLocator);
        screnshotUtils.takeScreenShot();
        selHelper.enter(selectLocator);
    }

}
