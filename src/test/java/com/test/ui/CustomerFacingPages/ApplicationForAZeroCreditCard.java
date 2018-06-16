package com.test.ui.CustomerFacingPages;

import com.test.helper.BasePage;

public class ApplicationForAZeroCreditCard extends BasePage {

    String doYouLiveinUKYes = "css:label[for='liveInUk0']";
    String agreeCreditSearchYes = "css:label[for='agreeCreditSearch0']";
    String applyNowlocator = "id:applyNow";

    public boolean isValidPageDisplayed() {
        selHelper.waitForJSandJQueryToLoad();
        selHelper.printPageSource();
        selHelper.waitTillVisibleElementExist(doYouLiveinUKYes);
        return selHelper.isVisibleElementExisting(doYouLiveinUKYes);
    }

    public void submitDefaultValidDetails() {
        selectLiveInUKYes();
        selectCreditCheckYes();
        selectApplyNow();
    }

    private void selectApplyNow() {
        selHelper.waitTillClickableElementExist(applyNowlocator);
        selHelper.enter(applyNowlocator);
    }

    private void selectCreditCheckYes() {
        selHelper.waitTillClickableElementExist(agreeCreditSearchYes);
        selHelper.enter(agreeCreditSearchYes);
    }

    private void selectLiveInUKYes() {
        selHelper.waitTillClickableElementExist(doYouLiveinUKYes);
        selHelper.enter(doYouLiveinUKYes);


    }
}
