package com.test.helper.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public Properties prop = new Properties();
    InputStream inputStream ;

    public PropertyReader(String path) {
        loadProperties(path);
    }

    public void loadProperties(String propFilePath) {

        try {
            if (propFilePath!= null) {
                System.out.println(propFilePath);
                inputStream = new FileInputStream(propFilePath);
                System.out.println(propFilePath);
                prop.load(inputStream);
                //log.log_library_track("     in LoadProperties- file loaded");
            }
            else {
                //log.log_library_error("     in LoadProperties- file not loaded");
            }
        } catch (IOException e) {
            //log.log_library_error("     in LoadProperties- Exception");
            e.printStackTrace();
        }
        //log.log_library_track("in PropertyReader-LoadProperties- Finish");

    }

    public String readProperty(String key) {
        return prop.getProperty(key);
    }


    public String getBrowser() {

        return readProperty("LOCAL_BROWSER");
    }






}
