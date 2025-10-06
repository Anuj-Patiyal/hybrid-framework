package config;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {
    private static Properties properties;

    static
    {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBrowser()
    {
        return properties.getProperty("browser", "chrome");
    }

    public static boolean isHeadless()
    {
        return Boolean.parseBoolean(properties.getProperty("headless", "true"));
    }

    public static String getBaseUrl()
    {
        return properties.getProperty("base.url", "https://demoqa.com");
    }
}
