package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Manages Extent Reports with thread-safe test instances
 */
public class ReportManager {
    private static final Logger logger = LogManager.getLogger(ReportManager.class);
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HHmmss").format(new Date());
            String reportName = "TestReport_" + timestamp + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/" + reportName);
            
            spark.config().setDocumentTitle("Flipkart Automation Report");
            spark.config().setReportName("Flipkart Test Results");
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            
            logger.info("Initialized Extent Report: {}", reportName);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            logger.info("Finalized Extent Report");
        }
    }
}