package listeners;

import com.aventstack.extentreports.Status;
import base.BaseTest;
import exceptions.FrameworkException;
import utils.ConfigManager;
import utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener for comprehensive test monitoring and reporting
 */
public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("====================================================================");
        logger.info("Starting test: {}", result.getName());
        logger.debug("Test parameters: {}", result.getParameters());
        BaseTest.getTest().log(Status.INFO, "Test started with parameters: " + 
            formatParameters(result.getParameters()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test PASSED: {}", result.getName());
        BaseTest.getTest().pass("Test completed successfully");
        attachSystemInfoToReport();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test FAILED: {}", result.getName(), result.getThrowable());
        
        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getName());
        if (!screenshotPath.isEmpty()) {
            logger.debug("Attaching screenshot: {}", screenshotPath);
            BaseTest.getTest().fail(result.getThrowable())
                    .addScreenCaptureFromPath(screenshotPath);
        } else {
            BaseTest.getTest().fail(result.getThrowable());
        }
        
        attachSystemInfoToReport();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test SKIPPED: {}", result.getName());
        if (result.getThrowable() != null) {
            BaseTest.getTest().skip(result.getThrowable());
        } else {
            BaseTest.getTest().skip("Test skipped due to configuration issues");
        }
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("=============== TEST SUITE STARTED: {} ===============", context.getName());
        if (context.getSuite().getParallel() != null) {
            logger.info("Parallel execution mode: {}", context.getSuite().getParallel());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=============== TEST SUITE COMPLETED: {} ===============", 
            context.getName());
        logger.info("Passed tests: {}", context.getPassedTests().size());
        logger.info("Failed tests: {}", context.getFailedTests().size());
        logger.info("Skipped tests: {}", context.getSkippedTests().size());
    }

    private String formatParameters(Object[] parameters) {
        if (parameters == null || parameters.length == 0) {
            return "No parameters";
        }
        StringBuilder sb = new StringBuilder();
        for (Object param : parameters) {
            sb.append("\n  - ").append(param.toString());
        }
        return sb.toString();
    }

    private void attachSystemInfoToReport() {
        try {
            BaseTest.getTest().info("Browser: " + ConfigManager.getProperty("browser"));
            BaseTest.getTest().info("Environment: " + ConfigManager.getProperty("env"));
            BaseTest.getTest().info("OS: " + System.getProperty("os.name"));
            BaseTest.getTest().info("Java Version: " + System.getProperty("java.version"));
        } catch (FrameworkException e) {
            logger.warn("Failed to attach system info: {}", e.getMessage());
        }
    }
}