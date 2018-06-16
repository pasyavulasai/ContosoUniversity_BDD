package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;


public class CustomerSearchGeneralPortalPage {

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




    //This is a single page for Customer Search and Customer Records Page
    //This is single page used for following portal : General, Sales

    public boolean isValidPageDisplayed() {
         selHelper.waitTillClickableElementExist(customerTypeSearchLoc);
         screnshotUtils.takeScreenShot();
         return selHelper.isClickableElementExisting(customerTypeSearchLoc);
    }

    public void submitCustomerSearchRequest(int rowNumber) {
        selectTypeOfSearch();
        enterCustomerNumber(rowNumber);
        submitSearch();
    }

    private void enterCustomerNumber(int rowNumber) {
        selHelper.waitTillClickableElementExist(customerNoLoc);
        selHelper.enterTextWithoutCleaning(customerNoLoc,cmnVrbl.final_CUData.get(rowNumber).get("FNumber"));
    }

    private void submitSearch() {
        selHelper.waitTillClickableElementExist(searchButtonLoc);
        selHelper.enter(searchButtonLoc);
    }

    private void selectTypeOfSearch() {
        selHelper.selectByMatchingVisibleText(customerTypeSearchLoc,"Customer Number");
    }


}
