package com.test.configuration;

import com.test.exceptions.StopTestException;
import com.test.extension.fieldInitialisers.FieldAssessor;
import com.test.handlers.Refreshable;
import com.test.helper.LoggingHelper;
import com.test.helper.ReportingHelper;
import com.test.helper.helpers.ReflectionHelper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class PageFactory {
    private static final Logger LOG = Logger.getLogger(PageFactory.class.getName());
    private static WebDriver driver = null;

    private static WebDriver remoteWebDriver = null;

    private static final String SAFARI_BROWSER = "SAFARI";

    public static Object getPage(String pageClassName)
            throws StopTestException {
        String applicationMode = Configuration.getConfiguration().getApplicatonMode();

        if ("MOBILE".equals(applicationMode)) {
            pageClassName = pageClassName + "Mobile";
        }
        // removed until all pages have been split, can then be brought back
        // with the if statement below being removed
//        else {
//            pageClassName = pageClassName + "Web";
//        }
        Object page = null;
        try {
            Class<?> pageClass = Class.forName(pageClassName);
            // if statement below is to allow non split pages to work until all
            // pages have been split
            if (pageClass.isInterface()) {
                pageClassName = pageClassName + "Web";
                pageClass = Class.forName(pageClassName);
            }
            LoggingHelper.printDebug("Loading page: " + pageClassName);
            Constructor<?> constructor = pageClass.getConstructor(WebDriver.class);
            page = constructor.newInstance(getDriver());
        } catch (Exception ex) {
            throw new StopTestException("could not get page : " + pageClassName);
        }
        return page;
    }

    /**
     * Method tp switch to an iframe on a page - elements inside an iFrame cannot be accessed unless
     * the driver switches to inside the iFrame
     *
     * @param element
     */
    public static void switchToIframe(WebElement element) {
        getDriver().switchTo().frame(element);
    }


    /**
     * Create WebDriver instance if it doesn't exist yet
     */
    public static WebDriver getDriver() {

        try {
            if (Configuration.getConfiguration().getGridRun().equalsIgnoreCase("true")) {
                remoteWebDriver.getTitle();
                return remoteWebDriver;
            } else {
                driver.getTitle();
                return driver;
            }
        } catch (Exception e) {
            setUp();
            return driver;
        }
    }

    /**
     * Get currently used driver instance and don't create new one if it doesn't
     * exists
     *
     * @throws StopTestException
     */
    public static WebDriver getCurrentDriver() throws StopTestException {
        try {
            if (Configuration.getConfiguration().getGridRun().equalsIgnoreCase("true")) {
                return remoteWebDriver;
            } else {
                return driver;
            }
        } catch (Exception e) {
            throw new StopTestException("Unable to getCurrentDriver()");
        }
    }

    /**
     * Kills WebDriver instance if it exists
     */
    public static void tearDown() {
        String debug = "false";
        try {
            debug = Configuration.getConfiguration().getDebugProperty();
        } catch (Exception a) {
            // do nothing.
            // debug set to false above.
        }
        if (driver != null) {
            try {
                TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
            } catch (Exception e1) {
                // Ignore
            }
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception e) {
                // Ignore
            }

            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("In tearDown() - Exception thrown when attempting driver.quit() "
                                        + e.getMessage());
            } finally {
                driver = null;
            }
        }

        // RemoteWebDriver instances (GRID Runs!)
        if (remoteWebDriver != null) {
            try {
                TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
            } catch (Exception e1) {
                if (debug.equalsIgnoreCase("true")) {
                    System.out
                            .println(
                                    "Exception thrown in tearDown(). Unable to delete temporary files. "
                                            + e1.getMessage());
                }
            }
            // Cookie Handling.
            try {
                if (debug.equalsIgnoreCase("true")) {
                    Set<Cookie> cookies = remoteWebDriver.manage().getCookies();
                    Iterator<Cookie> cookiesIter = cookies.iterator();
                    System.out
                            .println(
                                    "Before remoteWebDriver.manage().deleteAllCookies()");
                    while (cookiesIter.hasNext()) {
                        System.out.println(cookiesIter.next());
                    }
                }
                boolean bol;
                String scenarioNotDeleteCookiesStatus = System.getProperty("NotDeleteCookiesScenario");
                if (scenarioNotDeleteCookiesStatus == null || scenarioNotDeleteCookiesStatus == "false")
                    bol = true;
                else
                    bol = false;
                System.out.println("bol value is " + bol);
                if (bol) {
                    System.out.println("invoking delete cookies");
                    remoteWebDriver.manage().deleteAllCookies();
                } else {
                    System.out.println("not deleting the cookies");
                    System.setProperty("NotDeleteCookiesScenario", "false");
                }
                if (debug.equalsIgnoreCase("true")) {

                    Set<Cookie> cookies = remoteWebDriver.manage().getCookies();
                    Iterator<Cookie> cookiesIter = cookies.iterator();
                    System.out
                            .println(
                                    "After remoteWebDriver.manage().deleteAllCookies()");
                    while (cookiesIter.hasNext()) {
                        System.out.println(cookiesIter.next());
                    }
                }
            } catch (Exception e) {
                if (debug.equalsIgnoreCase("true")) {
                    System.out
                            .println(
                                    "Exception thrown in tearDown(). Unable to delete cookies. "
                                            + e.getMessage());
                }
            }

            // Screenshot and page source handling.
            try {
                String stopTest = "false";
                if (System.getProperty("autotest.stop_test") != null) {
                    stopTest = System.getProperty("autotest.stop_test");
                }
                if (debug.equalsIgnoreCase("true")
                        && stopTest.equalsIgnoreCase("true")) {

                    ReportingHelper.takeGridScreenShot();
                    ReportingHelper.getPageSourceCode();

                }

            } catch (Exception e) {
                System.out
                        .println(
                                "Unable to perform grid screenshot/page source handling"
                                        + e.getMessage());
            }

            // Quit RemoteWebDriver.
            try {
                remoteWebDriver.quit();
            } catch (Exception e) {
                System.out
                        .println(
                                "In tearDown() - Exception thrown when attempting remoteWebDriver.quit() "
                                        + e.getMessage());
            } finally {
                remoteWebDriver = null;
            }
        }
    }

    /**
     * Creates WebDriver instance and opens ie browser
     */
    public static void setUp() {
        // changing default browser to Firefox
        driver = new FirefoxDriver();
    }

    public static void deleteAllCookies() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

    public static void deleteNamedCookie(String cookieName) {
        if (driver != null) {
            driver.manage().deleteCookieNamed(cookieName);
        }
    }

    public static void closeBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Ignore
                e.printStackTrace();
            }
            //WaitHelper.getInstance().sleepQuietly(1000);
        }
    }

    public static void refocusDriver() {
        if (driver != null) {
            try {
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                // Ignore

            }
        }
        if (remoteWebDriver != null) {
            try {
                remoteWebDriver.switchTo().defaultContent();
            } catch (Exception e) {
                // Ignore
                e.printStackTrace();
            }
        }
    }

    public static void addCookie(String key, String value)
            throws StopTestException {
        WebDriver driver = PageFactory.getCurrentDriver();
//         System.out.println("Cookie details: "+ driver.manage().getCookies().toString());
        Cookie newCookie = new Cookie(key, value);
        driver.manage().addCookie(newCookie);
        System.out.println(key + "Cookie Added: "
                + driver.manage().getCookieNamed(key).toString());
//         System.out.println("Cookie details: "+ driver.manage().getCookies().toString());
    }

    /**
     * Adding Cookie wherein we specify the domain as well
     *
     * @param key
     * @param value
     * @param domain
     * @throws StopTestException
     */
    public static void addCookie(String key, String value, String domain)
            throws StopTestException {
        WebDriver driver = PageFactory.getCurrentDriver();
        Cookie newCookie = new Cookie(key, value, domain, null, null);
        driver.manage().addCookie(newCookie);
    }

    /**
     * This method returns the value of the name cookie
     *
     * @param cookieName
     * @return
     * @throws StopTestException
     */
    public static String readCookieValue(String cookieName)
            throws StopTestException {
        Cookie cookie = null;

        WebDriver driver = PageFactory.getCurrentDriver();

        cookie = driver.manage().getCookieNamed(cookieName);
        if (cookie != null)
            return cookie.getValue();

        return null;
    }

    public static WebDriver getRemoteWebDriver(String browser)
            throws MalformedURLException, StopTestException {

        if (remoteWebDriver != null)
            return remoteWebDriver;

        String hubServerDetails = "";
        DesiredCapabilities capability = new DesiredCapabilities(browser, "", Platform.ANY);

        if (browser != null && browser.equalsIgnoreCase("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setPreference("network.proxy.type", 5);
            profile.setPreference("network.http.phishy-userpass-length", 255);
            capability.setCapability(FirefoxDriver.MARIONETTE, profile);
            capability.setBrowserName("firefox");
            capability.setCapability("acceptInsecureCerts", true);
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }

        if (browser != null && browser.equalsIgnoreCase("chrome")) {
            capability.setBrowserName("chrome");
        }

        if (browser != null && browser.equalsIgnoreCase("ie")) {

            capability = DesiredCapabilities.internetExplorer();
            capability.setCapability("platform", Platform.ANY);
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capability.setBrowserName("internet explorer");
            capability.setVersion("11");

            capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capability.setCapability("ie.ensureCleanSession", true);
            capability.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
            capability.setCapability("ignoreZoomSetting", true);
            capability.setCapability("nativeEvents", true);
            capability.setCapability("unexpectedAlertBehaviour", "accept");
            capability.setCapability("ignoreProtectedModeSettings", true);
            capability.setCapability("disable-popup-blocking", true);
            capability.setCapability("enablePersistentHover", true);
        }

        if (browser != null && browser.equalsIgnoreCase("edge")) {

            capability = DesiredCapabilities.edge();
            capability.setBrowserName("MicrosoftEdge");

            capability.setCapability(
                    CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
                    true);
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }

        if (browser != null && browser.equalsIgnoreCase("safari")) {

            capability = DesiredCapabilities.safari();
            capability.setBrowserName("safari");

            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setUseCleanSession(true);

            capability.setCapability(SafariOptions.CAPABILITY, safariOptions);
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }
        hubServerDetails = Configuration.getConfiguration().getGridHubServerDetails();
        URL hubUrl = new URL("http://" + hubServerDetails + "/wd/hub");
        try {
            remoteWebDriver = new RemoteWebDriver(hubUrl, capability);
            if (Configuration.getConfiguration().getDebugProperty().equalsIgnoreCase("true")) {
                Set<Cookie> cookies = remoteWebDriver.manage().getCookies();
                Iterator<Cookie> cookiesIter = cookies.iterator();
                while (cookiesIter.hasNext()) {
                    System.out.println(cookiesIter.next());
                }
            }
            printSessionId();
            remoteWebDriver.manage().deleteAllCookies();
        } catch (Throwable e) {
            System.out.printf("Connecting to selenium grid: %s%n", hubUrl);
            System.out.printf("Desired capabilities: %s%n", capability);
            e.printStackTrace();
            throw new StopTestException(
                    "Unable to create a RemoteWebDriver instance"
                            + e.getMessage(), e);
        }

        return remoteWebDriver;
    }

    private static void printSessionId()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        if (!(remoteWebDriver instanceof RemoteWebDriver))
            return;
        Method getSessionIdMethod = RemoteWebDriver.class.getMethod("getSessionId");
        String sessionId = getSessionIdMethod.invoke(remoteWebDriver).toString();
        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",sessionId, System.getenv("SELENIUM_BUILD"));
        System.out.println(message);
    }


    /**
     * Create Chrome WebDriver instance after closing other driver instances
     *
     * @author n417179
     */
    public static WebDriver getChromeWebDriver() throws MalformedURLException,
            StopTestException {

        // Check to see if firefox is the current driver quit if not
        if (driver != null) {
            if (!(driver instanceof ChromeDriver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    // Ignore

                }
            }
        }

        // See if firefox driver is running if not start a firefox driver
        try {
            driver.getTitle();
            return driver;
        } catch (Exception e) {

            System.setProperty("webdriver.chrome.driver", Configuration
                    .getConfiguration().getWebdriverForChrome());
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("enable-automation");
            options.addArguments("--disable-infobars");
            options.addArguments("--start-maximized");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("--disable-extensions");
            //options.setExperimentalOption("useAutomationExtension",false);
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
        }
        return driver;
    }

    public static WebDriver getSafariWebDriver() throws MalformedURLException,StopTestException {
        // Check to see if firefox is the current driver quit if not
        if (driver != null) {
            if (!(driver instanceof SafariDriver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    // Ignore
                    e.printStackTrace();
                }
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception e) {

            System.setProperty("webdriver.safari.noinstall", "true");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            String browserName =  null != Configuration.getConfiguration().getBrowser() ?
                            Configuration.getConfiguration().getBrowser().toUpperCase() :
                            SAFARI_BROWSER;
            capabilities.setBrowserName(browserName);
            driver = new SafariDriver(capabilities);
            driver.manage().deleteAllCookies();
        }

        return driver;
    }

    public static WebDriver getIEWebDriver() throws MalformedURLException,
            StopTestException {

        // Check to see if firefox is the current driver quit if not
        if (driver != null) {
            if (!(driver instanceof InternetExplorerDriver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    // Ignore
                    e.printStackTrace();
                }
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception e) {

            System.setProperty("webdriver.ie.driver", Configuration.getConfiguration().getWebdriverForIE());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            capabilities.setBrowserName("internet explorer");
            capabilities.setCapability("ie.ensureCleanSession", true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
            capabilities.setCapability("ignoreZoomSetting", true);
            //To resolve the clicking issue for IE
            capabilities.setCapability("nativeEvents", true);
            capabilities.setCapability("unexpectedAlertBehaviour", "accept");
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            capabilities.setCapability("disable-popup-blocking", true);
            capabilities.setCapability("enablePersistentHover", true);
            driver = new InternetExplorerDriver(capabilities);
            driver.manage().deleteAllCookies();
        }
        return driver;
    }

    /**
     * Create Firefox WebDriver instance after closing other driver instances
     *
     * @throws StopTestException
     */
    public static WebDriver getFireFoxDriver() throws StopTestException {
        // Check to see if firefox is the current driver quit if not
        if (driver != null) {
            if (!(driver instanceof FirefoxDriver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Ignore
                }
            }
        }

        // See if firefox driver is running if not start a firefox driver
        try {
            driver.getTitle();
            return driver;
        } catch (Exception e) {

            System.setProperty("webdriver.gecko.driver", Configuration.getConfiguration().getWebDriverForFirefox());
            driver = new FirefoxDriver();
            driver.manage().deleteAllCookies();
        }
        return driver;
    }

    /**
     * Create Edge WebDriver instance after closing other driver instances
     *
     * @throws StopTestException
     */
    public static WebDriver getEdgeDriver() throws StopTestException {

        // Check to see if firefox is the current driver quit if not
        if (driver != null) {
            if (!(driver instanceof EdgeDriver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    // Ignore

                }
            }
        }

        // See if firefox driver is running if not start a firefox driver
        try {
            driver.getTitle();
            return driver;
        } catch (Exception e) {

            System.setProperty("webdriver.edge.driver", Configuration
                    .getConfiguration().getWebdriverForEdge());
            driver = new EdgeDriver();
            driver.manage().deleteAllCookies();
        }
        return driver;
    }
//
//    @Provides
//    public PageFactory get() {
//        LOG.fine("Got request for PageFactory");
//        return this;
//    }

    public void invalidate(Object object) {
        if (object instanceof Refreshable) {
            ((Refreshable) object).invalidate();
        }

        if (object instanceof Proxy) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(object);
            if (invocationHandler instanceof Refreshable) {
                ((Refreshable) invocationHandler).invalidate();
            }
        } else {
            List<Field> fields = ReflectionHelper.getAllFields(object);
            Iterator var3 = fields.iterator();

            while (var3.hasNext()) {
                Field field = (Field) var3.next();
                if (FieldAssessor.isSeleniumPomField(field)) {
                    Object fieldValue = ReflectionHelper.getFieldValue(object, field.getName());
                    this.invalidate(fieldValue);
                }
            }
        }

    }


}
