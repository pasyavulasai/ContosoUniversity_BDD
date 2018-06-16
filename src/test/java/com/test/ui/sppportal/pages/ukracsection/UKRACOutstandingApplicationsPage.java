//package com.test.ui.sppportal.pages.ukracsection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
////import com.test.seleniumcustomframework.extension.PageElement;
//import org.junit.Assert;
//import org.openqa.selenium.support.FindBy;
//
//public class UKRACOutstandingApplicationsPage extends BasePage {
//
//    @Inject
//    private SeleniumHelper seleniumHelper;
//    @Inject
//
//    private ScrenshotUtils screenshotUtils;
//
//    public String OATitle = "Outstanding Applications";
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
//    @FindBy(css = "span[class*='gbLabelFormNormal']")
//    private PageElement pleaseNoteText;
//
//    @FindBy(css = "div[id*='main_formPendingTask.lblLitOrganisation_repaint'] span")
//    private PageElement organizationLbl;
//
//    @FindBy(css = "div[id*='main_formPendingTask.lblLitProduct_repaint'] span")
//    private PageElement productLbl;
//
//    @FindBy(id = "slcOrganisation")
//    private PageElement organization;
//
//    @FindBy(id = "product")
//    private PageElement prdct;
//
//    @FindBy(css = "u[id*='AccessKeysearch']")
//    private PageElement searchBtn;
//
//
//    public boolean validateOutstandingApplicationPage() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        System.out.println(oatitle.getText());
//        Assert.assertEquals("Outstanding Applications is displaying", "Outstanding Applications", oatitle.getText());
//        System.out.println(searchCriteriaText.getText());
//        Assert.assertEquals("Search Criteria is displaying", "Search Criteria", searchCriteriaText.getText());
//        System.out.println(searchOptionsText.getText());
//        Assert.assertEquals("Click on the icon to hide the search options is displaying", "Click on the icon to hide the search options", searchOptionsText.getText());
//        System.out.println(pleaseNoteText.getText());
//        Assert.assertEquals("Please note, all fields are mandatory. is displaying", "Please note, all fields are mandatory.", pleaseNoteText.getText());
//        System.out.println(organizationLbl.getText());
//        Assert.assertEquals("Organization: label is displaying", "Organization:", organizationLbl.getText());
//        System.out.println(productLbl.getText());
//        Assert.assertEquals("Product: label is displaying", "Product:", productLbl.getText());
//        System.out.println(organization.isDisplayed());
//        Assert.assertEquals("Organization dropdown is displaying", true, organization.isDisplayed());
//        System.out.println(prdct.isDisplayed());
//        Assert.assertEquals("Product dropdown is displaying", true, prdct.isDisplayed());
//        System.out.println(searchBtn.isDisplayed());
//        Assert.assertEquals("Search button is displaying", true, searchBtn.isDisplayed());
//        screenshotUtils.takeScreenShot();
//        return seleniumHelper.isTextExistingonPage(OATitle);
//    }
//
//
//    public void selectSarchOutstandingApplications(int rowIndex) {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitForJSandJQueryToLoad();
//        String org = cmnVrbl.final_SABBData.get(rowIndex).get("Organization").trim();
//        String prdt = cmnVrbl.final_SABBData.get(rowIndex).get("Product").trim();
//        System.out.println("Organization is "+org);
//        System.out.println("Product is "+prdt);
//        seleniumHelper.waitForPageToLoad();
//        seleniumHelper.selectByMatchingVisibleText(organization,org);
//        seleniumHelper.selectByMatchingVisibleText(prdct,prdt);
//        screenshotUtils.takeScreenShot();
//        seleniumHelper.clickUsingJavascript(searchBtn);
//    }
//
//
//}
