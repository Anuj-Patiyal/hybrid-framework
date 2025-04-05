package listeners;

import utils.ConfigManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles test retry logic with configurable attempts
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int count = 0;
    private final int maxRetries = ConfigManager.getIntProperty("maxRetries");

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetries) {
            logger.warn("Retrying test {}: Attempt {}", result.getName(), count+1);
            count++;
            return true;
        }
        return false;
    }
}