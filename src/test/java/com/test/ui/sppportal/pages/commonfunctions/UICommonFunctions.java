package com.test.ui.sppportal.pages.commonfunctions;

import com.google.inject.Inject;
import com.test.extension.PageElement;
import com.test.helper.BasePage;
import com.test.helper.utils.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;


public class UICommonFunctions extends BasePage {
    public String APPLICATION_STATUS = null;
    public String SPP_PRE_URL = null;
    public String SPP_PROD_URL = null;

    @Inject
    private SeleniumHelper seleniumHelper;

    public void popUpHandlerPageElement(PageElement pageElement) {
        pageFactory.invalidate(this);
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        int aa = handles.size();
        System.out.println(aa);
        Iterator<String> iterator = handles.iterator();
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
//            System.out.println("--------" + subWindowHandler + "------------------------");
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
        boolean value = safeCheck(pageElement);
        if (value) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageElement);
        }
        driver.switchTo().window(parentWindowHandler);
    }

    public boolean safeCheck(PageElement pageElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(pageElement));
            return true;
        } catch (Exception t) {
            return false;
        }
    }

    public void logintoSalesPortal(String environment, String scenarioType, String accountNo) {
        pageFactory.invalidate(this);

        if (environment.equals("Pre")) {
            salesPortlaPreEnvironment(scenarioType, accountNo);
        } else if (environment.equals("Prod")) {
            salesPortlaProdEnvironment(scenarioType, accountNo);
        }
    }

    public String salesPortlaPreEnvironment(String assembly, String accountNo) {
        pageFactory.invalidate(this);
        switch (assembly) {
            case "UKRAC_OUTSTANDING_APPS":
                break;
            case "UKRAC_DECISION_LOG_SEARCH":
                SPP_PRE_URL = "http://saba.santanderuk.pre.corp/PORINT_ENS/BtoChannelDriver.ssobto?dse_operationName=PO_Index&in.NombrePortal=SPP&agregador=SPP";
                break;
            case "PAMANB_ENS":
                break;
            case "CDUPGB_ENS":
                break;
            case "OVUPGB_ENS":
                break;
            default:
                System.out.println("Test Assembly type choosen is not valid: " + assembly);
        }
        return "safdas";
    }

    public String salesPortlaProdEnvironment(String assembly, String accountNo) {
        pageFactory.invalidate(this);
        switch (assembly) {
            case "UKRAC_OUTSTANDING_APPS":
                break;
            case "UKRAC_DECISION_LOG_SEARCH":
                break;
            case "PAMANB_ENS":
                break;
            case "CDUPGB_ENS":
                break;
            case "OVUPGB_ENS":
                break;
            default:
                System.out.println("Test Assembly type choosen is not valid: " + assembly);
        }
        return "safdas";
    }


    public String[] stringSplitMethod(String inputText, String splitChar) {
        String[] values = inputText.split(splitChar);
//                for (String arrValues:values) {
//                        System.out.println(arrValues);
//                }
        return values;
    }

    public String getApplicaitonStatus(WebElement webElement) throws Exception {
        pageFactory.invalidate(this);
        seleniumHelper.waitforElementVisible(webElement);
        boolean value = seleniumHelper.safeCheck(webElement);
        if (value) {
            String status = webElement.getText();
//            System.out.println("----------------------" + status);
            if (status.contains("Decline")) {
                APPLICATION_STATUS = "Decline";
            } else if (status.contains("Review")) {
                APPLICATION_STATUS = "Review";
            } else if (status.contains("Approve")) {
                APPLICATION_STATUS = "Approve";
            }
        }
        return APPLICATION_STATUS;
    }

}
