package com.company.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties PROPS = loadSerenityConf();

    private ConfigReader() {}

    public static String getBaseUrl() {
        return getProperty("base.url", "http://opencart.abstracta.us/");
    }

    public static String getApiBaseUrl() {
        return getProperty("api.base.url", "http://opencart.abstracta.us/index.php?route=api/");
    }

    public static String getProperty(String key) {
        String val = System.getProperty(key);
        if (val != null && !val.isEmpty()) return val;
        return PROPS.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    private static Properties loadSerenityConf() {
        Properties props = new Properties();
        props.setProperty("base.url", "http://opencart.abstracta.us/");
        props.setProperty("api.base.url", "http://opencart.abstracta.us/index.php?route=api/");
        return props;
    }
}
