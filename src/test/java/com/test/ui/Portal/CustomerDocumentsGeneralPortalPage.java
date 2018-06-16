package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;


public class CustomerDocumentsGeneralPortalPage {

    @Inject
    SeleniumHelper selHelper;

    @Inject
    ScrenshotUtils screnshotUtils;

    @Inject
    CommonVariables cmnVrbl;

    @Inject
    CommonFunctions cmnFnctn;

    String customerTypeSearchLoc = "id:typeSearchPersonal";
    String searchButtonLoc = "id:search";
    String customerNoLoc = "id:customerNumber";
    String newButtonLocator = "id:new";
    String backButtonlocator = "id:back";
    String customerFirstDocumentsRecordLocator = "xpath://tbody//td[1]//div//div";
    // the locators are added for 2 different environment. it is an exception. Details= PreProduction, detalle = Production
    String detailsLocator = "xpath://*[@id='Details' or @id='detalle']";




    //This is a single page for Customer Search and Customer Records Page
    //This is single page used for following portal : General, Sales

    public boolean isValidPageDisplayed() {
         selHelper.waitTillClickableElementExist(customerFirstDocumentsRecordLocator);
         screnshotUtils.takeScreenShot();
         return selHelper.isClickableElementExisting(customerFirstDocumentsRecordLocator);
    }


    public void selectFirstDocument() {
        selHelper.enter(customerFirstDocumentsRecordLocator);
        selHelper.isClickableElementExisting(detailsLocator);
        screnshotUtils.takeScreenShot();
        selHelper.enter(detailsLocator);

    }


}
