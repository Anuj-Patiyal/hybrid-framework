package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.FrameworkException;

import java.io.InputStream;
import java.util.Properties;

/**
 * Handles configuration properties with proper error handling
 */
public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("config.properties file not found!");
                throw new FrameworkException("config.properties file not found in classpath");
            }
            props.load(input);
            logger.info("Loaded configuration properties");
        } catch (Exception e) {
            logger.fatal("Error loading configuration: {}", e.getMessage());
            throw new FrameworkException("Configuration initialization failed", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            logger.error("Property {} not found in config file", key);
            throw new FrameworkException("Property " + key + " not found in configuration");
        }
        return value;
    }

    public static int getIntProperty(String key) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException e) {
            logger.error("Invalid integer value for property: {}", key);
            throw new FrameworkException("Invalid integer value for property: " + key, e);
        }
    }

    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            logger.error("Invalid boolean value for property: {}", key);
            throw new FrameworkException("Invalid boolean value for property: " + key);
        }
        return Boolean.parseBoolean(value);
    }

    private static String getDefaultValue(String key) {
        switch(key) {
            case "env": return "dev";
            case "explicitWait": return "15";
            case "maxRetries": return "1";
            default: 
                logger.error("No default for missing property: {}", key);
                throw new FrameworkException("Missing mandatory property: " + key);
        }
    }
}
