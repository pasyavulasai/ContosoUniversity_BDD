package com.test.ui.CustomerFacingPages;

import com.test.helper.BasePage;

public class EmploymentFinancePage extends BasePage {

    String doYouLiveinUKYes = "css:label[for='liveInUk0']";


    public boolean isValidPageDisplayed() {
        selHelper.waitForJSandJQueryToLoad();
        selHelper.printPageSource();
        selHelper.waitTillVisibleElementExist(doYouLiveinUKYes);
        return selHelper.isVisibleElementExisting(doYouLiveinUKYes);
    }

    public void submitDefaultValidDetails() {

    }


}
