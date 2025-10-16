package waits;

import config.Configuration;
import exceptions.FrameworkException;
import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private static final Logger logger = LogManager.getLogger(WaitUtils.class);

    private final WebDriver driver;
    private final FluentWait<WebDriver> fluentWait;

    public WaitUtils(WebDriver driver)
    {
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                              .withTimeout(Duration.ofSeconds(Configuration.getIntProperty("explicit.wait", 10)))
                              .pollingEvery(Duration.ofMillis(500))
                              .ignoring(NoSuchElementException.class)
                              .ignoring(StaleElementReferenceException.class);
    }

    // Smart wait for element with custom timeout
    public WebElement waitForElement(By locator, int timeoutSeconds)
    {
        logger.debug("Waiting for element: {} with timeout: {} seconds", locator, timeoutSeconds);
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException("Element not found within " + timeoutSeconds + " seconds: " + locator, e);
        }
    }

    // Smart wait for element visibility
    public WebElement waitForElementVisible(By locator)
    {
        logger.debug("Waiting for element to be visible: {}", locator);
        try {
            return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException("Element not visible: " + locator, e);
        }
    }

    // Smart wait for element to be clickable
    public WebElement waitForElementClickable(By locator)
    {
        logger.debug("Waiting for element to be clickable: {}", locator);
        try {
            return fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException("Element not clickable: " + locator, e);
        }
    }

    // Smart wait for element to disappear
    public boolean waitForElementInvisible(By locator)
    {
        logger.debug("Waiting for element to be invisible: {}", locator);
        try {
            return fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException("Element still visible: " + locator, e);
        }
    }

    // Smart wait for text to be present in element
    public boolean waitForTextPresent(By locator, String text)
    {
        logger.debug("Waiting for text '{}' in element: {}", text, locator);
        try {
            return fluentWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            throw new FrameworkException("Text not found in element: " + locator, e);
        }
    }

    // Smart wait for page to load completely
    public void waitForPageLoad()
    {
        logger.debug("Waiting for page to load completely");
        try {
            ExpectedCondition<Boolean> pageLoadCondition = driver ->
            {
                assert driver != null;
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            };
            fluentWait.until(pageLoadCondition);
        } catch (TimeoutException e) {
            throw new FrameworkException("Page did not load completely within timeout", e);
        }
    }

    // Smart wait for AJAX calls to complete
    public void waitForAjaxToComplete()
    {
        logger.debug("Waiting for AJAX calls to complete");
        try {
            ExpectedCondition<Boolean> ajaxCondition = driver ->
            {
                assert driver != null;
                return (Boolean)((JavascriptExecutor)driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0)");
            };
            fluentWait.until(ajaxCondition);
        } catch (TimeoutException e) {
            logger.warn("AJAX calls did not complete within timeout, continuing...");
        }
    }

    // Smart wait for specific condition with custom message
    public <T> T waitForCondition(ExpectedCondition<T> condition, String conditionDescription)
    {
        logger.debug("Waiting for condition: {}", conditionDescription);
        try {
            return fluentWait.until(condition);
        } catch (TimeoutException e) {
            throw new FrameworkException("Condition not met: " + conditionDescription, e);
        }
    }

    // Smart wait for multiple elements
    public List<WebElement> waitForAllElements(By locator)
    {
        logger.debug("Waiting for all elements: {}", locator);
        try {
            return fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException("Elements not found: " + locator, e);
        }
    }

    // Smart wait with custom polling interval
    public WebElement waitForElementWithCustomPolling(By locator, int timeoutSeconds, int pollingMillis)
    {
        logger.debug("Waiting for element: {} with custom polling: {}ms", locator, pollingMillis);
        try {
            return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException("Element not found with custom polling: " + locator, e);
        }
    }
}
