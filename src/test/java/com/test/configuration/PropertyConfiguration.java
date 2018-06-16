package com.test.configuration;

import com.test.exceptions.StopTestException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main propertyConfiguration for test automation framework
 */
public class PropertyConfiguration {

    public static final String KEYBASE = "autotest";

    public static final String KEY_Environ = "environ";



    private volatile static PropertyConfiguration propertyConfiguration; // volatile is needed
    // This is used to ensure the settings are loaded
    public static final String KEY_LOADED = ".loaded";


    private Properties myprops = null;

    // This is the full key allowing for multiple settings
    public static final String KEY_SETTINGS = ".settings";
    private String settings = null;


    public PropertyConfiguration() throws StopTestException {
        settings = System.getProperty(KEY_SETTINGS, null);
        myprops = new Properties(System.getProperties());
        String userhome = System.getProperty("user.home", "./");
        String loaded = myprops.getProperty(KEY_LOADED);
        List<String> files = getPropertyFiles();
        if (loaded == null) {
            for (String filename : files) {
                try {
                    filename = filename.replace("{user.home}", userhome);
                    myprops.load(new FileInputStream(filename));
                } catch (FileNotFoundException fnfe) {
                    // Ignore any files that aren't present - same as ant build
                    // infrastructure
                } catch (IOException ioe) {
                    // TODO decide what we require here?
                    System.err.println("ERROR: " + ioe.getMessage()); // NOPMD
                }
            }
            myprops.putAll(System.getProperties());
        }
        if (settings == null) {
            settings = myprops.getProperty(KEY_SETTINGS, null);
        }
    }




    private List<String> getPropertyFiles() throws StopTestException {
        Configuration configuration = new Configuration();
        List<String> files = new ArrayList<String>();
        String defaultFile = "./src/test/resources/TestData/Pre.properties";

        String environment = configuration.getEnviron();
        if(environment.equalsIgnoreCase("pre")){
            defaultFile = "./src/test/resources/TestData/Pre.properties";
        } else if(environment.equalsIgnoreCase("prod")){
            defaultFile = "./src/test/resources/TestData/Prod.properties";
        }

        files.add(defaultFile);
        return files;
    }

    public static synchronized PropertyConfiguration getPropertyConfiguration()
            throws StopTestException {
        if (propertyConfiguration == null)
            propertyConfiguration = new PropertyConfiguration();
        return propertyConfiguration;
    }

    public static synchronized void resetConfiguration() {
        propertyConfiguration = null;
    }

    public String overrideProperty(String keypart, String value) {
        return (String) myprops.setProperty(keypart, value);
    }

    public String getProperty(String keypart) {
        return getProperty(keypart, null);
    }

    protected String getProperty(String keypart, String default_value) {
        String value = null;
        if (myprops != null) {

            if (settings != null && settings.length() > 0) {
                value = myprops.getProperty(settings + "."
                        + keypart);
            } else {
                value = myprops.getProperty(keypart);
            }
            if (value == null || value.length() == 0) {
                value = myprops.getProperty("default." + keypart,
                        default_value);
            }
        }
        return interpolate(value);
    }

    public String interpolate(String value) {
        if (null == value) {
            return value;
        }

        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z.]+}");
        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            value = value.replaceAll("\\$\\{", "").replaceAll("}", "");
            value = getProperty(value);
        }

        return value;
    }
//    public String getURL(){
//        return getProperty("SINGLE_SIGNON_PORTAL_URL");
//    }

    protected String getEnvironmentsProperty(String keypart)
            throws StopTestException {

        return getProperty(keypart);
    }




    public Properties getProperties() {
        Properties combinedProperties = new Properties();
        Properties existingProperties = new Properties();
        existingProperties.putAll(myprops);

        for (Object key : new HashSet<Object>(existingProperties.keySet())) {
            if (key.toString().startsWith(KEY_SETTINGS)) {
                combinedProperties.put(key.toString()
                                .replaceFirst(KEY_SETTINGS,
                                        KEYBASE),
                        existingProperties.get(key));
                existingProperties.remove(key);
            }
        }

        for (Object key : new HashSet<Object>(existingProperties.keySet())) {
            if (key.toString().startsWith("default.")) {
                combinedProperties.put(key.toString()
                                .replaceFirst("default","."),
                        existingProperties.get(key));
                existingProperties.remove(key);
            }
        }

        combinedProperties.putAll(existingProperties);

        return combinedProperties;
    }



    public String getEnviron() {
        return getProperty(KEY_Environ);
    }

//    public void addProperties(String environFileName) {
//        try {
//            myprops.load(new FileReader("src/test/resources/properties/" + environFileName + ".properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
