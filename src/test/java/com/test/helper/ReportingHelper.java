package com.test.helper;

import com.test.configuration.Configuration;
import com.test.configuration.PageFactory;
import com.test.exceptions.StopTestException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.Augmenter;

/**
 * This class is a helper class that will have accessor methods for Reporting.
 * 
 */

public class ReportingHelper {

	private static File path = null;

	private static String runnerName;

	// static Workbook wb = new HSSFWorkbook();

	// static Sheet sheet = null;

	static List<Result> results = new ArrayList<Result>();

	/**
	 * This method will take a screenshot whenever it is called; using in-build
	 * webdriver functionality
	 * 
	 * @return String screenshot filename
	 * @throws StopTestException
	 * @throws StopTestException
	 */
	public static String takeScreenShot() throws StopTestException {
		File screenshotFile = new File("");
		WebDriver driver = PageFactory.getCurrentDriver();

		if (driver instanceof InternetExplorerDriver) {
			try {
				// Construct all what is needed to make screenshot
				Dimension screenDim = Toolkit.getDefaultToolkit()
						.getScreenSize();
				Rectangle screenBounds = new Rectangle(0, 0, screenDim.width,
						screenDim.height);
				Robot robot = new Robot();

				// Take a screenshot
				BufferedImage image = robot.createScreenCapture(screenBounds);

				// TODO location for screenshots should be configurable from
				// settings.xml
				String location = ExecControlHelper.getScreenshotLocation();

				// Construct a path to screenshots
				String screenshotPath = null;
				if (System.getProperty("concordion.output.dir") != null) {
					// Store screenshot where concordion stores results
					screenshotPath = System
							.getProperty("concordion.output.dir")
							+ File.separator + location;

				} else {
					// Store screenshot in temp location where concordion will
					// store results as well if property was not set
					screenshotPath = System.getProperty("java.io.tmpdir")
							+ File.separator + "concordion" + File.separator
							+ location;
					// remove any \\ and replace with single \
					screenshotPath = screenshotPath.replace("\\\\",
							File.separator);
				}

				// Save screenshot
				screenshotFile = new File(screenshotPath + File.separator
						+ "screenshot" + System.currentTimeMillis() + ".png");
				// Create folder if it doesn't exist
				screenshotFile.mkdirs();
				ImageIO.write(image, "png", screenshotFile);

			} catch (AWTException e) {
				System.err.println("Can not take a screenshot: "
						+ e.getMessage());
			} catch (IOException e) {

				System.err.println("Can not take a screenshot: "
						+ e.getMessage());
				// } catch (StopTestException e) {
				// System.err.println("Can not take a screenshot: "
				// + e.getMessage());
			}

			// TODO Make sure browser is in focus before making screenshot

		}

		if (screenshotFile.getName().length() == 0) {
			return "no screenshot";
		} else {
			return screenshotFile.getAbsolutePath();
		}

	}

//	public static String getRemoteWebDriverEnv() {
//		String env = "";
//		try {
//			driver = PageFactory.getRemoteWebDriver("firefox");
//			env = driver.getCurrentUrl();
//		} catch (Exception e) {
//
//		}
//		return env;
//	}

