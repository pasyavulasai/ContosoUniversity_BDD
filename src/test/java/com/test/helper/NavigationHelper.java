package com.test.helper;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;


public class NavigationHelper {

    @Inject
    WebDriver driver;

    @Inject
    public PropertiesHelper prop;

    public void navigateTo(String url){

        driver.navigate().to(url);

    }
}
