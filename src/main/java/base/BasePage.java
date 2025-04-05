package base;

import exceptions.FrameworkException;
import utils.ConfigManager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;

/**
 * Base page object with common UI actions and logging
 */
public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getIntProperty("explicitWait")));
    }

    protected void click(By locator) {
        try {
            logger.info("Clicking on element: {}", locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            throw new FrameworkException("Failed to click element: " + locator, e);
        }
    }

    protected void type(By locator, String text) {
        try {
            logger.info("Typing '{}' in field: {}", text, locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new FrameworkException("Failed to type in element: " + locator, e);
        }
    }

    protected boolean isVisible(By locator) {
        logger.debug("Checking visibility of element: {}", locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (Exception e) {
            return false;
        }
    }
    protected List<WebElement> waitForElements(By locator) {
        try {
            logger.debug("Waiting for elements: {}", locator);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            logger.error("Elements not found within timeout: {}", locator);
            throw new FrameworkException("Timeout waiting for elements: " + locator, e);
        }
    }

    protected WebElement waitForElement(By locator) {
        try {
            logger.debug("Waiting for element: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not visible within timeout: {}", locator);
            throw new FrameworkException("Timeout waiting for element: " + locator, e);
        }
    }
    protected WebDriverWait getWait() {
        return new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigManager.getIntProperty("explicitWait")));
    }
}