package com.test.ui.sppportal.pages.loginblock;

import com.test.helper.BasePage;

public class SPPLoginURL extends BasePage {


    public void launchURL(String accType) {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        switch (accType) {
            case "Savings Account":
                driver.get("http://back.santanderuk.pre.corp/bk_CaseManagement_V3_ENS/BtoChannelDriver.ssobto?dse_operationName=FastTrackSale_csmg&idFastTrack.codAgrupacion.COD_ALFANUM=9931&idFastTrack.codProceso=0036&origenHIQR=N");
                break;
            case "Retail Current Account":
                driver.get("http://back.santanderuk.pre.corp/bk_CaseManagement_V3_ENS/BtoChannelDriver.ssobto?dse_operationName=FastTrackSale_csmg&idFastTrack.codAgrupacion.COD_ALFANUM=9930&idFastTrack.codProceso=0004&origenHIQR=N");
                break;
            case "Savings Bond Account":
                driver.get("http://back.santanderuk.pre.corp/bk_CaseManagement_V3_ENS/BtoChannelDriver.ssobto?dse_operationName=FastTrackSale_csmg&dse_parentContextName=&dse_processorState=initial&dse_nextEventName=start&idFastTrack.codAgrupacion.COD_ALFANUM=9932&idFastTrack.codProceso=0036&origenHIQR=N");
                break;
            case "Savings ISA Account":
                driver.get("http://back.santanderuk.pre.corp/bk_CaseManagement_V3_ENS/BtoChannelDriver.ssobto?dse_operationName=FastTrackSale_csmg&dse_parentContextName=&dse_processorState=initial&dse_nextEventName=start&idFastTrack.codAgrupacion.COD_ALFANUM=9933&idFastTrack.codProceso=0036&origenHIQR=N");
                break;
            case "Business Current Account":
                driver.get("http://back.santanderuk.pre.corp/bk_CaseManagement_V3_ENS/BtoChannelDriver.ssobto?dse_operationName=FastTrackSale_csmg&idFastTrack.codAgrupacion.COD_ALFANUM=BB03&idFastTrack.codProceso=0025&origenHIQR=N");
                break;
        }
    }

}
