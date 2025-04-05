package drivers;

import exceptions.FrameworkException;
import utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

/**
 * Creates WebDriver instances with comprehensive logging and error handling
 */
public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createDriver() {
        String browser = ConfigManager.getProperty("browser");
        logger.info("Creating driver for browser: {}", browser);

        WebDriver driver;
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (ConfigManager.getBooleanProperty("headless")) {
                        logger.debug("Setting Chrome headless mode");
                        chromeOptions.addArguments("--headless=new");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    if (ConfigManager.getBooleanProperty("headless")) {
                        logger.debug("Setting Firefox headless mode");
                        ffOptions.addArguments("-headless");
                    }
                    driver = new FirefoxDriver(ffOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new FrameworkException("Unsupported browser: " + browser);
            }
        } catch (Exception e) {
            logger.error("Driver initialization failed: {}", e.getMessage());
            throw new FrameworkException("Driver creation failed", e);
        }

        configureDriver(driver);
        return driver;
    }

    private static void configureDriver(WebDriver driver) {
        int implicitWait = ConfigManager.getIntProperty("implicitWait");
        int pageLoadTimeout = ConfigManager.getIntProperty("pageLoadTimeout");
        
        logger.debug("Setting implicit wait to {} seconds", implicitWait);
        logger.debug("Setting page load timeout to {} seconds", pageLoadTimeout);
        
        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(implicitWait))
              .pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        
        logger.info("Driver configured successfully");
    }
}