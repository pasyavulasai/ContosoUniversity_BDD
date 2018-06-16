package com.test.ui.Test;

import com.google.inject.Inject;
import com.test.helper.BasePage;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import org.openqa.selenium.WebDriver;

public class JenkinsDashboard extends BasePage {
    @Inject
    WebDriver driver;

    @Inject
    private SeleniumHelper seleniumHelper;

    @Inject
    private ScrenshotUtils screnshotUtils;

//    @Inject
//    private CommonVariables cmnvrbl;

//    @Inject
//    private FileUtils fileUtils;

    final String USER_DIR= System.getProperty("user.dir");
    String screenShotDir= USER_DIR+"\\src\\test\\resources\\TestData\\Screenshots\\";


    String featuresLinkLocator = "partiallink:Features";
    String tagsLinkLocator = "partiallink:Tags";
    String failuresLinkLocator = "partiallink:Failures";




    public boolean isValidPageDisplayed() {
         seleniumHelper.waitTillClickableElementExist(featuresLinkLocator);
        //screnshotUtils.takeScreenShot();
         return seleniumHelper.isClickableElementExisting(featuresLinkLocator);
    }


    public void takeReportScreenshots() throws InterruptedException {
        getOverviewReportScreenshot();
        getTagsReportScreenshot();
        //getFailureReportScreenshot();

    }

    private void getFailureReportScreenshot() {
        seleniumHelper.waitTillClickableElementExist(failuresLinkLocator);
        seleniumHelper.enter(failuresLinkLocator);
        //2 screenshot are taken because FailureReport screenshot is overwritten on subsequent execution
        screnshotUtils.takeScreenShot();
        screnshotUtils.takeScreenShot("FailureReport");

    }

    private void getTagsReportScreenshot() {
        seleniumHelper.waitTillClickableElementExist(tagsLinkLocator);
        seleniumHelper.enter(tagsLinkLocator);
        screnshotUtils.takeScreenShot();
        screnshotUtils.takeScreenShot("TagsReport");
        //FileUtils.copyFile();
        //FileUtils.de

    }

    private void getOverviewReportScreenshot() throws InterruptedException {
        Thread.sleep(1000);
        screnshotUtils.takeScreenShot();
        screnshotUtils.takeScreenShot("FeatureReport");
    }
}
