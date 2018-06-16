package com.test.helper;

import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesHelper {


    public static HashMap<String, String> getSeleniumProperties()
            throws StopTestException {
        HashMap<String, String> props = new HashMap<String, String>();

        props.putAll(PropertiesHelper.extractPropertiesFromProperties(
                Configuration.SELENIUM_VARIABLES_PREFIX,
                Configuration.getConfiguration().getProperties()));

        props.putAll(PropertiesHelper.extractPropertiesFromEnvironment(
                Configuration.SELENIUM_ENVIRONMENT_VARIABLES_PREFIX));


        return props;
    }

    public static HashMap<String, String> extractPropertiesFromEnvironment(
            String prefix) throws StopTestException {

        HashMap<String, String> props = new HashMap<String, String>();

        for (Map.Entry<String, String> object : System.getenv().entrySet()) {
            String keyName = object.getKey();

            props.putAll(collect(prefix, keyName, object.getValue()));
        }

        return props;
    }

    public static HashMap<String, String> extractPropertiesFromProperties(
            String prefix, Properties properties) throws StopTestException {
        HashMap<String, String> props = new HashMap<String, String>();

        for (Map.Entry<Object, Object> object : properties
                .entrySet()) {
            String keyName = object.getKey().toString();

            props.putAll(
                    collect(prefix, keyName, object.getValue().toString()));
        }

        return props;
    }

    public static HashMap<String, String> extractPropertiesFromSystemProperties(
            String prefix) throws StopTestException {
        return extractPropertiesFromProperties(prefix, System.getProperties());
    }

    private static HashMap<String, String> collect(String prefix,
            String keyName, String value) throws StopTestException {

        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();

        if (!keyName.startsWith(prefix))
            return stringStringHashMap;

        String sanitisedKeyName = sanitiseKeyName(keyName, prefix);

        ArrayList<String> excludedSeleniumProperties =
                Configuration.getConfiguration().getExcludedSeleniumProperties();

        if(!excludedSeleniumProperties.contains(sanitisedKeyName))
            stringStringHashMap.put(sanitisedKeyName, value);

        return stringStringHashMap;
    }

    private static String sanitiseKeyName(String keyName, String prefix) {
        String desiredKey = keyName.replace(prefix, "");
        return StringHelper.toCamelCase(desiredKey);
    }

}
