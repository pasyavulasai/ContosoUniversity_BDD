package com.test.helper.utils;


import com.google.inject.Inject;
import com.test.ui.CommonVariables;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;


public class SeleniumHelper {
    WebDriver driver;

    @Inject
    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    WebElement element;

    @Inject
    CommonFunctions cmnFunction;

    @Inject
    CommonVariables cmnVrbl;

    public void
    waitForPageToLoad() {
        new WebDriverWait(driver, 15).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        System.out.println("waited 30 secs for JQuery and JS Load to finish");
        return wait.until(jQueryLoad) && wait.until(jsLoad);


    }


    public boolean waitforElementVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementVisible(String webElementString) {
        try {
            WebElement webElement = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementToBeVisibleAndClickable(WebElement webElement) {
        try {
            return waitforElementVisible(webElement) && waitforElementClickable(webElement);
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitLongerforElementVisible(WebElement webElement, Long timeinSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeinSeconds);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }


    public boolean safeCheck(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            System.out.println("User Thrown Alert: Webelement was not visible at the time of run " + webElement);
            return false;
        }

    }

    public boolean safeTextCheck(String string) {
        try {
            boolean bol = driver.getPageSource().contains(string);
            return bol;
        } catch (Exception t) {
            System.out.println("User Thrown Alert: Safe Text not existing at the time of run " + string);
            return false;
        }
    }



    public void enter(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.click();
        } catch (Exception t) {
            System.out.println("unable to click on WebELement using enter(WebElement webElement): " + webElement);
            throw t;
        }

    }

    public void enter(String elementLocator) {
        try {
            System.out.println("about to click on element with locator " + elementLocator);
            WebElement element = getElement(elementLocator);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            waitTillClickableElementExist(elementLocator);
            element.click();
            System.out.println("element clicked" + elementLocator);
        } catch (Exception t) {
            System.out.println("unable to click on the element using enter(String elementLocator): " + elementLocator);
            throw t;
        }

    }

    public void enterUsingSendKeys(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.RETURN);
        } catch (Exception t) {
            throw t;
        }

    }

    public void enterUsingSendKeys(String webElementString) {
        try {
            WebElement element = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(Keys.RETURN);
        } catch (Exception t) {
            System.out.println("unable to click on the element usingenterUsingSendKeys(String webElementString): " + webElementString);
            throw t;
        }

    }

    public void enterUsingKeys(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.ENTER);
        } catch (Exception t) {
            throw t;
        }

    }

    public void useTABKey(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.ENTER);
        } catch (Exception t) {
            throw t;
        }

    }

    public void useTabPlusEnterKey(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.TAB, Keys.ENTER);
        } catch (Exception t) {
            System.out.println("unable to click on the element usingenter Using useTabPlusEnterKey(WebElement webElement): " + webElement);
            throw t;
        }

    }

    public void enterUsingJS(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
        } catch (Exception t) {
            throw t;
        }

    }

    public void enterText(WebElement webElement, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElement,String string) : " + webElement);
            throw t;
        }

    }

    public void enterText(String webElementString, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElementString,String string) : " + webElementString);
            throw t;
        }

    }

    public void enterTextWithoutCleaning(String webElementString, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterTextWithoutCleaning(String webElementString,String string) : " + webElementString);
            throw t;
        }

    }

    public boolean isTextExistingonPage(String string) {
        try {
            waitTillTextExist(string);
            waitForPageToLoad();
            waitTillTextExist(string);
            boolean bol = driver.getPageSource().contains(string);
            return bol;
        } catch (Exception t) {
            System.out.println("string not existing on the page : " + string);
            throw t;
        }

    }

    public boolean areStringsInListExistingonPage(List<String> list) {
        int count = list.size();
        boolean bol = false;
        boolean finalresult = true;
        for (int i = 0; i < count; i++) {
            bol = (isTextExistingonPage(list.get(i)));
            finalresult = bol && finalresult;
        }
        return finalresult;

    }

    public boolean isTextNotExistingonPage(String string) {
        try {
            waitTillTextExist(string);
            waitForPageToLoad();
            boolean bol = driver.getPageSource().contains(string);
            return bol;
        } catch (Exception t) {
            System.out.println("string not existing on the page : " + string);
            throw t;
        }

    }

    public void printPageSource() {
        try {
            waitForPageToLoad();
            System.out.println(driver.getPageSource());
        } catch (Exception t) {
            throw t;
        }

    }

    public int getNoOfWindowHandles() {
        try {
            waitForPageToLoad();
            return driver.getWindowHandles().size();
        } catch (Exception t) {
            throw t;
        }

    }

    public WebElement getElementofListUsingAttribute(List<WebElement> webElements, String attributeName, String attributeValue) {
        try {
            WebElement element = null;
            for (int i = 0; i < webElements.size(); i++) {

                if (webElements.get(i).getAttribute(attributeName).equals(attributeValue)) {
                    element = webElements.get(i);
                } else {
                    //System.out.println("No Element found");
                }
            }
            return element;
        } catch (Exception t) {
            throw t;
        }

    }

    public List<WebElement> getElementList(String locatorType, String locatorValue) {
        List<WebElement> elements;
        switch (locatorType) {
            case "tagname":
                elements = driver.findElements(By.tagName(locatorValue));
                break;
            case "xpath":
                elements = driver.findElements(By.xpath(locatorValue));
                break;
            case "name":
                elements = driver.findElements(By.name(locatorValue));
                break;
            case "css":
                elements = driver.findElements(By.cssSelector(locatorValue));
                break;
            default:
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type or locatorName");

        }
        return elements;

    }

    public List<WebElement> getElementList(String strLocator) {
        List<WebElement> elements;

        waitForPageToLoad();
        String strLocatorType = (strLocator.split(":", 2)[0]).toLowerCase();
        String strLocatorValue = (strLocator.split(":", 2)[1]);


        switch (strLocatorType) {
            case "tagname":
                elements = driver.findElements(By.tagName(strLocatorValue));
                break;
            case "xpath":
                elements = driver.findElements(By.xpath(strLocatorValue));
                break;
            case "name":
                elements = driver.findElements(By.name(strLocatorValue));
                break;
            case "css":
                elements = driver.findElements(By.cssSelector(strLocatorValue));
                break;
            default:
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type or locatorName");

        }
        return elements;

    }

    public List<WebElement> getElementListUsingTagName(String locatorName) {

        return driver.findElements(By.tagName(locatorName));

    }


    public void printListWebELement(List<WebElement> webElements) {

        for (int i = 0; i < webElements.size(); i++) {
            System.out.println(webElements.get(i).getText());
        }
    }

    public String getPageSource() {
        String str;
        waitForJSandJQueryToLoad();
        waitForPageToLoad();

        str = driver.getPageSource();
        return str;
    }

    public WebElement getElement(String str) {
        waitForPageToLoad();
        String strLocatorType = (str.split(":", 2)[0]).toLowerCase();
        String strLocatorValue = (str.split(":", 2)[1]);

        switch (strLocatorType) {

            case "id":
                element = driver.findElement(By.id(strLocatorValue));
                break;
            case "name":
                element = driver.findElement(By.name(strLocatorValue));
                break;
            case "partiallink":
                element = driver.findElement(By.partialLinkText(strLocatorValue));
                break;
            case "linktext":
                element = driver.findElement(By.linkText(strLocatorValue));

                break;
            case "css":
                element = driver.findElement(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(strLocatorValue));
                break;
            default:
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type");

        }
        waitforElementVisible(element);
        return element;
    }

    public WebElement getElement(WebElement element, String str) {
        waitForPageToLoad();
        String strLocatorType = (str.split(":", 2)[0]).toLowerCase();
        String strLocatorValue = (str.split(":", 2)[1]);
        WebElement elementToReturn;
        switch (strLocatorType) {

            case "id":
                elementToReturn = element.findElement(By.id(strLocatorValue));
                break;
            case "name":
                elementToReturn = element.findElement(By.name(strLocatorValue));
                break;
            case "partiallink":
                elementToReturn = element.findElement(By.partialLinkText(strLocatorValue));
                break;
            case "linktext":
                elementToReturn = element.findElement(By.linkText(strLocatorValue));

                break;
            case "css":
                elementToReturn = element.findElement(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                elementToReturn = element.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
                elementToReturn = element.findElement(By.xpath(strLocatorValue));
                break;
            default:
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type");

        }
        waitforElementVisible(elementToReturn);
        return elementToReturn;
    }

    public boolean isTextBoxContainingValue(WebElement webElement) {
        return !webElement.getText().isEmpty();
    }

    public boolean isTextBoxContainingValue(String webElementString) {
        WebElement webElement = getElement(webElementString);
        return !webElement.getText().isEmpty();
    }

    public boolean isElementContainingValue(String webElementString) {
        WebElement webElement = getElement(webElementString);
        return !webElement.getText().isEmpty();
    }

    public boolean isElementContainsGivenValue(WebElement webElement, String string) {
        return webElement.getText().contains(string);
    }

    public boolean isElementContainsGivenValue(String webElementString, String string) {
        WebElement webElement = getElement(webElementString);
        return webElement.getText().contains(string);
    }

    public String getElementText(String webElementString) {
        WebElement webElement = getElement(webElementString);
        return webElement.getText();
    }

    public boolean isElementAttributeContainingValue(WebElement webElement, String atributeName) {
        return !webElement.getAttribute(atributeName).isEmpty();
    }

    public boolean isElementAttributeContainingValue(String webElementString, String atributeName) {
        WebElement webElement = getElement(webElementString);
        return !webElement.getAttribute(atributeName).isEmpty();
    }

    public boolean isElementAttributeContainsGivenValue(WebElement webElement, String atributeName, String attributeValue) {
        return webElement.getAttribute(atributeName).equals(attributeValue);
    }

    public void clickElementUsingTagNameAndAttribute(String tagName, String attributeName, String attributeValue) {
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        WebElement element = getElementofListUsingAttribute(elements, attributeName, attributeValue);
        waitTillClickableElementExist(element);
        enter(element);
        waitForJSandJQueryToLoad();

    }

    public WebElement getElementUsingTagNameAndAttribute(String tagName, String attributeName, String attributeValue) {
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        WebElement element = getElementofListUsingAttribute(elements, attributeName, attributeValue);
        return element;

    }

    public boolean isURLContainingString(String urlPartialString) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        return wait.until(ExpectedConditions.urlContains(urlPartialString));

    }

    public void selectByVisibleText(WebElement element, String toBeSelected) {
        waitforElementVisible(element);
        Select oSelect = new Select(element);
        oSelect.selectByVisibleText(toBeSelected);
    }

    public void selectByMatchingVisibleText(WebElement element, String matchingString) {
        waitforElementVisible(element);
        Select oSelect = new Select(element);
        List<WebElement> elements = oSelect.getOptions();
        for (int i = 0; i < elements.size(); i++) {
            waitforElementVisible(elements.get(i));
            if (elements.get(i).getText().contains(matchingString)) {
                elements.get(i).click();
                break;
            }
        }

    }

    public void selectByIndex(String elementString, int index) {
        waitforElementVisible(elementString);
        element = getElement(elementString);
        Select oSelect = new Select(element);
        oSelect.selectByIndex(index);
    }

    public void selectByMatchingVisibleText(String elementString, String matchingString) {
        waitforElementVisible(elementString);
        element = getElement(elementString);
        Select oSelect = new Select(element);
        List<WebElement> elements = oSelect.getOptions();
        for (int i = 0; i < elements.size(); i++) {
            waitforElementVisible(elements.get(i));
            if (elements.get(i).getText().contains(matchingString)) {
                elements.get(i).click();
                break;
            }
        }

    }

    public Boolean isClickableElementExisting(String elementLocator) {
        WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public Boolean isClickableElementExisting(WebElement element) {
        //WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public Boolean isVisibleElementExisting(String elementLocator) {
        WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public Boolean isVisibleElementExisting(WebElement element) {
        //WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public void waitTillTextExist(String string) {
        waitForJSandJQueryToLoad();
        final Boolean[] isWaitSuccessfull = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {
                //System.out.println("waitTillTextExist method entering " + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());
                //getPageSource();
                if (getPageSource().contains(string)) {
                    isWaitSuccessfull[0] = true;
                    System.out.println("waitTillTextExist method passing time" + string + " STATUS is: " + isWaitSuccessfull[0] + cmnFunction.getCurrentDate());
                    return true;

                } else {

                    return false;
                }
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method just before exist " + string + " STATUS is: " + isWaitSuccessfull[0] + cmnFunction.getCurrentDate());

    }

    public void waitTillTextExist(String string, Integer timeinSeconds) {
        final Boolean[] isWaitSuccessfull = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(timeinSeconds, SECONDS);


        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {
                String pageSource = getPageSource();
                if (pageSource.contains(string)) {
                    isWaitSuccessfull[0] = true;
                    return true;

                } else {
                    //System.out.println("value of Page source at the time of failure is"+ pageSource);
                    System.out.println("waitTillTextExist method " + string + " STATUS is: " + isWaitSuccessfull[0] + cmnFunction.getCurrentDate());
                    return false;
                }
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method " + string + " STATUS is: " + isWaitSuccessfull[0] + cmnFunction.getCurrentDate());

    }

    public void safeWaitTillTextExists(String string, Integer timeinSeconds) {
        try {
            waitTillTextExist(string, timeinSeconds);
        } catch (TimeoutException e) {
            System.out.println("Text safe checked and does not exist :" + string);
        }
    }


    public void waitTillTextNotExist(String string, Integer timeinSeconds) {
        final Boolean[] isWaitSuccessfull = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(timeinSeconds, SECONDS);


        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {
                String pageSource = driver.getPageSource();
                if (!pageSource.contains(string)) {
                    isWaitSuccessfull[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method " + string + " STATUS is: " + isWaitSuccessfull[0] + cmnFunction.getCurrentDate());

    }


    public void waitTillClickableElementExist(String string) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if (isClickableElementExisting(string)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillClickableElementExist method " + string + " STATUS is: " + isElementFound[0]);

    }

    public void waitTillClickableElementExist(WebElement element) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if (isClickableElementExisting(element)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method " + element + " STATUS is: " + isElementFound[0]);

    }

    public void waitTillVisibleElementExist(String string) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if (isVisibleElementExisting(string)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillClickableElementExist method " + string + " STATUS is: " + isElementFound[0]);

    }

    public void waitTillVisibleElementExist(WebElement element) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if (isClickableElementExisting(element)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method " + element + " STATUS is: " + isElementFound[0]);

    }

    public void printFrames() {
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        System.out.println("Count of frames now is " + elements.size());
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i));
            System.out.println(elements.get(i).getAttribute("title"));
        }
    }

    public void switchFrameByElementName(WebElement element) {
        try {
            waitforElementVisible(element);
            driver.switchTo().frame(element);
        } catch (Exception e) {
            System.out.println("Unable to Switch- may be check eleemnt existence");
            throw e;

        }
    }

    public void switchToDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Unable to Defauklt Content- check!");
            throw e;

        }
    }

    public Integer getElementCountFromList(String StringLocator) {
        List<WebElement> list = getElementList(StringLocator);
        return list.size();
    }

    public String getAttributeValue(String StringLocator, String attribute) {
        return getElement(StringLocator).getAttribute(attribute);

    }

    public boolean switchWindow(String title) {

        String currentWindow = driver.getWindowHandle();
        Set<String> availableWindows = driver.getWindowHandles();
        if (!availableWindows.isEmpty()) {
            for (String windowId : availableWindows) {
                if (driver.switchTo().window(windowId).getTitle().equals(title)) {
                    return true;
                } else {
                    driver.switchTo().window(currentWindow);
                    driver.switchTo().defaultContent();
                }
            }
        }
        return false;
    }

    public void switchToWindow(String windowHandle) {

        driver.switchTo().window(windowHandle);
    }

    public void CapsLockSwitchOn() {
        boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK);

        System.out.print("isCapsLockOn>>>>" + isCapsLockOn);
        if (isCapsLockOn) {
            Toolkit.getDefaultToolkit().setLockingKeyState(
                    KeyEvent.VK_CAPS_LOCK, true);
            isCapsLockOn = true;
            System.out.print("isCapsLockOn>>> true \n");
        }
    }

    public void CapsLockSwitchOff() {
        boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK);

        System.out.print("isCapsLockOn>>>>" + isCapsLockOn);
        if (isCapsLockOn) {
            Toolkit.getDefaultToolkit().setLockingKeyState(
                    KeyEvent.VK_CAPS_LOCK, false);
            isCapsLockOn = false;
            System.out.print("isCapsLockOn>>> false \n");
        }
    }


    public void hover(WebElement element) {
        sleepms(200);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click().perform();
        sleepms(500);
    }

    public void clickAndHold(WebElement element) {
        sleepms(200);
        Actions actions = new Actions(driver);
        actions.clickAndHold(element);
        actions.click().perform();
        sleepms(500);
    }

    public void clickPositionXinElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + element.getLocation().x + ")");
        element.click();
        sleepms(200);
    }

    public void sleepms(long milliseconds) {
        System.out.println("sleep(" + milliseconds + ")");
        long end_time = System.currentTimeMillis() + milliseconds;
        while (System.currentTimeMillis() < end_time) {
        }
    }


    public void clickUsingJavascript(WebElement element) {
        scrollViewUsingJavascript(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    public void typeUsingJavascript(WebElement element, String text) {
        scrollViewUsingJavascript(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + text + "'", element);
    }

    public void typeUsingActions(WebElement element, String text) {
        Actions builder = new Actions(driver);
        builder.click(element).sendKeys(text).perform();

    }


    public void scrollViewUsingJavascript(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public void acceptAlertIfExists() throws InterruptedException {
        try {
            Thread.sleep(10000); // waiting enough for random Alert to appear, if it was to be.
            Alert alert = driver.switchTo().alert();
            //System.out.println("2. switched to alert and number rof window handles are"+driver.getWindowHandles());
            System.out.println(alert.getText());
            alert.accept();
            System.out.println("3. alert accepted and number rof window handles are" + driver.getWindowHandles());
//            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
    }

    public void setParentWindow(String window) {
        cmnVrbl.parentWindowHandle = window;
    }

    public void setChild1Window(String window) {
        cmnVrbl.child1WindowHandle = window;
    }

    public void setWindow(String windowType, String window) {
        String windorTypeUpperCase = windowType.toUpperCase();
        switch (windowType) {
            case "PARENT":
                setParentWindow(window);
                break;
            case "CHILD1":
                setChild1Window(window);

        }
    }

    public String getNewWindowOutOfSet() {
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("number of window handles are" + windowHandles);
        windowHandles.remove(cmnVrbl.parentWindowHandle);
        windowHandles.remove(cmnVrbl.child1WindowHandle);
        windowHandles.remove(cmnVrbl.child2WindowHandle);
        return windowHandles.iterator().next();
    }

    public void closeDriver() {
        driver.close();
        //driver.quit();
    }

    public void donotacceptAlertIfExists() throws InterruptedException {
        try {
            Thread.sleep(5000); // waiting enough for random Alert to appear, if it was to be.
            Alert alert = driver.switchTo().alert();
            //System.out.println("2. switched to alert and number rof window handles are"+driver.getWindowHandles());
            System.out.println(alert.getText());
            alert.dismiss();
            System.out.println("3. alert accepted and number rof window handles are" + driver.getWindowHandles());
//            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }
}