package pages;

import base.BasePage;
import exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(SearchPage.class);
    private final By productTitles = By.xpath("//div[@class='KzDlHZ']");
    private final By productPrices = By.xpath("//div[@class='Nx9bqj _4b5DiR']");
    private final By resultsHeader = By.xpath("//span[@class='BUOuZu']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void printSearchResults() {
        try {
            // Wait for results to load
            waitForElement(resultsHeader);
            
            List<WebElement> products = waitForElements(productTitles);
            List<WebElement> prices = waitForElements(productPrices);

            validateResults(products, prices);
            logResults(products, prices);

        } catch (FrameworkException e) {
            logger.error("Search results processing failed: {}", e.getMessage());
            throw new FrameworkException("Search results validation error", e);
        }
    }

    private void validateResults(List<WebElement> products, List<WebElement> prices) {
        if(products.isEmpty() || prices.isEmpty()) {
            logger.error("No products found in search results");
            throw new FrameworkException("Empty search results");
        }
        
        if(products.size() != prices.size()) {
            logger.error("Product/Price count mismatch. Products: {}, Prices: {}", 
                        products.size(), prices.size());
            throw new FrameworkException("Search results data inconsistency");
        }
    }

    private void logResults(List<WebElement> products, List<WebElement> prices) {
        logger.info("Found {} products in search results", products.size());
        for(int i = 0; i < products.size(); i++) {
            String product = products.get(i).getText().trim();
            String price = prices.get(i).getText().trim();
            logger.info("[Product {}] {} - {}", i+1, product, price);
        }
    }
    public boolean areResultsDisplayed() {
        try {
            return waitForElement(resultsHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        return waitForElements(productTitles).size();
    }
}