package com.test.helper;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.test.annotations.Page;
import com.test.configuration.PageFactory;
import com.test.exceptions.UnexpectedPageException;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ScrenshotUtils;
import com.test.helper.utils.SeleniumHelper;
import com.test.ui.CommonVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

// DEMO basic page functionality
public class BasePage {
    private static final Logger LOG = Logger.getLogger(BasePage.class.getName());

    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 60000;

    private static final int DEFAULT_ELEMENT_RELOAD_WAIT = 100;

    @Inject
    private Injector injector;

    @Inject
    public WebDriver driver;

    @Inject
    public PageFactory pageFactory;

    @Inject
    @Named("browser")
    protected String browser;

    @Inject
    @Named("environment")
    protected String environment;

    @Inject
    protected NavigationHelper navigationHelper;

    @Inject
    protected CommonVariables cmnVrbl;

    @Inject
    protected SeleniumHelper selHelper;

    @Inject
    protected CommonFunctions cmnFunctns;

    @Inject
    protected ScrenshotUtils screnshotUtils;


    // DEMO page displayed check
    public boolean isDisplayed() {
        try {
            //return getPageIdFromAnnotation().equalsIgnoreCase(getPageId());
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }


    public void load() {
        String pageUrl = "https://google.co.uk";
        driver.navigate().to(pageUrl);
    }

    public void submit() throws TimeoutException {
        throw new UnexpectedPageException("%s page has not been displayed%n");
    }

    public void continueIfExists() {
        try {
            // Wait up to 5 seconds for the page to load
            waitFor(5000);
            submit();
        } catch (TimeoutException ex) {
            System.out.printf("%s page has not been displayed%n", this);
        }
    }

    public void assertPage() {
        String expectedPage = getPageIdFromAnnotation();
        String actualPage = getPageId();

        if (!expectedPage.equals(actualPage)) {
            throw new UnexpectedPageException(String.format(
                    "Unexpected page displayed: \n\tExpected: %s\n\tbut got:  %s",
                    expectedPage, actualPage));
        }
    }

    public String getPageId() {
//        PageIdHelper pageIdHelper = injector.getInstance(MobilePageIdHelper.class);
//
//        if (pageIdHelper == null) {
//            return null;
//        }
//
//        return pageIdHelper.getPageId();
        return null;
    }


    private List<Page> getPageAnnotations(Class klass) {
        List<Page> annotations = new ArrayList<Page>();

        Page pageAnnotation = (Page) klass.getAnnotation(Page.class);
        if (null != pageAnnotation) {
            annotations.add(pageAnnotation);
        }
        if (null != klass.getSuperclass()) {
            annotations.addAll(getPageAnnotations(klass.getSuperclass()));
        }

        return annotations;
    }

    private String getPageIdFromAnnotation() {
        List<Page> pageAnnotations = getPageAnnotations(this.getClass());

        for (Page pageAnnotation : pageAnnotations) {
            if (pageAnnotation.id().isEmpty()) {
                continue;
            }
            return pageAnnotation.id();
        }

        return "";
    }

    public void waitFor(Integer timeout) throws TimeoutException {
        TimeoutHelper.withTimeout(timeout, new Runnable() {
            @Override
            public void run() {
                while (!getPageIdFromAnnotation().equals(getPageId())) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        LOG.fine(e.getMessage());
                    }
                }
            }
        });
    }

    public void waitFor() throws TimeoutException {
        this.waitFor(DEFAULT_PAGE_LOAD_TIMEOUT);
    }


    protected String getRelativePath() {
        Page pageAnnotation = this.getClass().getAnnotation(Page.class);
        if (pageAnnotation == null) {
            return "";
        }
        return pageAnnotation.path();
    }

    public void continueNext() throws TimeoutException {
                String.format("Need to implement in child class: %s",
                        this.getClass().getName());
    }


    /**
     * Added to ensure that the elements on the  page are completely stable after loading
     * and therefore clickable at right position
     * before the operations are resumed further
     *
     * @throws TimeoutException
     */
