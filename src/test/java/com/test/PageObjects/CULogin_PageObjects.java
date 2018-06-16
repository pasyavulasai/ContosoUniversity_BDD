package com.test.PageObjects;

/**
 * Created by PASYA on 31/10/2016.
 */
public class CULogin_PageObjects {
//        private WebDriver	driver;
//        private String		baseUrl;
//        @FindBy(how = How.XPATH, using = ".//ul[@id='navlist']/li/a[text()='Admin']")
//        private WebElement	AdminLink;
//
//        @FindBy(how = How.CSS, using = "input#UserName")
//        private WebElement	UserName;
//
//        @FindBy(how = How.CSS, using = "input#Password")
//        private WebElement	Password;
//
//        @FindBy(how = How.CSS, using = "div#main input[type=submit]")
//        private WebElement	LoginBtn;
//
//        public void setUp() throws Exception {
//            driver = new FirefoxDriver();
//            baseUrl = "http://localhost:90";
//            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//            driver.manage().window().maximize();
//        }
//
//        public void goToHomePage() {
//            driver.get(baseUrl);
//        }
//
//        public void goToAdminPage() {
//            driver.findElement(By.xpath(".//ul[@id='navlist']/li/a[text()='Admin']")).click();
//        }
//
//        public void attemptLogin(String UserName, String Password) {
//            driver.findElement(By.cssSelector("input#UserName")).clear();
//            driver.findElement(By.cssSelector("input#UserName")).sendKeys(UserName);
//
//            driver.findElement(By.cssSelector("input#Password")).clear();
//            driver.findElement(By.cssSelector("input#Password")).sendKeys(Password);
//
//            driver.findElement(By.cssSelector("div#main input[type=submit]")).click();
//        }
//
//        public void assertErrorMessage(String expectedErrorMessage) {
//            String actualErrorMessage = driver.findElement(By.cssSelector(".validation-summary-errors>span")).getText();
//            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
//        }
//
//        public void assertPasswordErrorMessage(String expectedErrorMessage) {
//            String actualErrorMessage = driver.findElement(By.cssSelector("span.field-validation-error>span[htmlfor=Password]")).getText();
//            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
//        }
//
//        public void assertLoginPageVisible() {
//            String PageHeader = driver.findElement(By.cssSelector("div#main>h2")).getText();
//            Assert.assertTrue(PageHeader.contains("Log On"));
//        }

    }
