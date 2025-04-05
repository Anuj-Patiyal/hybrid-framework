package tests;

import base.BaseTest;
import drivers.DriverManager;
import exceptions.FrameworkException;
import pages.HomePage;
import pages.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SearchTest.class);
    
    @Test(description = "Verify product search functionality")
    public void testProductSearch() {
        try {
            logger.info("Executing testProductSearch");
            
            HomePage homePage = new HomePage(DriverManager.getDriver())
                .handleLoginModal();
                
            SearchPage searchPage = homePage.searchProduct("iPhone 16");
            
            logger.info("Validating search results");
            Assert.assertTrue(searchPage.areResultsDisplayed(), 
                "Search results not displayed");
            Assert.assertTrue(searchPage.getProductCount() > 0,
                "No products found in search results");

        } catch (FrameworkException e) {
            logger.error("Test failed due to framework error: {}", e.getMessage());
            Assert.fail("Framework error occurred: " + e.getMessage());
        }
    }
}