	/**
	 * This method will scan the test case log xml files and data mine values
	 * which will be used in a runner report.
	 * @return List<Result> A list of Result objects.
	 * @throws StopTestException
	 */
	public static List<Result> getResultsForTestCase() throws StopTestException {

		Configuration config = Configuration.getConfiguration();
		String resultsLocation = config.getResultsLocation();
		Pattern pattern;
		Matcher matcher;
		String environment;
		String sessionID;
		String fileName;
		int endIndex;
		int lastIndexFullStop;
		String testName;

		// Get the list of test results files.
		final File folder = new File(resultsLocation);

		for (File file : folder.listFiles()) {

			fileName = file.getName();
			environment = "";
			sessionID = "";

			// Create a new Result object to hold the test results.
			Result testResult = new Result();

			// Get the test name
			endIndex = 0;
			lastIndexFullStop = 0;
			endIndex = fileName.lastIndexOf(".xml");
			lastIndexFullStop = fileName.lastIndexOf(".", endIndex - 1);
			testName = fileName.substring(lastIndexFullStop + 1, endIndex);

			if (!testName.toUpperCase().equals("REPORTINGHELPERTEST")) {

				testResult.setTestName(testName);

				// Read the result file and get the successes, failures
				// and exceptions figures.
				String line = null;

				try {
					// FileReader reads text files in the default encoding.
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(
							fileReader);

					while ((line = bufferedReader.readLine()) != null) {

						// Environment.
						pattern = Pattern
								.compile("^HttpPost\\s+url\\s+=\\s+\\[http://(\\w+).*");
						matcher = pattern.matcher(line);
						if (matcher.find() && environment.length() == 0) {
							testResult.setEnvironemnt(matcher.group(1));
						}

						// SessionID.
						pattern = Pattern
								.compile("^HttpPost\\s+sessionId=\\[(\\d+)]");
						matcher = pattern.matcher(line);

						if (matcher.find() && sessionID.length() == 0) {
							testResult.setSessionID(matcher.group(1));
						}

						// Failure message.
						pattern = Pattern
								.compile("^\\s+<failure message=\"(.*)\\.\"");
						matcher = pattern.matcher(line);
						if (matcher.find() || environment.length() > 0) {
							testResult.setErrorMessage(matcher.group(1));
						}

						// Error message.
						matcher = pattern.matcher(line);
						if (matcher.find()) {
							testResult.setErrorMessage(matcher.group(1));
						} else {

							pattern = Pattern
									.compile("^\\s+<error message=\"(.*)");

							matcher = pattern.matcher(line);
							if (matcher.find()) {
								String errorMsg = matcher.group(1);
								if (errorMsg.length() > 100) {
									testResult.setErrorMessage(matcher.group(1)
											.substring(0, 100));
								} else {
									testResult
											.setErrorMessage(matcher.group(1));
								}
								// if there has been an Error!
								// break as no failure / success details
								// will be available as the test case hasn't had
								// a chance to
								// run.
								break;
							}
						}

						// Successes: 2, Failures: 0
						// Successes: 2, Failures: 6, Exceptions: 6
						pattern = Pattern
								.compile("Successes:\\s(\\d+),\\sFailures:\\s(\\d+)$");
						matcher = pattern.matcher(line);
						if (matcher.find()) {
							testResult.setSuccesses(matcher.group(1));
							testResult.setFailures(matcher.group(2));
							testResult.setExceptions("0");
							break;
						} else {
							// Successes: 3, Failures: 0, Exceptions: 10
							pattern = Pattern
									.compile("Successes:\\s(\\d+),\\sFailures:\\s(\\d+),\\sExceptions:\\s(\\d+)$");
							matcher = pattern.matcher(line);
							if (matcher.find()) {
								testResult.setSuccesses(matcher.group(1));
								testResult.setFailures(matcher.group(2));
								testResult.setExceptions(matcher.group(3));
								break;
							}

						}

					}

					// Always close files!
					fileReader.close();
					bufferedReader.close();
				} catch (FileNotFoundException ex) {
					throw new StopTestException("Unable to open file: "
							+ file.getName() + " in ReportingHelper.java");
				} catch (IOException ex) {
					throw new StopTestException("Error reading file : "
							+ file.getName() + " in ReportingHelper.java");
				}

			}

			// Add the results to the results array.
			results.add(testResult);

		}

		return results;
	}


	/**
	 * This method will take a screenshot of the browser page at the failure
	 * point when running tests against the grid.
	 * 
	 * C:/<TestCaseName>_<TimeStamp>.png
	 * 
	 * @throws StopTestException
	 * @throws IOException
	 * 
	 */
	public static void takeGridScreenShot() throws StopTestException,
			IOException {

		Configuration config = Configuration.getConfiguration();

		// RemoteWebDriver does not implement the TakesScreenshot class
		// if the driver does have the Capabilities to take a screenshot
		// then Augmenter will add the TakesScreenshot methods to the instance
		WebDriver webDriver = PageFactory.getCurrentDriver();
		WebDriver augmentedDriver = new Augmenter().augment(webDriver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);

		String screenshotLocation = (System.getProperty("user.home") + "/");

		FileUtils.copyFile(screenshot,
				new File(screenshotLocation + config.getTestCaseName() + "_"
						+ System.currentTimeMillis() + ".png"));

	}

	/**
	 * This method will capture the html source code of the page and create a
	 * file to store it.
	 * 
	 * The file will be created on the C Drive in the following format.
	 * 
	 * C:/<TestCaseName>_<TimeStamp>.txt
	 * 
	 * @throws StopTestException
	 * 
	 */
	public static void getPageSourceCode() throws StopTestException {

		Writer writer = null;
		String fileLocation = (System.getProperty("user.home") + "/");

		try {
			WebDriver webDriver = PageFactory.getCurrentDriver();
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileLocation
							+ Configuration.getConfiguration()
									.getTestCaseName() + "_"
							+ System.currentTimeMillis() + ".txt"), "utf-8"));
			writer.write(webDriver.getPageSource());
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}

	}
}
