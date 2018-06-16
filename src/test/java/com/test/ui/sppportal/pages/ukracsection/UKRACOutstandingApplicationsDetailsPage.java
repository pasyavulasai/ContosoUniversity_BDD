//package com.test.ui.sppportal.pages.ukracsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import com.test.ui.CommonVariables;
//import org.junit.Assert;
//import org.openqa.selenium.support.FindBy;
//
//public class UKRACOutstandingApplicationsDetailsPage extends BasePage {
//    @Inject
//    private CommonVariables commonVariables;
//
//    @Inject
//    private SeleniumHelper seleniumHelper;
//
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//
//    public String OAWebTableHeaderText = "Outstanding Applications per Group";
//
//    @FindBy(css = "div[class*='Title']")
//    private PageElement oatitle;
//
//    @FindBy(xpath = ".//*[@id='main_cabBuscador_text']/span[1]")
//    private PageElement searchCriteriaText;
//
//    @FindBy(xpath = ".//*[@id='main.cabBuscador#subtitle']")
//    private PageElement searchOptionsText;
//
//    @FindBy(id = "slcOrganisation")
//    private PageElement oaOrganization;
//
//    @FindBy(id = "product")
//    private PageElement oaProduct;
//
//    @FindBy(css = "u[id*='AccessKeyprint']")
//    private PageElement printBtn;
//
//    @FindBy(xpath = ".//*[@id='main_cabTabla_text']/span[1]")
//    private PageElement oaSearchResultsHeader;
//
//    @FindBy(id = "main_tblListado_ROWSFIX_TABLE")
//    private PageElement oaSearchResultstable;
//
//
////    public void searchOutstandingApplications(int index) {
////        pageFactory.invalidate(this);
////        seleniumHelper.waitForJSandJQueryToLoad();
//////        seleniumHelper.selectByMatchingVisibleText(oaOrganization,commonVariables.sabbAWSHealthcheck.get(index).get("Organization").trim());
//////        seleniumHelper.selectByMatchingVisibleText(oaProduct,commonVariables.sabbAWSHealthcheck.get(index).get("Product").trim());
////        screenshotUtils.takeScreenShot();
////        seleniumHelper.clickUsingJavascript(searchBtn);
////    }
//
//
//    public boolean validateSearchResults() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        System.out.println(oatitle.getText());
//        Assert.assertEquals("Outstanding Applications is displaying", "Outstanding Applications", oatitle.getText());
//        System.out.println(searchCriteriaText.getText());
//        Assert.assertEquals("Search Criteria is displaying", "Search Criteria", searchCriteriaText.getText());
//        System.out.println(searchOptionsText.getText());
//        Assert.assertEquals("Click on the icon to show the search options is displaying", "Click on the icon to show the search options", searchOptionsText.getText());
//        System.out.println(oaSearchResultsHeader.getText());
//        Assert.assertEquals(oaSearchResultsHeader.getText(), "Outstanding Applications per Group");
//        System.out.println(printBtn.isDisplayed());
//        Assert.assertEquals("Search button is displaying", true, printBtn.isDisplayed());
//        screenshotUtils.takeScreenShot();
//        return seleniumHelper.isTextExistingonPage(OAWebTableHeaderText);
//
////        screenshotUtils.takeScreenShot();
////        String title = driver.findElement(By.xpath(".//*[@id='main_titGeneral_REPAINT']/div[2]")).getText();
////        System.out.println("----"+title);
////        Assert.assertTrue(String.valueOf(title.equals("Outstanding Applications")),true);
////
////        Assert.assertNotNull(oaSearchResultstable.getSize());
////        Assert.assertTrue(String.valueOf(printBtn.isDisplayed()), true);
////        return oaSearchResultsHeader.getText();
//    }
//}
