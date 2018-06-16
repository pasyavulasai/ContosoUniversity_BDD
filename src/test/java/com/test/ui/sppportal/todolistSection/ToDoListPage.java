//package com.test.ui.sppportal.todolistSection;
//
//import com.google.inject.Inject;
//import com.test.helper.BasePage;
//import com.test.helper.utils.ScrenshotUtils;
//import com.test.helper.utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
//import org.openqa.selenium.support.FindBy;
//
//public class ToDoListPage extends BasePage {
//    @Inject
//    private ScrenshotUtils screenshotUtils;
//    @Inject
//    private SeleniumHelper seleniumHelper;
//
//    @FindBy(css = "table[id*='main_tabla_1_HEADER_SCROLL']")
//    private PageElement tableContent;
//
//    @FindBy(css = "a[id*='button1']")
//    private PageElement errorAcceptBtn;
//
//    @FindBy(xpath = "//table[@id='main_tabla_1_ROWS_TABLE']/tbody/tr[1]/td[1]/div/div")
//    private PageElement firstRecordData;
//
//    @FindBy(css = "a[id*='task']")
//    private PageElement taskBtn;
//
//    @FindBy(xpath = "//table[@id='main_tabla_1_ROWS_TABLE']/tbody/tr[1]/td[1]/div/div")
//    private PageElement processName;
//
//    public void todoListData() {
//        pageFactory.invalidate(this);
//        seleniumHelper.waitforElementVisible(processName);
//        String text = null;
//        text = processName.getText();
////        System.out.println("--------" + text + "------------------------");
//        while (text.equals("---")) {
//           seleniumHelper.waitForJSandJQueryToLoad();
//            driver.navigate().refresh();
//            seleniumHelper.waitForJSandJQueryToLoad();
//            processName.invalidate();
//            text = processName.getText();
////            System.out.println("--------" + text + "------------------------");
//            if(!text.equals("---")) {
//                break;
//            }
//        }
//        if (text.equals("Account Transfer") || text.equals("Account Opening Savings") || text.equals("Account Opening Banking")|| text.equals("Business Account Transfer")|| text.equals("Business Overdraft Request")|| text.equals("Business Debit Card")) {
//            processName.click();
//            screenshotUtils.takeScreenShot();
//            seleniumHelper.clickUsingJavascript(taskBtn);
//        }
//    }
//
//}
