package com.test.ui.Portal;

import com.google.inject.Inject;
import com.test.helper.BasePage;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class PortalLoginPage extends BasePage {

    String userLoc = "name:calias";
    String passwordLoc = "name:cpassword";
    String okButtonLoc= "id:submit";

    //This is a single sign on Portal Page

    @Inject
    SeleniumHelper selHelper;



    @Inject
    private CommonFunctions cmnFunctn;

    @Inject
    private ScrenshotUtils screnshotUtils;

    public void submitCredentials(int rowIndex) throws InterruptedException {

        //int rowIndex = cmnFunctn.getRowIndex(cmnvrbl.pre_PortalRows, cmnvrbl.pre_PortalRows.get(scenarioNo).get("CustomerID"));
        //System.out.println(cmnvrbl.pre_PortalRows.get(rowIndex).get("CustomerID"));
        //System.out.println(cmnvrbl.pre_PortalRows.get(rowIndex).get("Password"));
        System.out.println("4. before entering credentials and number rof window handles are"+driver.getWindowHandles());
        cmnVrbl.parentWindowHandle=selHelper.getCurrentWindowHandle();
        String userName = cmnVrbl.final_CUData.get(rowIndex).get("CustomerID");
        String password = cmnVrbl.final_CUData.get(rowIndex).get("Password");
        System.out.println("Username is "+userName);
        System.out.println("Password is "+password);
        selHelper.waitForPageToLoad();
        selHelper.enterTextWithoutCleaning(userLoc,userName);
        selHelper.enterTextWithoutCleaning(passwordLoc,password);
        screnshotUtils.takeScreenShot();
        selHelper.enter(okButtonLoc);
        Thread.sleep(10000);// wait long enough for General Portal Dashboard to open completely
        //System.out.println("5. After pressing ok and number rof window handles are"+driver.getWindowHandles());
        ///Thread.sleep(3000);

    }



    public void followWorkArounds() throws InterruptedException {
        //System.out.println("1. opened main url and number of window handles are"+driver.getWindowHandles());
        selHelper.acceptAlertIfExists();
        ///Thread.sleep(3000);
    }

    public void donotacceptAlertIfExists() throws InterruptedException {
        //System.out.println("1. opened main url and number of window handles are"+driver.getWindowHandles());
        selHelper.donotacceptAlertIfExists();
        ///Thread.sleep(3000);
    }

    public void switchDriverToNewWindow() {
        String windowHandle = selHelper.getNewWindowOutOfSet();
        cmnVrbl.child1WindowHandle=windowHandle;
        selHelper.switchToWindow(windowHandle);
        screnshotUtils.takeScreenShot();
        //TODO
        // Assert
        System.out.println(driver.getTitle());
    }

    public void switchAndCloseParentWindow() {
        //selHelper.setWindow("parent",cmnVrbl.parentWindowHandle);
        selHelper.switchToWindow(cmnVrbl.parentWindowHandle);
        //screnshotUtils.takeScreenShot();
        System.out.println("window General is now closed");
        driver.close();
        //System.out.println(driver.manage().getCookies());

        //selHelper.closeDriver();

    }

    private void embedScreenshot(Scenario scenario) {
        scenario.embed(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES), "image/png");
    }

    public void switchBackToNewWindow() {
        selHelper.switchToWindow(cmnVrbl.child1WindowHandle);
    }

    public void markScenarioToNotDeleteCookies() {
        System.setProperty("NotDeleteCookiesScenario","true");
    }
}
