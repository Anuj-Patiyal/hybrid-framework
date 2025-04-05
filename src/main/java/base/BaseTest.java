package base;

import com.aventstack.extentreports.ExtentTest;

import drivers.DriverFactory;
import drivers.DriverManager;
import exceptions.FrameworkException;
import listeners.TestListener;
import utils.ConfigManager;
import utils.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    protected void setup(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            logger.info("======== STARTING TEST: {} ========", testName);
            
            // Initialize ExtentTest first
            ExtentTest extentTest = ReportManager.createTest(testName);
            test.set(extentTest);
            
            // Then initialize driver
            DriverManager.setDriver(DriverFactory.createDriver());
            DriverManager.getDriver().get(ConfigManager.getProperty("url"));
            
        } catch (Exception e) {
            logger.error("Test initialization failed", e);
            throw new FrameworkException("Test setup failed", e);
        }
    }
    @AfterMethod(alwaysRun = true)
    protected void teardown() {
        logger.info("======== ENDING TEST ========");
        DriverManager.quitDriver();
        ReportManager.flushReport();
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
