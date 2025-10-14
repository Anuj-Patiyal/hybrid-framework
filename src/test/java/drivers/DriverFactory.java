package drivers;

import config.Configuration;
import exceptions.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    private DriverFactory()
    {
        // Private constructor to prevent instantiation
    }

    public static WebDriver createDriver()
    {
        String browser = Configuration.getBrowser().toLowerCase();
        boolean headless = Configuration.isHeadless();

        logger.info("Creating WebDriver instance for browser: {} (headless: {})", browser, headless);

        WebDriver driver;

        try {
            switch (browser) {
            case "chrome":
                driver = createChromeDriver(headless);
                break;
            case "firefox":
                driver = createFirefoxDriver(headless);
                break;
            case "edge":
                driver = createEdgeDriver(headless);
                break;
            case "safari":
                driver = createSafariDriver(headless);
                break;
            default:
                logger.warn("Unsupported browser: {}. Defaulting to Chrome.", browser);
                driver = createChromeDriver(headless);
            }

            // Set common driver configurations
            setupDriverCommonConfig(driver);

            // Set the driver in DriverManager
            DriverManager.setDriver(driver);

            logger.info("WebDriver created successfully for browser: {}", browser);
            return driver;

        } catch (Exception e) {
            logger.error("Failed to create WebDriver for browser: {}", browser, e);
            throw new FrameworkException("WebDriver creation failed for browser: " + browser, e);
        }
    }

    private static WebDriver createChromeDriver(boolean headless)
    {
        logger.debug("Setting up ChromeDriver using WebDriverManager");
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            logger.debug("Chrome running in headless mode");
        }

        // Common Chrome options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--window-size=1920,1080");

        logger.debug("Chrome options configured: {}", options);
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless)
    {
        logger.debug("Setting up FirefoxDriver using WebDriverManager");
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();

        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
            logger.debug("Firefox running in headless mode");
        }

        // Common Firefox options
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        logger.debug("Firefox options configured: {}", options);
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless)
    {
        logger.debug("Setting up EdgeDriver using WebDriverManager");
        WebDriverManager.getInstance(DriverManagerType.EDGE).setup();

        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            logger.debug("Edge running in headless mode");
        }

        // Common Edge options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--window-size=1920,1080");

        logger.debug("Edge options configured: {}", options);
        return new EdgeDriver(options);
    }

    private static WebDriver createSafariDriver(boolean headless)
    {
        if (headless) {
            logger.warn("Safari does not support headless mode. Running in normal mode.");
        }

        logger.debug("Setting up SafariDriver using WebDriverManager");
        WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();

        SafariOptions options = new SafariOptions();
        // Safari has limited options compared to other browsers

        logger.debug("Safari options configured: {}", options);
        return new SafariDriver(options);
    }

    private static void setupDriverCommonConfig(WebDriver driver)
    {
        // Set window size
        driver.manage().window().maximize();

        // Set timeouts
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(
            Configuration.getIntProperty("implicit.wait", 10)));

        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(
            Configuration.getIntProperty("page.load.timeout", 30)));

        logger.debug("Common driver configurations applied");
    }

    public static void quitDriver()
    {
        DriverManager.quitDriver();
    }
}
