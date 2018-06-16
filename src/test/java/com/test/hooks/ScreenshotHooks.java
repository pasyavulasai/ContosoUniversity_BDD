package com.test.hooks;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.test.configuration.PageFactory;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.SeleniumHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
 * Created by n450777 on 12/04/2016.
 */
public class ScreenshotHooks {
    @Inject
    Provider<WebDriver> driver;
    @Inject
    WebDriver webDriver;
    @Inject
    SeleniumHelper seleniumHelper;
    @Inject
    CommonFunctions commonFunctions;
    @After
    public void takeScreenshot(Scenario scenario) throws Exception {

        // try {
        try {
            scenario.write(webDriver.getCurrentUrl()+"  "+commonFunctions.getCurrentDate());
        } catch(Exception e) {
            System.out.println("unable to getCurrent URl- may be because IE- check screenshots");
        }

        try {
            scenario.embed(((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BYTES), "image/png");
        } catch (Exception e) {
            System.out.println("unable to take screenshot");
        }
        //File scrFile = getScreenShotFile();
        //byte[] data = FileUtils.readFileToByteArray(scrFile);
        try {
            PageFactory.tearDown();
        } catch(Exception e) {
            System.out.println("unable to tearDown orignally");
            seleniumHelper.closeDriver();
        }

        // } catch (Exception e) {
        //      System.out.println("unable to invoke original takeScreenshot");
        //  }
    }
}
