package tests;

import base.BaseTest;
import config.Configuration;
import exceptions.ElementNotFoundException;
import exceptions.FrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxTest extends BaseTest {

    @Test
    public void testTextBoxFormSubmission()
    {
        try {
            logger.info("Starting text box form submission test");

            // Navigate to text-box page
            navigateTo(Configuration.getBaseUrl() + "/text-box");

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
            clickElement(By.id("submit"));

            // Validate results
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
        } catch (Exception e) {
            throw new ElementNotFoundException("Element not found for sending keys: " + locator, e);
        }
    }

    private void clickElement(By locator)
    {
        try {
            logger.trace("Clicking element: {}", locator);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            throw new ElementNotFoundException("Clickable element not found: " + locator, e);
        }
    }

    private String waitAndGetText(By locator)
    {
        try {
            logger.trace("Waiting for element and getting text: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        } catch (Exception e) {
            throw new ElementNotFoundException("Element not found for getting text: " + locator, e);
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
}
