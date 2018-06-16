package com.test.helper;

import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;

import java.net.UnknownHostException;

/**
 * Execution Control helper. Contains helper methods to control test execution
 *         (exceptionExplanation), Rename to ExecController, TestController,
 *         AutomationController, etc. (see CruiseControl ;-) and probably move
 *         to other package (e.g. likely together with "Configuration")
 * 
 */
public class ExecControlHelper {

	// TODO look at multi threading issues of stop test

	static ThreadLocal<String> screenshotLocationTL = new ThreadLocal<String>();

	static final String STOP_TEST_PROP_NAME = "autotest.stop_test";

	private static String exceptionExplanation;

	private static Object[] windowHandle = null;

	/**
	 * Sets global variable to stop test execution
	 * 
	 * @param message
	 *            Exception explanation
	 */
	public static void stopTest(String message) {
		System.setProperty(STOP_TEST_PROP_NAME, "true");
		exceptionExplanation = message;

	}

	/**
	 * Clears global variable for stop test to prevent further tests to stop
	 */
	public static void resetStopTest() {
		System.clearProperty(STOP_TEST_PROP_NAME);
		exceptionExplanation = "";
	}

	/**
	 * Check system variables to see if test execution should be aborted and
	 * throw StopTestException
	 * 
	 * @throws StopTestException
	 *             if test execution should be stopped
	 */
	public static void checkStopTest() throws StopTestException {
		if (System.getProperty(STOP_TEST_PROP_NAME) != null) {
			if (System.getProperty(STOP_TEST_PROP_NAME).equals("true"))
				throw new StopTestException(getMessage(), false);
		}
	}

	/**
	 * Get exception explanation if test is stopped
	 * 
	 * @return
	 * @throws StopTestException
	 * @throws UnknownHostException
	 */
	public static String getMessage() {
		return exceptionExplanation + getSystemDetails();
	}

	/**
	 * Switch driver focus to popup window
	 * 
	 * @return
	 * @return
	 * @throws StopTestException
	 */
//	public static void switchDriverToWindowZero() throws StopTestException {
//		// Get current driver instance
//		WebDriver driver = PageFactory.getCurrentDriver();
//		// Get all window id's and put in array
//
//		windowHandle = driver.getWindowHandles().toArray();
//
//		// Use the popup window's driver
//		driver.switchTo().window((String) windowHandle[0]);
//
//	}
//
//	public static void switchDriverToPopup(int handle) throws StopTestException {
//		// Get current driver instance
//		WebDriver driver = PageFactory.getCurrentDriver();
//		// Get all window id's and put in array
//		windowHandle = driver.getWindowHandles().toArray();
//		// Use the popup window's driver
//		driver.switchTo().window((String) windowHandle[handle]);
//
//	}

	/**
	 * Switch driver focus To second window or popup	 *
	 * @return
	 * @throws StopTestException
	 */
//	public static void switchDriverToWindowOne() throws StopTestException {
//		WebDriver driver = PageFactory.getCurrentDriver();
//		windowHandle = driver.getWindowHandles().toArray();
//		int i=0;
//		while (windowHandle.length <2) {
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			if (i>10){
//				break;
//			}
//			i++;
//			windowHandle = driver.getWindowHandles().toArray();
//		}
//
//		// Use the popup window's driver (array windowHandle is populated from
//		// switchDriverToPopup())
//
//		try {
//			driver.switchTo().window((String) windowHandle[1]);
//		} catch (Exception e) {
//			// if window 1 does not exist set to window 0
//
//			driver.switchTo().window((String) windowHandle[0]);
//		}
//
//	}

	public static String getScreenshotLocation() {
		return screenshotLocationTL.get();
	}

	public static void setScreenshotLocation(String screenshotLocation) {
		screenshotLocationTL.set(screenshotLocation);
	}

	/**
	 * Used to close the popup window for the given index
	 * 
	 * @param index
	 * @throws StopTestException
	 */
//	public static void closePopUpWindow(int index) throws StopTestException {
//		// Get current driver instance
//		WebDriver driver = PageFactory.getCurrentDriver();
//
//		windowHandle = driver.getWindowHandles().toArray();
//
//		// Use the popup window's driver (array windowHandle is populated from
//		// switchDriverToPopup())
//
//		try {
//			driver.switchTo().window((String) windowHandle[index]).close();
//		} catch (Exception e) {
//			// if window 1 does not exist set to window 0
//		}
//		driver.switchTo().window((String) windowHandle[0]);
//	}

	/**
	 * 
	 */
	public static String getSystemDetails() {

		String systemDetails = "";

		try {
			systemDetails = " env = "
					+ Configuration.getConfiguration().getEnvironment()
					+ " System host "
					+ Configuration.getConfiguration().getGridNode();
		} catch (Exception e) {
			// ignore.
		}

		return systemDetails;
	}

}
