//package PageObjects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Capabilities;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.Assert.assertEquals;
//
//
///**
// * Created by PASYA on 31/10/2016.
// */
//public class BrowserSetup {
//    public WebDriver driver;
//    private String  baseUrl;
//    private String	browserName;
//    private String	browserVersion;
//
//    public void setUp(String browserType) throws Exception {
//        switch (browserType) {
//            case "ff":
//                System.setProperty("webdriver.gecko.driver", "D:\\Automation Projects\\ContosoUniversity_BDD\\browserdrivers\\geckodriver.exe");
//                driver = new FirefoxDriver();
//                break;
//            case "html":
//                System.setProperty("webdriver.gecko.driver", "D:\\Automation Projects\\ContosoUniversity_BDD\\browserdrivers\\geckodriver.exe");
//                driver = new HtmlUnitDriver();
//                break;
//            case "chrome":
//                System.setProperty("webdriver.gecko.driver", "D:\\Automation Projects\\ContosoUniversity_BDD\\browserdrivers\\geckodriver.exe");
//                driver = new ChromeDriver();
//                break;
//            case "ie":
//                System.setProperty("webdriver.gecko.driver", "D:\\Automation Projects\\ContosoUniversity_BDD\\browserdrivers\\geckodriver.exe");
//                driver = new InternetExplorerDriver();
//                break;
//            case "safari":
//                System.setProperty("webdriver.gecko.driver", "D:\\Automation Projects\\ContosoUniversity_BDD\\browserdrivers\\geckodriver.exe");
//                driver = new SafariDriver();
//                break;
//            default:  System.out.println("Invalid browser selection");
//                break;
//        }
//        driver.manage().deleteAllCookies();
//        baseUrl = "http://localhost/MyApp";
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().deleteAllCookies();
//        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
//        browserName = caps.getBrowserName();
//        browserVersion = caps.getVersion();
//        System.out.println("Automated test run. We’re running on " + browserName + " " + browserVersion);
//    }
//
//    public WebElement returnWebElement(String identifier, String element){
//        WebElement webElement=null;
//        switch (identifier) {
//            case "id": webElement= driver.findElement(By.id(element));
//                break;
//            case "xpath": webElement= driver.findElement(By.xpath(element));
//                break;
//            case "css": webElement=driver.findElement(By.cssSelector(element));
//                break;
//            case "link":  webElement= driver.findElement(By.linkText(element));
//                break;
//            case "className": webElement= driver.findElement(By.className(element));
//                break;
//            default:  System.out.println("Invalid identifier found");
//                break;
//        }
//        return webElement;
//    }
//
//    public void contentCheck(String identifier, String text)
//    {
//       String textCheck = driver.findElement(By.xpath(identifier)).getText();
//        assertEquals(text,textCheck);
//    }
//
//    public void pageChecker(String ptitle){
//       // String url = driver.getCurrentUrl();
//        String title = driver.getTitle();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//       // System.out.println("\n-------------------------------"+title+"-------------------------------------");
//        assertEquals(title,ptitle);
//    }
//
//    public void enterText(String sText, String identifier)
//    {
//        driver.findElement(By.xpath(identifier)).clear();
//        driver.findElement(By.xpath(identifier)).click();
//        driver.findElement(By.xpath(identifier)).sendKeys(sText);
//    }
//
//     public void submitLink(String identifier)
//        {
//            driver.findElement(By.xpath(identifier)).click();
//        }
//
//
//    public void LoginUserValidation(String userName, String paassword){
//        driver.findElement(By.id("MainContent_LoginUser_UserName")).sendKeys(userName);
//        driver.findElement(By.id("MainContent_LoginUser_Password")).sendKeys(paassword);
//        driver.findElement(By.id("MainContent_LoginUser_LoginButton")).click();
//    }
//
//    public String returnText(String identifier)
//    {
//        String stext = driver.findElement(By.xpath(identifier)).getText();
////        System.out.println("\n-------------------------------"+stext+"-------------------------------------");
//        return stext;
//    }
//
//    public void clickMainMenuOption(String identifier, String lText) {
//        //System.out.println("\n-------------------------------" + lText + "-------------------------------------");
//        Actions action = new Actions(driver);
//        WebElement we = driver.findElement(By.xpath(identifier));
//        action.moveToElement(we).moveToElement(driver.findElement(By.linkText(lText))).click().build().perform();
//    }
//
//    public  void clickOnSubMenuOption(String sText, String idetifier)
//    {
//        Actions actions = new Actions(driver);
//        WebElement mainMenu = driver.findElement(By.linkText(sText));
//        actions.moveToElement(mainMenu);
//        WebElement subMenu = driver.findElement(By.xpath(idetifier));
//        actions.moveToElement(subMenu);
//        actions.click().build().perform();
//    }
//
//    public void handle_Dynamic_Webtable(String identifier, String name) {
//        //To locate table.
//        WebElement mytable = driver.findElement(By.id(identifier));
//        //To locate rows of table.
//        List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
//        //To calculate no of rows In table.
//        int rows_count = rows_table.size();
//        //System.out.println("Number of Rows in the table are " + rows_count);
//        //Loop will execute till the last row of table.
//        for (int row = 0; row < rows_count; row++) {
//            //To locate columns(cells) of that specific row.
//            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
//            //To calculate no of columns(cells) In that specific row.
//            int columns_count = Columns_row.size();
//            //System.out.println("Number of cells In Row " + row + " are " + columns_count);
//            //Loop will execute till the last cell of that specific row.
//            for (int column = 0; column < columns_count; column++) {
//                //To retrieve text from that specific cell.
//                String celtext = Columns_row.get(column).getText();
//               // System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celtext);
//            }
//          //  System.out.println("--------------------------------------------------");
//        }
//    }
//
//    public void checkUserNameOnLogin(String identifier, String userName)
//    {
//        String stext = driver.findElement(By.id(identifier)).getText();
////        System.out.println("\n-------------------------------"+stext+"-------------------------------------");
//        assertEquals(stext,userName);
//    }
//
//    public void clickOnLink(String identifier, String element)
//    {
//        System.out.println("\nIdentifier is "+identifier + " and the webelement is "+element);
//        switch (identifier) {
//            case "id": driver.findElement(By.id(element)).click();
//                assertEquals("Log In", driver.getTitle());
//                break;
//            case "xpath": driver.findElement(By.xpath(element)).click();
//                break;
//            case "css": driver.findElement(By.cssSelector(element)).click();
//                break;
//            case "link":  driver.findElement(By.linkText(element)).click();
//                break;
//            case "className": driver.findElement(By.className(element)).click();
//                break;
//            default:  System.out.println("Invalid identifier found");
//                break;
//        }
//    }
//
//    public void tearDown() {
//        driver.close();
//    }
//    public void goToCUHomePage() {
//        driver.get(baseUrl);
//    }
//
//}
