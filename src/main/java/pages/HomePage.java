package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Flipkart Home Page operations
 */
public class HomePage extends BasePage {
    private final By closeLoginModal = By.xpath("//button[text()='✕']");
    private final By searchBox = By.name("q");
    private final By searchButton = By.cssSelector("button[type='submit']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage handleLoginModal() {
        if (isVisible(closeLoginModal)) {
            logger.info("Closing login modal");
            click(closeLoginModal);
        }
        return this;
    }

    public SearchPage searchProduct(String productName) {
        logger.info("Searching for product: {}", productName);
        type(searchBox, productName);
        click(searchButton);
        return new SearchPage(driver);
    }
}
