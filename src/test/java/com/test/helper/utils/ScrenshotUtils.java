package com.test.helper.utils;

import com.google.inject.Inject;
import com.test.applications.cu.web.steps.CUStepDefs;
import com.test.helper.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;


public class ScrenshotUtils extends BasePage {
    @Inject
    private CUStepDefs CUStepDefs;

    @Inject
    CommonFunctions cmnFunct;

    public String folderName = null;
    public String DATESTRING = null;

    final String USER_DIR= System.getProperty("user.dir");
    String screenShotDir= USER_DIR+"\\src\\test\\resources\\TestData\\Screenshots\\";
    String screenShotDir_CucumberReport= USER_DIR+"/target/Screenshots/";


    public String getDateTimestamp() {
        return cmnFunct.getCurrentDate();
    }

public String dateSplitter(String str, String formatter)
{
    String[] strings = str.split(formatter);
    String string = strings[0]+strings[1]+strings[2];
    return string;
}

    public void takeScreenShot() {

        String stringScenarioName = CUStepDefs.testScenarioName;


        folderName=stringScenarioName;
        DATESTRING = getDateTimestamp();
//        System.out.println("-----------" + folderName + "------------------"+DATESTRING);
        File dir = new File("C:SeleniumAutomationScreens\\RetailInternetRegression\\" + folderName);
        dir.mkdir();

        try {
            File scrnsht =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new
                    File("C:\\SeleniumAutomationScreens\\RetailInternetRegression\\"+folderName+"\\"+DATESTRING+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShot(String screenshotName) {






//        System.out.println("-----------" + folderName + "------------------"+DATESTRING);
        File dir = new File(screenShotDir_CucumberReport);
        dir.mkdir();
        System.out.println(dir.toString());

        try {
            File scrnsht =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new
                    File(screenShotDir_CucumberReport+"/"+screenshotName+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
