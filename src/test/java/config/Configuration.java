package config;

import exceptions.FrameworkException;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private static Properties properties;

    static
    {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
            logger.info("Configuration loaded successfully from config.properties");
        } catch (Exception e) {
            logger.error("Failed to load configuration file: {}", e.getMessage());
            throw new FrameworkException("Configuration file loading failed", e);
        }
    }

    public static String getBrowser()
    {
        try {
            String browser = properties.getProperty("browser", "chrome");
            logger.debug("Browser configured: {}", browser);
            return browser;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get browser configuration", e);
        }
    }

    public static boolean isHeadless()
    {
        try {
            boolean headless = Boolean.parseBoolean(properties.getProperty("headless", "true"));
            logger.debug("Headless mode: {}", headless);
            return headless;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get headless configuration", e);
        }
    }

    public static String getBaseUrl()
    {
        try {
            String url = properties.getProperty("base.url", "https://demoqa.com");
            logger.debug("Base URL: {}", url);
            return url;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get base URL configuration", e);
        }
    }

    public static int getIntProperty(String key, int defaultValue)
    {
        try {
            String value = properties.getProperty(key);
            if (value != null) {
                return Integer.parseInt(value);
            }
            return defaultValue;
        } catch (NumberFormatException e) {
            logger.warn("Invalid integer value for property '{}'. Using default: {}", key, defaultValue);
            return defaultValue;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get integer property: " + key, e);
        }
    }

    public static long getLongProperty(String key, long defaultValue)
    {
        try {
            String value = properties.getProperty(key);
            if (value != null) {
                return Long.parseLong(value);
            }
            return defaultValue;
        } catch (NumberFormatException e) {
            logger.warn("Invalid long value for property '{}'. Using default: {}", key, defaultValue);
            return defaultValue;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get long property: " + key, e);
        }
    }

    public static String getProperty(String key, String defaultValue)
    {
        try {
            return properties.getProperty(key, defaultValue);
        } catch (Exception e) {
            throw new FrameworkException("Failed to get property: " + key, e);
        }
    }

    // New methods for v0.7.0 - Wait Configuration
    public static int getWaitPollingInterval()
    {
        try {
            int interval = getIntProperty("wait.polling.interval", 500);
            logger.debug("Wait polling interval: {}ms", interval);
            return interval;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get wait polling interval configuration", e);
        }
    }

    public static int getFluentWaitTimeout()
    {
        try {
            int timeout = getIntProperty("wait.fluent.timeout", 30);
            logger.debug("Fluent wait timeout: {} seconds", timeout);
            return timeout;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get fluent wait timeout configuration", e);
        }
    }

    public static int getAjaxWaitTimeout()
    {
        try {
            int timeout = getIntProperty("wait.ajax.timeout", 10);
            logger.debug("AJAX wait timeout: {} seconds", timeout);
            return timeout;
        } catch (Exception e) {
            throw new FrameworkException("Failed to get AJAX wait timeout configuration", e);
        }
    }
}
