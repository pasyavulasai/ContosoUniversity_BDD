package com.test.configuration;

import com.test.exceptions.StopTestException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main configuration for test automation framework
 */
public class Configuration {

    public static final String KEYBASE = "autotest";

    public static final String SELENIUM_VARIABLES_PREFIX = "selenium.";

    public static final String SELENIUM_ENVIRONMENT_VARIABLES_PREFIX = "SELENIUM_";

    public static final String KEY_SELENIUM_EXCLUDED_PROPERTIES = "excluded_selenium_properties";

    // This is the full key allowing for multiple settings
    public static final String KEY_SETTINGS = ".settings";

    // This is used to ensure the settings are loaded
    public static final String KEY_LOADED = ".loaded";

    // This environment used for testing
    public static final String KEY_ENVIRONMENT = "environment";

    public static final String KEY_Environ = "environ";

    public static final String KEY_PAGE_TIMEOUT = "timeout.page_load";

    public static final String KEY_PAGE_TIMING = "timing.page_load";

    public static final String KEY_BROWSER = "browser";

    public static final String KEY_GRID_RUN = "grid.run";

    public static final String KEY_GRID_ENVIRONMENT = "selenium.grid.env.";

    public static final String KEY_GRID_NODE = "selenium.grid.node";

    public static final String KEY_GRID_HUB_SERVERNAME_PORT = "grid.hub.server";

    // remaining parts
//    public static final String KEYP_ENV_URL = "http://localhost/MyApp";
    public static final String KEYP_ENV_URL = "url";

    public static final String KEYP_ENV_MULTI_JVM = "environment_multi_jvm";


    public static final String KEYP_DEBUG_ENABLED = "debug.enabled";


    public static final String KEY_HELPER_DEFINITIONS = "location.helper_definitions";

    private String mode = "WEB";

    public static final String KEYP_WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    public static final String KEYP_WEBDRIVER_FIREFOX_DRIVER = "webdriver.gecko.driver";

    public static final String KEYP_WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";

    public static final String KEYP_WEBDRIVER_EDGE_DRIVER = "webdriver.edge.driver";

    public static final String KEYP_MOBILE_PLATFORMNAME = "platformName";

    public static final String KEYP_MOBILE_DEVICENAME = "deviceName";

    public static final String KEYP_MOBILE_PLATFORMVERSION = "platformVersion";

    public static final String KEYP_MOBILE_AUTOACCEPTALERTS = "autoAcceptAlerts";

    public static final String KEYP_MOBILE_FULLRESET = "fullReset";

    public static final String KEYP_MOBILE_NEWCOMMANDTIMEOUT = "newCommandTimeout";

    public static final String KEYP_MOBILE_APP= "app";

    public static final String KEYP_MOBILE_BROWSER= "mobile.browser";

    public static final String KEYP_MOBILE_AUTOMATIONNAME= "automationName";

    public static final String KEYP_REPORTING_ENABLED = "reporting.enabled";

    public static final String KEYP_RUNNER_NAME = "runner.name";

    public static final String KEYP_RESULTS_LOCATION = "results.location";

    private static final String KEYP_DEBUG_ERROR_DUMP_ENABLED = "debug.error.dump.enabled";

    public static String testCaseName = "";

    public static final String KEYP_BROWSER_SIZE = "browser.size";

    private String setUpModifier = "none";

	/*
     * 3 levels of hierarchy - user - project - module
	 */

    private static final String[] files = new String[] {
            // The reverse order here is due to he fact they are all loaded in
            // this order and so the later ones loaded overwrite the newer ones
            // process.
            //"{user.home}/capuser.properties",
            "./bootstrap.properties", "./test.properties"};

    private volatile static Configuration configuration; // volatile is needed

    // so that multiple thread can reconcile the instance

   private String settings = null;

    private Properties myprops = null;

    private String platform = null;

