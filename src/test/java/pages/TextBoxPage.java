package pages;

import base.BasePage;
import exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TextBoxPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(TextBoxPage.class);

    // WebElements using PageFactory
    @FindBy(id = "userName")
    private WebElement fullNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "name")
    private WebElement outputName;

    @FindBy(id = "email")
    private WebElement outputEmail;

    @FindBy(css = "p#currentAddress")
    private WebElement outputCurrentAddress;

    @FindBy(css = "p#permanentAddress")
    private WebElement outputPermanentAddress;

    public TextBoxPage(WebDriver driver)
    {
        super(driver);
        logger.info("TextBoxPage initialized");
    }

    public void enterFullName(String fullName)
    {
        logger.debug("Entering full name: {}", fullName);
        // Wait for page to load before interacting
        waitForPageToLoad();
        sendKeysToElement(fullNameInput, fullName);
    }

    public void enterEmail(String email)
    {
        logger.debug("Entering email: {}", email);
        sendKeysToElement(emailInput, email);
    }

    public void enterCurrentAddress(String currentAddress)
    {
        logger.debug("Entering current address: {}", currentAddress);
        sendKeysToElement(currentAddressInput, currentAddress);
    }

    public void enterPermanentAddress(String permanentAddress)
    {
        logger.debug("Entering permanent address: {}", permanentAddress);
        sendKeysToElement(permanentAddressInput, permanentAddress);
    }

    public void clickSubmit()
    {
        logger.debug("Clicking submit button using JavaScript to avoid ad interference");
        // Wait for AJAX calls to complete if any
        waitForAjax();
        // Use JavaScript click to avoid ad iframe issues
        clickUsingJavaScript(submitButton);
        // Wait for the form submission to complete
        waitForPageToLoad();
    }

    public void verifyOutputName(String expectedName)
    {
        String actualText = getElementText(outputName);
        logger.debug("Verifying output name - Expected: 'Name:{}', Actual: '{}'", expectedName, actualText);
        Assert.assertEquals(actualText, "Name:" + expectedName, "Output name verification failed");
    }

    public void verifyOutputEmail(String expectedEmail)
    {
        String actualText = getElementText(outputEmail);
        logger.debug("Verifying output email - Expected: 'Email:{}', Actual: '{}'", expectedEmail, actualText);
        Assert.assertEquals(actualText, "Email:" + expectedEmail, "Output email verification failed");
    }

    public void verifyOutputCurrentAddress(String expectedAddress)
    {
        String actualText = getElementText(outputCurrentAddress);
        logger.debug("Verifying output current address - Expected: 'Current Address :{}', Actual: '{}'", expectedAddress, actualText);
        Assert.assertEquals(actualText, "Current Address :" + expectedAddress, "Output current address verification failed");
    }

    public void verifyOutputPermanentAddress(String expectedAddress)
    {
        String actualText = getElementText(outputPermanentAddress);
        logger.debug("Verifying output permanent address - Expected: 'Permananet Address :{}', Actual: '{}'", expectedAddress, actualText);
        Assert.assertEquals(actualText, "Permananet Address :" + expectedAddress, "Output permanent address verification failed");
    }

    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress)
    {
        try {
            logger.info("Filling form with data - Name: {}, Email: {}", fullName, email);
            enterFullName(fullName);
            enterEmail(email);
            enterCurrentAddress(currentAddress);
            enterPermanentAddress(permanentAddress);

            // Scroll to submit button to ensure it's in view
            scrollToElement(submitButton);
            clickSubmit();
            logger.info("Form submitted successfully");
        } catch (Exception e) {
            logger.error("Failed to fill form", e);
            throw new FrameworkException("Form filling failed", e);
        }
    }

    public void validateFormSubmission(String fullName, String email, String currentAddress, String permanentAddress)
    {
        try {
            logger.info("Validating form submission results");
            verifyOutputName(fullName);
            verifyOutputEmail(email);
            verifyOutputCurrentAddress(currentAddress);
            verifyOutputPermanentAddress(permanentAddress);
            logger.info("Form validation completed successfully");
        } catch (AssertionError e) {
            logger.error("Form validation failed", e);
            throw new FrameworkException("Form validation failed", e);
        }
    }
}
