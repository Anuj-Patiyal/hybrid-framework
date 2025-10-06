package config;

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
            logger.info("Configuration loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to load configuration file: {}", e.getMessage());
        }
    }

    public static String getBrowser()
    {
        String browser = properties.getProperty("browser", "chrome");
        logger.debug("Browser configured: {}", browser);
        return browser;
    }

    public static boolean isHeadless()
    {
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless", "true"));
        logger.debug("Headless mode: {}", headless);
        return headless;
    }

    public static String getBaseUrl()
    {
        String url = properties.getProperty("base.url", "https://demoqa.com");
        logger.debug("Base URL: {}", url);
        return url;
    }
}
