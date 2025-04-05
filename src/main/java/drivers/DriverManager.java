package drivers;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exceptions.FrameworkException;

/**
 * Thread-safe WebDriver management with modern thread identification
 */
public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<String> threadIdentifier = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String errorMsg = String.format("Driver not initialized for [%s]", getThreadInfo());
            logger.error(errorMsg);
            throw new FrameworkException(errorMsg);
        }
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        threadIdentifier.set(generateThreadIdentifier());
        logger.info("Initializing driver for {}", getThreadInfo());
        driver.set(driverInstance);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                logger.info("Closing driver for {}", getThreadInfo());
                driver.get().quit();
            } catch (Exception e) {
                logger.error("Driver cleanup failed: {}", e.getMessage());
            } finally {
                driver.remove();
                threadIdentifier.remove();
            }
        }
    }

    private static String generateThreadIdentifier() {
        Thread t = Thread.currentThread();
        return String.format("%s-ID%d", t.getName(), t.threadId());
    }

    private static String getThreadInfo() {
        return threadIdentifier.get() != null ? 
               threadIdentifier.get() : 
               "Thread-" + Thread.currentThread().threadId();
    }
}