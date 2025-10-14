package base;

import config.Configuration;
import drivers.DriverFactory;
import drivers.DriverManager;
import exceptions.FrameworkException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void baseSetUp()
    {
        try {
            logger.info("=== Starting test class: {} ===", this.getClass().getSimpleName());

            // Create driver instance
            driver = DriverFactory.createDriver();

            // Initialize wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.getIntProperty("explicit.wait", 10)));

            logger.info("Base test setup completed successfully");

        } catch (Exception e) {
            logger.error("Base test setup failed", e);
            throw new FrameworkException("Test setup failed", e);
        }
    }

    @BeforeMethod
    public void baseBeforeMethod()
    {
        try {
            logger.info("--- Starting test method ---");

            // Navigate to base URL before each test method
            String baseUrl = Configuration.getBaseUrl();
            if (baseUrl != null && !baseUrl.isEmpty()) {
                driver.get(baseUrl);
                logger.info("Navigated to base URL: {}", baseUrl);
            }

        } catch (Exception e) {
            logger.error("Before method setup failed", e);
            throw new FrameworkException("Before method setup failed", e);
        }
    }

    @AfterMethod
    public void baseAfterMethod()
    {
        try {
            logger.info("--- Completed test method ---");

            // Optional: Take screenshot on test failure
            // This can be enhanced with TestNG listeners later

        } catch (Exception e) {
            logger.error("After method cleanup failed", e);
        }
    }

    @AfterClass
    public void baseTearDown()
    {
        try {
            logger.info("=== Cleaning up test class: {} ===", this.getClass().getSimpleName());

            if (DriverManager.isDriverActive()) {
                DriverFactory.quitDriver();
                logger.info("WebDriver quit successfully");
            }

            logger.info("Base test cleanup completed");

        } catch (Exception e) {
            logger.error("Base test cleanup failed", e);
        }
    }

    // Common utility methods available to all test classes
    protected WebDriver getDriver()
    {
        return DriverManager.getDriver();
    }

    protected WebDriverWait getWait()
    {
        return wait;
    }

    protected void navigateTo(String url)
    {
        try {
            logger.info("Navigating to URL: {}", url);
            driver.get(url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw new FrameworkException("Navigation failed to: " + url, e);
        }
    }

    protected String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle()
    {
        return driver.getTitle();
    }
}
