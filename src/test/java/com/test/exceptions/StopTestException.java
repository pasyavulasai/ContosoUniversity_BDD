package com.test.exceptions;

import com.test.helper.ExecControlHelper;

/**
 * Exception which shall be thrown in order to stop execution of current test
 *
 */
public class StopTestException extends Exception {

	private static final long serialVersionUID = 1L;

	private String screenshot;

	/**
	 * Takes a screenshot if screenshot parameter was set to true
	 * 
	 * @param message
	 * @param screenshot
	 */
	public StopTestException(String message, boolean screenshot) {

		// if (screenshot) {
		// this.initCause(new Throwable(message + ", Screenshot: "
		// + ReportingHelper.takeScreenShot()));
		// this.setScreenshot(ReportingHelper.takeScreenShot());
		// } else {
		this.initCause(new Throwable(message));
		// }

	}

	public StopTestException(String message, Throwable exception) {
		super(message, exception);

		// Set global variable to say we need to stop test
		ExecControlHelper.stopTest(message);

	}

	public StopTestException(String message) {
		super(message);

		// Set global variable to say we need to stop test
		ExecControlHelper.stopTest(message);
	}

	public StopTestException(String messageFormat, Object... arguments) {
		this(String.format(messageFormat, arguments));
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public String getScreenshot() {
		return screenshot;
	}

}
