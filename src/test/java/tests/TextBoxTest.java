package tests;

import config.Configuration;
import exceptions.ElementNotFoundException;
import exceptions.FrameworkException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TextBoxTest {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(TextBoxTest.class);

    @BeforeClass
    public void setUp()
    {
        try {
            logger.info("Setting up test environment");

            ChromeOptions options = new ChromeOptions();

            if (Configuration.isHeadless()) {
                options.addArguments("--headless=new");
                logger.debug("Chrome running in headless mode");
            }

            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920, 1080));

            String url = Configuration.getBaseUrl() + "/text-box";
            logger.info("Navigating to URL: {}", url);
            driver.get(url);

            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            logger.info("Test setup completed successfully");

        } catch (Exception e) {
            logger.error("Test setup failed: {}", e.getMessage());
            throw new FrameworkException("Test setup failed", e);
        }
    }

    @Test
    public void testTextBoxFormSubmission()
    {
        try {
            logger.info("Starting text box form submission test");

            String fullName = "John Doe";
            String email = "john.doe@example.com";
            String currentAddress = "123 Main St";
            String permanentAddress = "456 Secondary St";

            logger.debug("Filling form with data - Name: {}, Email: {}", fullName, email);

            waitAndSendKeys(By.id("userName"), fullName);
            waitAndSendKeys(By.id("userEmail"), email);
            waitAndSendKeys(By.id("currentAddress"), currentAddress);
            waitAndSendKeys(By.id("permanentAddress"), permanentAddress);

            logger.debug("Clicking submit button");
            clickElement(By.id("submit"));

            validateResults(fullName, email, currentAddress, permanentAddress);

            logger.info("Text box form submission test completed successfully");

        } catch (Exception e) {
            logger.error("Test execution failed: {}", e.getMessage());
            throw new FrameworkException("Text box test failed", e);
        }
    }

    private void waitAndSendKeys(By locator, String text)
    {
        try {
            logger.trace("Waiting for element and sending keys: {}", locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        } catch (TimeoutException e) {
            throw new ElementNotFoundException("Element not found for sending keys: " + locator, e);
        } catch (Exception e) {
            throw new FrameworkException("Failed to send keys to element: " + locator, e);
        }
    }

    private void clickElement(By locator)
    {
        try {
            logger.trace("Clicking element: {}", locator);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException e) {
            throw new ElementNotFoundException("Clickable element not found: " + locator, e);
        } catch (Exception e) {
            throw new FrameworkException("Failed to click element: " + locator, e);
        }
    }

    private String waitAndGetText(By locator)
    {
        try {
            logger.trace("Waiting for element and getting text: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        } catch (TimeoutException e) {
            throw new ElementNotFoundException("Element not found for getting text: " + locator, e);
        } catch (Exception e) {
            throw new FrameworkException("Failed to get text from element: " + locator, e);
        }
    }

    private void validateResults(String fullName, String email, String currentAddress, String permanentAddress)
    {
        try {
            logger.debug("Validating form submission results");
            Assert.assertEquals(waitAndGetText(By.id("name")), "Name:" + fullName);
            Assert.assertEquals(waitAndGetText(By.id("email")), "Email:" + email);
            Assert.assertEquals(waitAndGetText(By.xpath("//p[@id='currentAddress']")), "Current Address :" + currentAddress);
            Assert.assertEquals(waitAndGetText(By.xpath("//p[@id='permanentAddress']")), "Permananet Address :" + permanentAddress);
        } catch (AssertionError e) {
            logger.error("Validation failed: {}", e.getMessage());
            throw new FrameworkException("Test validation failed", e);
        }
    }

    @AfterClass
    public void tearDown()
    {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver closed successfully");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: {}", e.getMessage());
        }
    }
}
