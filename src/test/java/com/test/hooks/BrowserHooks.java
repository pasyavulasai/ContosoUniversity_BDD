package com.test.hooks;

import com.google.inject.Inject;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.helper.utils.CommonFunctions;
import com.test.helper.utils.ExcelUtils;
import com.test.ui.CommonVariables;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BrowserHooks {

    @Inject
    public WebDriver driver;
    @Inject
    CommonVariables cmnvrbls;
    @Inject
    CommonFunctions cmnFunctn;
    @Inject
    ExcelUtils xclUtil;
    @Inject
    Configuration configuration;

    String pre_WorkBookName = "CU_TestData";
    String pre_CU_TestData = "TestData";
    String pre_CCARD_TestData = "CCARD_TestData";


//    String prod_WorkBookName = "ProdTestData";
//    String prod_SABB_TestData = "SABB_TestData";
//    String prod_CCARD_TestData = "CCARD_TestData";


    @Before
    public void setup() throws StopTestException {
        setXLSData();
//        boolean areAllValuesChecked = areAllValuesChecked();
//        Assert.assertTrue(areAllValuesChecked);
    }

    private void setXLSData() throws StopTestException {
        if(configuration.getEnviron().equals("pre")) {
            cmnvrbls.pre_CUData = xclUtil.getMapFromGivenExcel(pre_WorkBookName, pre_CU_TestData);
            cmnvrbls.pre_CCARDData = xclUtil.getMapFromGivenExcel(pre_WorkBookName, pre_CCARD_TestData);
            cmnvrbls.final_CUData= cmnvrbls.pre_CUData;
        }
//        else if (configuration.getEnviron().equals("prod")) {
//            cmnvrbls.prod_SABBData = xclUtil.getMapFromGivenExcel(prod_WorkBookName, prod_SABB_TestData);
//            cmnvrbls.final_SABBData= cmnvrbls.prod_SABBData;
//        }
    }

//    private boolean areAllValuesChecked() {
//        ArrayList<Map<String,String>> prod_check_sheet;
//        prod_check_sheet = xclUtil.getMapFromGivenExcel(prod_WorkBookName, prod_SABB_TestData);
//        return cmnFunctn.areAllValuesChecked(prod_check_sheet);
//    }

}
