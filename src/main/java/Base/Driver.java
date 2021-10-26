package Base;


import Utilities.Reporting.ExtentReport.ExtentTestManager;
import Utilities.Reporting.ExtentReport.Report;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Driver {


    private final int GLOBAL_TIMEOUT = 40;
    public WebDriver webDriver;


    private String browserName;


    public Driver() {
    }

    public Driver(String browserName) {
        this.browserName = browserName;
        switch (this.browserName.toUpperCase()) {

            case "CHROME":
                initializeChromeDriver();
                break;
            case "FIREFOX":
                initializeFirefoxDriver();
                break;
        }
    }


    private void initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    private void initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

    public void navigateToURL(String URL) {
        try {
            webDriver.get(URL);
            Thread.sleep(250);
        } catch (Exception e) {
            Report.Fail("Failed to navigate to webpage. URL used : " + URL + "", takeScreenshot());
        }
    }
    public void ReloadPage() {
        try {
            webDriver.navigate().refresh();
            Thread.sleep(250);
        } catch (Exception e) {
            Report.Fail("Failed to reload webpage.", takeScreenshot());
        }
    }

    public void clickByXPath(String XPath) {
        try {
            waitForElementVisible(XPath);
            webDriver.findElement(By.xpath(XPath)).click();
        } catch (Exception e) {
            Report.Fail("Failed to click on the element. XPath : " + XPath, takeScreenshot());
        }
    }

    public void clearTextByXPath(String XPath) {
        try {
            waitForElementVisible(XPath);
            webDriver.findElement(By.xpath(XPath)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        } catch (Exception e) {
            Report.Fail("Failed to clear text from element.  XPath : " + XPath + "", takeScreenshot());
        }
    }


    public void enterTextByXpath(String XPath, String valueToType) {
        try {
            clearTextByXPath(XPath);
            webDriver.findElement(By.xpath(XPath)).sendKeys(valueToType);
        } catch (Exception e) {
            Report.Fail("Failed to enter text into element.  XPath : " + XPath + " | Text to be entered: " + valueToType + "", takeScreenshot());
        }
    }

    public void switchToDefaultContent() {
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(0));
        } catch (Exception e) {
            Report.Fail("Failed to switch to current browser tab.", takeScreenshot());
        }
    }

    public void acceptAlert() {
        try {
            webDriver.switchTo().alert().accept();

        } catch (Exception e) {
            Report.Fail("Failed to accept the alert popup.", takeScreenshot());
        }
    }

    public boolean isElementVisible(String XPath) {
        int timer = 0;
        Boolean isPresent = false;
        WebElement element;
        try {
            while (timer <= GLOBAL_TIMEOUT && isPresent == false) {
                Thread.sleep(1000);
                isPresent = webDriver.findElements(By.xpath(XPath)).size() > 0;


                if (isPresent) {
                    element = webDriver.findElement(By.xpath(XPath));
                    if (element.isDisplayed() && element.isEnabled()) {
                        timer++;
                        break;
                    }
                } else {
                    timer++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isPresent;
    }

    public boolean isElementNotVisible(String XPath) {
        int timer = 0;
        Boolean isPresent = false;
        try {
            while (timer <= GLOBAL_TIMEOUT) {
                Thread.sleep(1000);
                isPresent = webDriver.findElements(By.xpath(XPath)).size() == 0;
                if (isPresent) {
                    break;
                } else {
                    timer++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isPresent;
    }

    public boolean isElementNotVisible(String XPath, int timeInSeconds) {
        int timer = 0;
        Boolean isPresent = false;
        try {
            while (timer <= timeInSeconds) {
                Thread.sleep(1000);
                isPresent = webDriver.findElements(By.xpath(XPath)).size() == 0;
                if (isPresent) {
                    break;
                } else {
                    timer++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isPresent;
    }

    public void waitForElementVisible(String XPath) {
        if (!isElementVisible(XPath)) {
            Report.Fail("Could not find element. Xpath : " + XPath, takeScreenshot());
        }
    }

    public void waitForElementNotVisible(String XPath) {
        if (!isElementNotVisible(XPath)) {
            Report.Fail("Could not find element. Please check the xpath used. Xpath : " + XPath, takeScreenshot());
        }
    }

    public void closeFocusedBrowserTab() {
        try {
            webDriver.close();
        } catch (Exception e) {
            Report.Fail("Failed to close current browser tab in focus.", takeScreenshot());
        }
    }

    public String takeScreenshot() {
        String path = "";
        String systemDelimter = File.separator;

        try {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("EN", "SA"));
            String date = simpleDateFormat.format(new Date());
            String newDate = date.replaceAll("[^a-zA-Z0-9]", "");
            TakesScreenshot ts = (TakesScreenshot) webDriver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            path = System.getProperty("user.dir") + systemDelimter + "ExtentReports" + systemDelimter + ExtentTestManager.getTest() + newDate + ".png";
            FileUtils.copyFile(source, new File(System.getProperty("user.dir") + systemDelimter + "ExtentReports" + systemDelimter + ExtentTestManager.getTest() + newDate + ".png"));
            System.out.println("the Screenshot is taken");
        } catch (Exception e) {
            System.out.println(e);
        }
        return path;
    }

    public void pauseExecution(int PauseTimeInSeconds) {
        try {
            int PauseTime = PauseTimeInSeconds*1000;
            Thread.sleep(PauseTime);
        } catch (Exception e) {
            Report.Fail("Failed to pause the Automation flow for " + PauseTimeInSeconds + "seconds." + takeScreenshot());
        }
    }

    public void scrollElementIntoView(String xpath) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (Exception e) {
            Report.Fail("Failed to scroll element into view." + takeScreenshot());
        }
    }

}