//
//    public void waitForPageToBecomeStable(PageElement element)
//            throws TimeoutException {
//        element.waitUntilVisible();
//        boolean isStable = false;
//        int count = 0;
//        Point point = element.getLocation();
//        while (!isStable) {
//            if (point.getX() == element.getLocation().getX()
//                    && point.getY() == element.getLocation().getY()) {
//                count++;
//            } else {
//                count = 0;
//                point = element.getLocation();
//            }
//
//            if (count == 10) {
//                isStable = true;
//            }
//
//            try {
//                // sleep for 100ms to give page a little time
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                LOG.fine(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * This method waits for element to get enabled with the help of web driver. This puts element in to cache which
//     * resolves StaleElementReferenceException issue for page elements
//     *
//     * @param element       This is page element, where enable check happens for this page element
//     * @param timesInMinute This is time unit integer value which considers value in minutes
//     */
//
//    public void waitForElementToBecomeEnabled(PageElement element, int timesInMinute) {
//        new WebDriverWait(driver, timesInMinute).until(ExpectedConditions.stalenessOf(element));
//    }
//
//    /**
//     * This method waits for element to get clickable with the help of web driver. This puts element in to cache which
//     * resolves StaleElementReferenceException issue for page elements
//     *
//     * @param element       This is page element, where clickable check happens for this page element
//     * @param timesInMinute This is time unit integer value which considers value in minutes
//     */
//    public void waitForElementToBecomeClickable(PageElement element, int timesInMinute) {
//        new WebDriverWait(driver, timesInMinute).until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    protected String getPageTitleFromAnnotation() {
//        List<Page> pageAnnotations = getPageAnnotations(this.getClass());
//        for (Page pageAnnotation : pageAnnotations) {
//            if (!pageAnnotation.title().isEmpty()) {
//                return pageAnnotation.title();
//            }
//        }
//
//        return "";
//    }
//
//
//    public PageIdHelper getPageIdHelperDelegate() {
//        List<Page> pageAnnotations = getPageAnnotations(this.getClass());
//        for (Page pageAnnotation : pageAnnotations) {
//            if (!pageAnnotation.pageIdDelegate().equals(NullPageIdHelper.class)) {
//                return injector.getInstance(pageAnnotation.pageIdDelegate());
//            }
//        }
//
//        return null;
//    }
//
//    public WebElement waitForElementByPolling(final By by) {
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(1, TimeUnit.SECONDS)
//                .ignoring(java.util.NoSuchElementException.class);
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//    }
//
//    public WebElement waitForElementByPolling(WebElement webElement) {
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(1, TimeUnit.SECONDS)
//                .ignoring(java.util.NoSuchElementException.class);
//        return wait.until(ExpectedConditions.visibilityOf(webElement));
//    }
//
//
//    public boolean navigateToiFrameBySeconds(Long seconds, String iframeName) throws TimeoutException {
//        new WebDriverWait(driver, seconds).until(ExpectedConditions
//                .frameToBeAvailableAndSwitchToIt(iframeName));
//        return true;
//    }
//
//
//    public void sleepms(long milliseconds) {
//        System.out.println("sleep(" + milliseconds + ")");
//        long end_time = System.currentTimeMillis() + milliseconds;
//        while (System.currentTimeMillis() < end_time) {}
//    }
//
//
//    public void hover(WebElement element) {
//        Actions action = new Actions(driver);
//        // move to the element to hover
//        action.moveToElement(element).build().perform();
//        sleepms(300);
//    }
//
//
//    public void clickPositionXinElement(WebElement element) {
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ element.getLocation().x + ")");
//        element.click();
//        sleepms(200);
//    }
//
//    public void clickUsingJavascript(WebElement element) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",element);
//    }
//
//    public void typeUsingJavascript(WebElement element, String text){
//        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + text + "'", element);
//    }
//
//
//    public void scrollViewUsingJavascript(WebElement element) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//    }
//
//    public List<String> getListValues(PageElement element) {
//        Select select = new Select(element);
//        List<WebElement> options = select.getOptions();
//        List<String> optionVales = new ArrayList<>();
//        options.forEach(option-> optionVales.add(option.getText()));
//        return optionVales;
//    }
//
//    public void updateElement(By locator, String value) {
//        waitForElementByPolling(locator);
//        driver.findElement(locator).clear();
//        driver.findElement(locator).sendKeys(value);
//    }
//
//    public void selectItem(PageElement pageElement, String str) {
//        Select select = new Select(pageElement);
//        select.selectByVisibleText(str);
//    }

    public <T extends WebElement> String getSelectedValue(T element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public List<String> getSelectedDropdownItems(By locator) {
        List<String> selectedValues = new ArrayList<>();
        List<WebElement> elements = driver.findElements(locator);
        elements.forEach(element -> {
            selectedValues.add(getSelectedValue(element));
        });
        return selectedValues;
    }

}
