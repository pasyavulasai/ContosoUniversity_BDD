package com.test.ui.Test;

import com.test.helper.BasePage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TestPage extends BasePage {



    //actual
    //String url_part1 =  "http://107.15.186.12:8080/job/copy_buildretial/";
    //String url_part2 =  "/cucumber-html-reports/overview-features.html";

    //workaround
    String url_part1 =  "http://107.15.186.12:8080/job/Experimental/job/copy_buildretial_pipeliner/";
    String url_part2 =  "/cucumber-html-reports/overview-features.html";
    String url_part3 =  "http://107.15.186.12:8080/job/Experimental/job/copy_buildinternetregression_pipeliner/";
    String url_part4 =  "http://107.15.186.12:8080/job/Experimental/job/copy_buildportalregression_pipeliner/";





    String loginLocator = "id:userInput";

    public void someTestDataEnter() throws InterruptedException {

        //System.out.println(propRead.testData.readProperty("key"));
        //System.out.println(propRead.elementLocator.readProperty("elementname"));
        //System.out.println(propRead.environmentData.readProperty("url"));
        driver.get("https://retail.santander.co.uk/LOGSUK_NS_ENS/BtoChannelDriver.ssobto?dse_operationName=LOGON&dse_processorState=initial&redirect=S");
        //driver.get("http://lynxpreoff.unix.santanderuk.pre.corp/comunes/VAuth.php");
        //driver.get("http://saba.santanderuk.pre.corp/PORINT_ENS/BtoChannelDriver.ssobto?dse_operationName=PO_Index&in.NombrePortal=SPP&agregador=SPP ");

        //seleniumHelp.getElement("id:lst-ib").sendKeys("Selenium");
        //Thread.sleep(10000);
        //driver.findElement(By.id("lst-ib")).sendKeys("Selenium");
     //   driver.findElement(By.id("lst-ib")).sendKeys("Selenium");
//        seleniumHelp.enterText(seleniumHelp.getElement("id:lst-ib"),"selenium");

        //driver.get(propRead.environmentData.readProperty("url"));


    }


    public void check() throws IOException {

        JSONObject obj = new JSONObject();
        obj.put("Name", "crunchify.com");
        obj.put("Author", "App Shah");

        JSONArray company = new JSONArray();
//        company.add("Compnay: eBay");
//        company.add("Compnay: Paypal");
//        company.add("Compnay: Google");
        obj.put("Company List", company);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("C:\\Automation\\abc.txt")) {
//            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
    }
    public void check1() throws IOException {

        JSONObject obj = new JSONObject();
        obj.put("ReportName", "Retail Report");
        obj.put("Date", "some timestamp");

        JSONArray cellList = new JSONArray();
        JSONArray cell = new JSONArray();
//        cell.add("ApplicationName: UBLO01_ENS1");
//        cell.add("URL: URLgoeshere");
//        cell.add("Enabled: Yes");
//        cellList.add("SABB:cell");
        obj.put("CellList", cellList);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("C:\\Automation\\abcne.txt")) {
//            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
    }


    public void setJobWorkaround(String number) {
        System.setProperty("JENKINS_ARG",number);
        //System.out.println(System.getProperty("JENKINS_ARG"));
    }


    public void hitReportURL(String urlPart1, String urlPart2) {
        //setJobWorkaround("184");
        //String str="abc"+System.getProperty("JENKINS_ARG")+"abc";
        //System.out.println("passed value is "+ str);
        //System.out.println("& one more time"+ "userdetail");

        String url = urlPart1+System.getProperty("JENKINS_ARG")+urlPart2;
        System.out.println(url);
        navigationHelper.navigateTo(url);
        screnshotUtils.takeScreenShot();
    }


    public void test() {
        String text = "abc,def,ghi";
        List l  =Arrays.asList(text.split(","));
        System.out.println(Arrays.asList(text.split(",")));
    }




    public void hitAllBankPaymentsURL() {
        hitReportURL(url_part1,url_part2);
    }
    public void hitRetailInternetRegressionURL() {
        hitReportURL(url_part3,url_part2);
    }

    public void hitPortalRegressionURL() {
        hitReportURL(url_part4,url_part2);
    }

    public void hitCustomisedURL() {
        //System.setProperty("Host","https://jenkins-team-taasapp-pre.appls.cap2.paas.gsnetcloud.corp/job/Job_InfrastructureRegression/");
        //System.setProperty("BuildNo","15");

        String url_to_hit =System.getProperty("Host")+System.getProperty("BuildNo")+url_part2;
        System.out.println(url_to_hit);
        navigationHelper.navigateTo(url_to_hit);
        screnshotUtils.takeScreenShot();
    }
}
