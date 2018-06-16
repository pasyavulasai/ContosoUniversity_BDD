package com.test.configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.test.exceptions.StopTestException;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class WebDriverProvider implements Provider<WebDriver> {

    @Inject
    @Named("browser")
    private String browser;

    @Inject
    public Configuration config;


    public WebDriver get(){
        try {
            if (config.getGridRun().equalsIgnoreCase("true")) {
              return PageFactory.getRemoteWebDriver(browser);
           }else {
                if (browser.equalsIgnoreCase("chrome")) {
                    return PageFactory.getChromeWebDriver();
                }

                if (browser.equalsIgnoreCase("internet explorer")) {
                    return PageFactory.getIEWebDriver();
                }

                if (browser.equalsIgnoreCase("safari")) {
                    return PageFactory.getSafariWebDriver();
                }

                if(browser.equalsIgnoreCase("firefox")){
                    return PageFactory.getFireFoxDriver();
                }

                if(browser.equalsIgnoreCase("MicrosoftEdge")){
                    return PageFactory.getEdgeDriver();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (StopTestException e) {
            e.printStackTrace();
        }

        return null;
    }
}