    /**
     * Evaluates the application mode and returns true if we are trying to test mobile sites
     *
     * @return true if the application mode is set to "mobile" (ignoring case), otherwise false.
     * @throws StopTestException
     * @see #getApplicatonMode()
     */
    public static boolean isMobile() throws StopTestException {
        return getConfiguration().getApplicatonMode().equalsIgnoreCase("mobile");
    }

    /**
     * Evaluates the application mode and returns true if we are trying to test api
     *
     * @return true if the application mode is set to "api" (ignoring case), otherwise false.
     * @throws StopTestException
     * @see #getApplicatonMode()
     */
    public static boolean isAPI() throws StopTestException {
        return getConfiguration().getApplicatonMode()
                .equalsIgnoreCase("api");
    }

    public Configuration() throws StopTestException {
        settings = System.getProperty(KEY_SETTINGS, null);
        myprops = new Properties(System.getProperties());
        String userhome = System.getProperty("user.home", "./");
        String loaded = myprops.getProperty(KEY_LOADED);
        if (loaded == null) {
            for (String filename : files) {
                try {
                    filename = filename.replace("{user.home}", userhome);
                    myprops.load(new FileInputStream(filename));
                } catch (FileNotFoundException fnfe) {
                    // Ignore any files that aren't present - same as ant build
                    // infrastructure
                } catch (IOException ioe) {
                    // TODO decide what we require here?
                    System.err.println("ERROR: " + ioe.getMessage()); // NOPMD
                }
            }
            myprops.putAll(System.getProperties());
        }
        if (settings == null) {
            settings = myprops.getProperty(KEY_SETTINGS, null);
        }
    }

    public static synchronized Configuration getConfiguration()
            throws StopTestException {
        if (configuration == null)
            configuration = new Configuration();
        return configuration;
    }

    public static synchronized void resetConfiguration() {
        configuration = null;
    }

    public String overrideProperty(String keypart, String value) {
        return (String) myprops.setProperty(keypart, value);
    }

    protected String getProperty(String keypart) {
        return getProperty(keypart, null);
    }

    protected String getProperty(String keypart, String default_value) {
        String value = null;
        if (myprops != null) {

            if (settings != null && settings.length() > 0) {
                value = myprops.getProperty(settings + "."
                        + keypart);
            } else {
                value = myprops.getProperty(keypart);
            }
            if (value == null || value.length() == 0) {
                value = myprops.getProperty("default." + keypart,
                        default_value);
            }
        }
        return interpolate(value);
    }

