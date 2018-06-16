package com.test.configuration;

import com.google.inject.Inject;
import com.test.helper.NavigationHelper;
import com.test.helper.utils.CommonFunctions;

public class URLCollection {

    @Inject
    protected PropertyConfiguration propertyConfiguration;
    @Inject
    protected NavigationHelper navigationHelper;
    @Inject
    protected CommonFunctions commonFunctions;


    public void loadSingleSignOnPortalURL() {
        System.out.println(propertyConfiguration.getProperty("CU_PORTAL_URL"));
        navigationHelper.navigateTo(propertyConfiguration.getProperty("CU_PORTAL_URL"));
    }

//    public void loadRetailGeneralPortalCFSales() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("RETAIL_GENERAL_PORTAL_CF_SALES_URL"));
//
//    }
//
//    public void loadDirectRetailGeneralPortalCustomerFinancialRiskURL() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("RETAIL_GENERAL_PORTAL_CF_RISKS_URL"));
//
//    }
//
//    public void loadDirectRetailGeneralPortalDCCollectionReviewURL() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("RETAIL_GENERAL_PORTAL_DC_COLLECTION_REVIEW"));
//
//    }
//
//    public void loadDirectRetailGeneralPortalDCEnquiryProofURL() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("RETAIL_GENERAL_PORTAL_DC_ENQUIRY_PROOF"));
//
//    }
//    public void loadOLACCPublicApplication() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("OLA_CC_PUBLIC_APPLICATION"));
//
//    }
    public void loadTest2Url() {
        navigationHelper.navigateTo(propertyConfiguration.getProperty("CCARD_TEST2"));
    }
//
//    public void loadDirectSalesUKRACOutstandingApplicationsURL() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("SALES_UKRAC_PORTAL_BB_OUTSTANDING_APPLICATIONS_URL"));
//
//    }
//
//    public void loadDirectSalesUKRACDecisionLogSearchURL() {
//        navigationHelper.navigateTo(propertyConfiguration.getProperty("SALES_UKRAC_PORTAL_BB_DECISION_LOG_SEARCH_URL"));
//
//    }
//
//    public void loadDirectSalesPAMANTBURL(String accNumber) {
//        String templateURL = propertyConfiguration.getProperty("SALES_PAMANB_PORTAL_BB_PARTICIPANT_MGMT_URL");
//        String actualURL = commonFunctions.replaceString(templateURL, "TEMPLATEACCOUNTNO", accNumber);
//        System.out.println(actualURL);
//        navigationHelper.navigateTo(actualURL);
//    }
//
//    public void loadDirectSalesPortalBusinessCARDUpgrade(String accNumber) {
//        String templateURL = propertyConfiguration.getProperty("SALES_CDUPGB_ENS_PORTAL_BB_CARD_UPGRADE_URL");
//        String actualURL = commonFunctions.replaceString(templateURL, "TEMPLATEACCOUNTNO", accNumber);
//        System.out.println(actualURL);
//        navigationHelper.navigateTo(actualURL);
//    }
//
//  public void loadDirectSalesPortalBusinessOverdraftUpgrade(String accNumber) {
//        String templateURL = propertyConfiguration.getProperty("SALES_OVUPGB_ENS_PORTAL_BB_OVERDRAFT_ADMINISTRATION_URL");
//        String actualURL = commonFunctions.replaceString(templateURL, "TEMPLATEACCOUNTNO", accNumber);
//        System.out.println(actualURL);
//        navigationHelper.navigateTo(actualURL);
//    }


}
