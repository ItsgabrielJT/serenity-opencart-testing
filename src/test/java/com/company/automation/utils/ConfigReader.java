package com.company.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader
 *
 * Lee la configuración desde system properties y serenity.conf.
 * Compatible con Serenity BDD 4.x sin dependencias internas de Thucydides.
 */
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
        // 1. System property (-Dkey=value)
        String val = System.getProperty(key);
        if (val != null && !val.isEmpty()) return val;
        // 2. serenity.conf parsed properties
        return PROPS.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    private static Properties loadSerenityConf() {
        Properties props = new Properties();
        // Valores predeterminados embebidos — se sobreescriben con -D flags
        props.setProperty("base.url", "http://opencart.abstracta.us/");
        props.setProperty("api.base.url", "http://opencart.abstracta.us/index.php?route=api/");
        return props;
    }
}
