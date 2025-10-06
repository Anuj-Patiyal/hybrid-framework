package tests;

import config.Configuration;
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
        logger.info("Setting up test environment");

        ChromeOptions options = new ChromeOptions();

        // Use configuration
        if (Configuration.isHeadless()) {
            options.addArguments("--headless=new");
            logger.debug("Chrome running in headless mode");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // START SESSION
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));

        String url = Configuration.getBaseUrl() + "/text-box";
        logger.info("Navigating to URL: {}", url);
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Test setup completed successfully");
    }

    @Test
    public void testTextBoxFormSubmission()
    {
        logger.info("Starting text box form submission test");

        // Input data
        String fullName = "John Doe";
        String email = "john.doe@example.com";
        String currentAddress = "123 Main St";
        String permanentAddress = "456 Secondary St";

        logger.debug("Filling form with data - Name: {}, Email: {}", fullName, email);

        // Fill form fields with wait
        waitAndSendKeys(By.id("userName"), fullName);
        waitAndSendKeys(By.id("userEmail"), email);
        waitAndSendKeys(By.id("currentAddress"), currentAddress);
        waitAndSendKeys(By.id("permanentAddress"), permanentAddress);

        // Click the submit button
        logger.debug("Clicking submit button");
        By submitButtonLocator = By.id("submit");
        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(submitButtonLocator));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        // Validate outputs
        logger.debug("Validating form submission results");
        Assert.assertEquals(waitAndGetText(By.id("name")), "Name:" + fullName);
        Assert.assertEquals(waitAndGetText(By.id("email")), "Email:" + email);
        Assert.assertEquals(waitAndGetText(By.xpath("//p[@id='currentAddress']")), "Current Address :" + currentAddress);
        Assert.assertEquals(waitAndGetText(By.xpath("//p[@id='permanentAddress']")),
            "Permananet Address :" + permanentAddress);

        logger.info("Text box form submission test completed successfully");
    }

    private void waitAndSendKeys(By locator, String text)
    {
        logger.trace("Waiting for element and sending keys: {}", locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private String waitAndGetText(By locator)
    {
        logger.trace("Waiting for element and getting text: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    @AfterClass
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed successfully");
        }
    }
}
