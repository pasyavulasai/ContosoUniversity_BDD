//package PageObjects;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.How;
//
///**
// * Created by PASYA on 31/10/2016.
// */
//
//public class CUHomePageObjects {
//
//    @FindBy(how = How.XPATH, using = ".//form/div[3]/div[2]/p")
//    private WebElement RegisterInfoText;
//    @FindBy(how = How.XPATH, using = ".//form/div[3]/div[2]/h2")
//    private WebElement	LoginText;
//    @FindBy(how = How.XPATH, using = ".//div[3]/div[2]/div[2]/fieldset/legend")
//    private WebElement	AccountInfoText;
//
//    private WebElement registerLink;
//    public WebElement getRegisterInfoText() {
//        return RegisterInfoText;
//    }
//
//
//
//
////    Actions actions = new Actions(driver);
////    WebElement mainMenu = driver.findElement(By.linkText("menulink"));
////    actions.moveToElement(mainMenu);
////
////    WebElement subMenu = driver.findElement(By.cssSelector("subLinklocator"));
////    actions.moveToElement(subMenu);
////    actions.click().build().perform();
////
//
//
//    public void setRegisterInfoText(WebElement registerInfoText) {
//        RegisterInfoText = registerInfoText;
//    }
//
//    public WebElement getLoginText() {
//        return LoginText;
//    }
//
//    public void setLoginText(WebElement loginText) {
//        LoginText = loginText;
//    }
//
//    public WebElement getAccountInfoText() {
//        return AccountInfoText;
//    }
//
//    public void setAccountInfoText(WebElement accountInfoText) {
//        AccountInfoText = accountInfoText;
//    }
//
//    public WebElement getRegisterLink(WebElement element) {
//        return registerLink;
//    }
//
//    public void setRegisterLink(WebElement registerLink) {
//        this.registerLink = registerLink;
//    }
//}