    public String interpolate(String value) {
        if (null == value) {
            return value;
        }

        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z.]+}");
        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            value = value.replaceAll("\\$\\{", "").replaceAll("}", "");
            value = getProperty(value);
        }

        return value;
    }

    public long getPageLoadTimeout() {
        String timeout = getProperty(KEY_PAGE_TIMEOUT);
        return Long.parseLong(timeout);
    }

    public Boolean getPageLoadTiming() {
        String timing = getProperty(KEY_PAGE_TIMING);
        return Boolean.parseBoolean(timing);
    }

    public String getScreenshotLocation(Class<?> testClass) {
        // TODO Currently relative path to runtime directory - do we want option
        // to hardcode a prefix to it?
        String screenshotPath = testClass.getName().replace(
                testClass.getSimpleName(), "screenshots");
        screenshotPath = screenshotPath.replaceAll("\\.", "/");
        return screenshotPath;
    }

    public String getGridRun() {

        return getProperty(KEY_GRID_RUN);
    }

    public String getGridEnvironment(String envId) {
        return getProperty(KEY_GRID_ENVIRONMENT + envId);

    }

    public String getSetUpModifier() {
        return setUpModifier;
    }


    public String getGridNode() {
        return getProperty(KEY_GRID_NODE);
    }

    public String getSafeBrowser() {
        try {
            return getBrowser();
        } catch (Exception e) {
            return null;
        }
    }

    public String getBrowser() {
        return getProperty(KEY_BROWSER);
    }

    public String getEnvironment() throws StopTestException {
        // get env from system properties first
        String env = getProperty(KEY_ENVIRONMENT);
        if (env == null) {
            throw new StopTestException("environment not set - please set "
                    + KEY_ENVIRONMENT);
        }
        return env;
    }

    protected String getEnvironmentsProperty(String keypart)
            throws StopTestException {
        return getProperty(keypart);
    }

    public String getEnvironmentUrl() throws StopTestException {
        // Default handled by URLHelper
        return getEnvironmentsProperty(KEYP_ENV_URL);
    }

    private String addLastSlash(String value) {
        if (value != null && !value.endsWith(File.separator)) {
            // value = value + File.separator;
            // Matt13th Sept
            value = value + "/";
        }
        return value;
    }

    /**
     * Reads the debug.enabled property
     *
     * @return the value of debug.screenshots.enabled or FALSE if not set.
     * @throws StopTestException
     */
    public String getDebugProperty() throws StopTestException {

        String prop = getProperty(KEYP_DEBUG_ENABLED);
        if (prop == null) {
            prop = "FALSE";
        }
        return prop;

    }

    /**
     * Evaluates the debug flag and returns a boolean.
     *
     * @return true if the autotest.debug.enabled property is set to "true", else false.
     * @throws StopTestException
     */
    public boolean isDebug() throws StopTestException {
        return getDebugProperty().trim().equalsIgnoreCase("true");
    }

    /**
     * @return the value of the autotest.debug.error.dump.enabled property, or FALSE if it is not set.
     * @throws StopTestException
     */
    public String getDebugErrorDumpProperty() throws StopTestException {
        String prop = getProperty(KEYP_DEBUG_ERROR_DUMP_ENABLED);
        if (prop == null) {
            prop = "FALSE";
        }
        return prop;
    }


    /**
     * Evaluates the environment_multi_jvm setting
     *
     * @return true if environment_multi_jvm is set to TRUE, else false
     * @throws StopTestException
     */
    public boolean getMultiJvm() throws StopTestException {

        String multiJvm = getProperty(KEYP_ENV_MULTI_JVM);
        if (multiJvm.toUpperCase().equalsIgnoreCase("TRUE")) {
            return true;
        } else {
            return false;
        }
    }

    public void setApplicationMode(String mode) {
        this.mode = mode;
    }

    /**
     * Returns the application mode as a String. The default is "WEB".
     * If there is a "Mobile" annotation to a test runner (or to a cucumber hook or any other class
     * or if a Cucumber scenario is tagged with @Mobile, this will be set to "MOBILE".
     *
     * @return WEB, or MOBILE, or a variation thereof (this method uses Strings, so spelling is not enforced).
     * @throws StopTestException
     */
    public String getApplicatonMode()  {
        return mode;
    }

    public String getHelperDefinitionLocation() {
        return addLastSlash(getProperty(KEY_HELPER_DEFINITIONS));
    }

    /**
     * This method will return the server name and the port number of the
     * selenium grid hub.
     * @return String Server name and port number.
     * @throws StopTestException
     */

    public String getGridHubServerDetails() throws StopTestException {

        String hubServerDetails = null;

        hubServerDetails = getProperty(KEY_GRID_HUB_SERVERNAME_PORT);

        if (hubServerDetails == null) {
            throw new StopTestException(
                    "Grid hub details not set - please set " + KEY_GRID_HUB_SERVERNAME_PORT);
        }

        return hubServerDetails;
    }

    public String getWebdriverForChrome() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_WEBDRIVER_CHROME_DRIVER);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Chrome exe file not available");
        }

        return driverDetails;
    }

    public String getWebDriverForFirefox() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_WEBDRIVER_FIREFOX_DRIVER);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Gecko exe file not available");
        }

        return driverDetails;
    }

    public String getWebdriverForIE() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_WEBDRIVER_IE_DRIVER);

        if (driverDetails == null) {
            throw new StopTestException(
                    "IE exe file not available");
        }

        return driverDetails;
    }

    public String getWebdriverForEdge() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_WEBDRIVER_EDGE_DRIVER);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Edge exe file not available");
        }

        return driverDetails;
    }

    public String getPlatformNameforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_PLATFORMNAME);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getDeviceNameforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_DEVICENAME);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getNewCommandTimeoutforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_NEWCOMMANDTIMEOUT);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getAppforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_APP);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getAutoAcceptAlertsforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_AUTOACCEPTALERTS);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getFullResetforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_FULLRESET);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getPlatformVersionforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_PLATFORMVERSION);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getBrowserforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_BROWSER);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    public String getAutomationNameforMobile() throws StopTestException {

        String driverDetails = null;

        driverDetails = getProperty(KEYP_MOBILE_AUTOMATIONNAME);

        if (driverDetails == null) {
            throw new StopTestException(
                    "Platform Name not available in properties");
        }

        return driverDetails;
    }

    /**
     * This method will return whether reporting has been enabled.
     *
     * @return String Server name and port number.
     * @throws StopTestException
     */

    public String getReportingEnabled() throws StopTestException {

        String reportingEnabled = null;

        reportingEnabled = getProperty(KEYP_REPORTING_ENABLED);

        if (reportingEnabled == null) {
            reportingEnabled = "false";
        }

        return reportingEnabled;
    }


    public String getRunnerName() throws StopTestException {

        String runnerName = null;

        runnerName = getProperty(KEYP_RUNNER_NAME);

        if (runnerName == null) {
            throw new StopTestException(
                    "Unable to retrieve runner name system properties");
        }

        return runnerName;
    }

    /**
     * This method will return the location of the runner xml result files.
     *
     * @return String Server name and port number.
     * @throws StopTestException
     */

    public String getResultsLocation() throws StopTestException {

        String resultsLocation = null;

        resultsLocation = getProperty(KEYP_RESULTS_LOCATION);

        if (resultsLocation == null) {
            throw new StopTestException("Unable to retrieve results location");
        }

        return resultsLocation;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestCaseName() throws StopTestException {
        return testCaseName;
    }

    public Properties getProperties() {
        Properties combinedProperties = new Properties();
        Properties existingProperties = new Properties();
        existingProperties.putAll(myprops);

        for (Object key : new HashSet<Object>(existingProperties.keySet())) {
            if (key.toString().startsWith(KEY_SETTINGS)) {
                combinedProperties.put(key.toString()
                                .replaceFirst(KEY_SETTINGS,
                                        KEYBASE),
                        existingProperties.get(key));
                existingProperties.remove(key);
            }
        }

        for (Object key : new HashSet<Object>(existingProperties.keySet())) {
            if (key.toString().startsWith("default.")) {
                combinedProperties.put(key.toString().replaceFirst("default","."),existingProperties.get(key));
                existingProperties.remove(key);
            }
        }
        combinedProperties.putAll(existingProperties);
        return combinedProperties;
    }

    public void resetApplicationMode() {
        //Setting default to Web
        this.setApplicationMode("WEB");
    }

    public ArrayList<String> getExcludedSeleniumProperties() {
        return new ArrayList<String>(Arrays.asList(getProperty(KEY_SELENIUM_EXCLUDED_PROPERTIES).toString().split(",")));
    }

    /**
     * Method to get the browser size from properties if it exists
     *
     * @return the property value
     */
    public String getBrowserSize() {
        return getProperty(KEYP_BROWSER_SIZE);
    }

    public String getEnviron() throws StopTestException {
        String environDetails = null;
        environDetails= getProperty(KEY_Environ);
        if (environDetails ==null) {
            throw new StopTestException("Environ Details not set"+KEY_Environ);
        }
        return getProperty(KEY_Environ);
    }

    public void addProperties(String environFileName) {
        try {
            myprops.load(new FileReader("src/test/resources/properties/" + environFileName + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
