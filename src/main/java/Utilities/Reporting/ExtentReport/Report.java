package Utilities.Reporting.ExtentReport;

import com.aventstack.extentreports.Status;

import org.testng.Assert;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Report {

    /**
     * This class is dedicated to checking if test pass or fails.
     * Based on the status of a test we can then make decisions and take actions like taking screenshots.
     */
    public static void Pass(String message) {
        ExtentTestManager.test.log(Status.PASS, message);

    }
    public static void Pass(String message,String path) {
        ExtentTestManager.test.log(Status.PASS, message);
        ExtentTestManager.getTest().addScreenCaptureFromPath(path);
    }
    public static void Fail(String message,String path) {
        ExtentTestManager.test.log(Status.FAIL, message);
        ExtentTestManager.getTest().addScreenCaptureFromPath(path);
        Assert.fail();// to add name in extent report
    }

    public static void Fail(String message) {
        try {
            ExtentTestManager.test.log(Status.FAIL, message);
            Assert.fail();// to add name in extent report
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    public static void FailApi(String message) {
        ExtentTestManager.test.log(Status.FAIL, message);
        Assert.fail();
    }

    public static void Skip(String message, String path) throws IOException {
        ExtentTestManager.test.log(Status.SKIP, message + ExtentTestManager.getTest().addScreenCaptureFromPath(path)); // to add error/exception in extent report
    }

    public static void Info(String message, String path) throws IOException {
        ExtentTestManager.test.log(Status.INFO, message + ExtentTestManager.getTest().addScreenCaptureFromPath(path));
    }

    //TODO Overload
    public static void InfoNoScreenshot(String message) {
        ExtentTestManager.test.log(Status.INFO, message);
    }

    public static void Warning(String message, String path) throws IOException {
        ExtentTestManager.test.log(Status.WARNING, message + ExtentTestManager.getTest().addScreenCaptureFromPath(path));
    }

    public static String datetime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("EN", "SA"));
        String date = simpleDateFormat.format(new Date());
        return date.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String format(Method method) {
        return method.getName().replaceAll("_", " ");
    }
}