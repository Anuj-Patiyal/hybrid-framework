package tests;

import base.BaseTest;
import config.Configuration;
import org.testng.annotations.Test;
import pages.TextBoxPage;

public class TextBoxTest extends BaseTest {

    @Test
    public void testTextBoxFormSubmission()
    {
        try {
            logger.info("Starting text box form submission test using Page Object Model");

            // Navigate to text-box page
            navigateTo(Configuration.getBaseUrl() + "/text-box");

            // Initialize Page Object
            TextBoxPage textBoxPage = new TextBoxPage(driver);

            // Test data
            String fullName = "John Doe";
            String email = "john.doe@example.com";
            String currentAddress = "123 Main St, Current City";
            String permanentAddress = "456 Park Ave, Permanent City";

            // Fill form and submit using Page Object methods
            textBoxPage.fillForm(fullName, email, currentAddress, permanentAddress);

            // Verify output using Page Object methods
            textBoxPage.validateFormSubmission(fullName, email, currentAddress, permanentAddress);

            logger.info("Text box form submission test completed successfully using POM");

        } catch (Exception e) {
            logger.error("Test execution failed", e);
            throw e; // Re-throw to maintain existing exception handling
        }
    }
}
