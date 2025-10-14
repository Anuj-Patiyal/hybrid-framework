package drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager()
    {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver()
    {
        if (driverThreadLocal.get() == null) {
            logger.warn("WebDriver is null. Please initialize driver first using DriverFactory.");
            throw new IllegalStateException("WebDriver is not initialized. Call DriverFactory.createDriver() first.");
        }
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver)
    {
        logger.debug("Setting WebDriver instance for current thread");
        driverThreadLocal.set(driver);
    }

    public static void quitDriver()
    {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            logger.info("Quitting WebDriver instance");
            driver.quit();
            driverThreadLocal.remove();
            logger.info("WebDriver instance quit successfully and removed from ThreadLocal");
        }
    }

    public static boolean isDriverActive()
    {
        return driverThreadLocal.get() != null;
    }
}
