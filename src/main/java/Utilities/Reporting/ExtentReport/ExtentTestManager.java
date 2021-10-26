package Utilities.Reporting.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static Map extentTestMap = new HashMap();
    public static ExtentReports extent = ExtentManager.getReport();
    public static ExtentTest test;

    /**
     * This method gets the current test name
     */
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int)(Thread.currentThread().getId()));
    }
    /**
     * This method starts the test allowing extent reports to start logging.
     */
    public static synchronized ExtentTest startTest(String testName, String desc) {
        test = extent.createTest(testName, desc);
        extentTestMap.put((int)(Thread.currentThread().getId()), test);
        return test;
    }
    public static synchronized ExtentTest startTest(String testName) {
        test = extent.createTest(testName);
        extentTestMap.put((int)(Thread.currentThread().getId()), test);
        return test;
    }
}
