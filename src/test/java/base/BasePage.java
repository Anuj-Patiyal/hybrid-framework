package base;

import config.Configuration;
import exceptions.ElementNotFoundException;
import exceptions.FrameworkException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.WaitUtils;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitUtils waitUtils;
    protected JavascriptExecutor jsExecutor;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.getIntProperty("explicit.wait", 10)));
        this.waitUtils = new WaitUtils(driver);
        this.jsExecutor = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
        logger.debug("Initialized BasePage for: {}", this.getClass().getSimpleName());
    }

    protected void waitForElementToBeVisible(WebElement element)
    {
        try {
            logger.trace("Waiting for element to be visible: {}", element);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new ElementNotFoundException("Element not visible: " + element, e);
        }
    }

    protected void waitForElementToBeClickable(WebElement element)
    {
        try {
            logger.trace("Waiting for element to be clickable: {}", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new ElementNotFoundException("Element not clickable: " + element, e);
        }
    }

    protected void clickElement(WebElement element)
    {
        try {
            logger.trace("Clicking element: {}", element);
            waitForElementToBeClickable(element);
            element.click();
        } catch (Exception e) {
            logger.warn("Regular click failed, trying JavaScript click: {}", e.getMessage());
            clickUsingJavaScript(element);
        }
    }

    protected void clickUsingJavaScript(WebElement element)
    {
        try {
            logger.trace("Clicking element using JavaScript: {}", element);
            waitForElementToBeVisible(element);
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new FrameworkException("Failed to click element using JavaScript: " + element, e);
        }
    }

    protected void sendKeysToElement(WebElement element, String text)
    {
        try {
            logger.trace("Sending keys to element: {} with text: {}", element, text);
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new FrameworkException("Failed to send keys to element: " + element, e);
        }
    }

    protected String getElementText(WebElement element)
    {
        try {
            logger.trace("Getting text from element: {}", element);
            waitForElementToBeVisible(element);
            return element.getText();
        } catch (Exception e) {
            throw new FrameworkException("Failed to get text from element: " + element, e);
        }
    }

    protected void scrollToElement(WebElement element)
    {
        try {
            logger.trace("Scrolling to element: {}", element);
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            throw new FrameworkException("Failed to scroll to element: " + element, e);
        }
    }

    public String getPageTitle()
    {
        return driver.getTitle();
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url)
    {
        try {
            logger.info("Navigating to URL: {}", url);
            driver.get(url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw new FrameworkException("Navigation failed to: " + url, e);
        }
    }

    // New methods for v0.7.0 - Smart Waiting Strategy
    protected void smartWaitForElement(By locator)
    {
        waitUtils.waitForElementVisible(locator);
    }

    protected void waitForPageToLoad()
    {
        waitUtils.waitForPageLoad();
    }

    protected void waitForAjax()
    {
        waitUtils.waitForAjaxToComplete();
    }

    protected WebElement waitForElementClickable(By locator)
    {
        return waitUtils.waitForElementClickable(locator);
    }

    protected boolean waitForElementInvisible(By locator)
    {
        return waitUtils.waitForElementInvisible(locator);
    }

    protected boolean waitForTextPresent(By locator, String text)
    {
        return waitUtils.waitForTextPresent(locator, text);
    }
}
