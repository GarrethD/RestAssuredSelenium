package FrontEndTests;

import Base.Driver;
import Env_Configuration.Config;
import PageObjects.FrontEndPageObjects.PageObjects;
import Utilities.ExcelSheetReader.ExcelDataReader;
import Utilities.Reporting.ExtentReport.ExtentManager;
import Utilities.Reporting.ExtentReport.ExtentTestManager;
import Utilities.Reporting.ExtentReport.Report;
import org.testng.annotations.*;
import static Utilities.Reporting.ExtentReport.ExtentManager.extent;

import java.lang.reflect.Method;

public class BookStoreApplication {
    private Driver driver;
    private ExcelDataReader reader;
    private String browserName;
    private String baseURL = Config.getEnviroment("environment");
    String bookTitle = "Learning JavaScript Design Patterns";



    @BeforeClass
    private void Setup() {
        browserName = Config.getBrowser("browser");
        driver = new Driver(browserName);
        ExtentManager.getReport();
        Config.ReadPropertiesFile();
    }

    @Test
    private void LoginAndValidate(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test Logs into an account.");
        navigateToBookstorePage();
        loginToAccount();
    }
    @Test(dependsOnMethods = "LoginAndValidate")
    private void AddBookToCollection(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test adds a book to the user collection and validates.");
        navigateToBookstorePage();
        loginToAccount();
        addBookToCollection();
        navigateToUserProfilePage();
        validateBookAddedToUserCollection();
    }
    @Test(dependsOnMethods = "AddBookToCollection")
    private void DeleteBookFromCollection(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test deletes the the book added to users collection.");
        navigateToBookstorePage();
        loginToAccount();
        navigateToUserProfilePage();
        DeleteBookFromProfile();
    }
    private void navigateToBookstorePage()
    {
        driver.navigateToURL(baseURL);
        driver.scrollElementIntoView(PageObjects.BookStoreApplicationButton());
        driver.waitForElementVisible(PageObjects.BookStoreApplicationButton());
        driver.clickByXPath(PageObjects.BookStoreApplicationButton());
        driver.waitForElementVisible(PageObjects.BookStoreLandingPageHeader());
        Report.Pass("Successfully Navigated to the Book Store web page : "+baseURL+".");
    }

    private void loginToAccount()
    {
        driver.waitForElementVisible(PageObjects.BookStoreLandingPageHeader());
        driver.clickByXPath(PageObjects.LoginButton());
        driver.waitForElementVisible(PageObjects.LoginToBookStoreLandingPageHeader());
        driver.enterTextByXpath(PageObjects.LoginUsernameField(),Config.getPropertiesFile().getProperty("Username"));
        driver.enterTextByXpath(PageObjects.LoginPasswordField(),Config.getPropertiesFile().getProperty("Password"));
        driver.clickByXPath(PageObjects.LoginButton());

        Report.Pass("Successfully logged into the book store.");
    }
    private void addBookToCollection()
    {
        driver.pauseExecution(2);
        driver.waitForElementVisible(PageObjects.BookStoreLandingPageHeader());
        driver.waitForElementVisible(PageObjects.BookTitleLink(bookTitle));
        driver.scrollElementIntoView(PageObjects.BookTitleLink(bookTitle));
        driver.clickByXPath(PageObjects.BookTitleLink(bookTitle));
        //Validate the link took the user to the correct book
        driver.waitForElementVisible(PageObjects.TitleOfBook(bookTitle));
        driver.waitForElementVisible(PageObjects.AddToYourCollectionButton());
        driver.scrollElementIntoView(PageObjects.AddToYourCollectionButton());
        driver.pauseExecution(2);
        driver.clickByXPath(PageObjects.AddToYourCollectionButton());
        driver.pauseExecution(2);
        driver.acceptAlert();

        Report.Pass("Successfully added "+bookTitle+" to user collection.");
    }
    private void navigateToUserProfilePage()
    {
        driver.waitForElementVisible(PageObjects.ProfileButton());
        driver.scrollElementIntoView(PageObjects.ProfileButton());
        driver.clickByXPath(PageObjects.ProfileButton());
        driver.waitForElementVisible(PageObjects.ProfilePageHeader());
        Report.Pass("Successfully navigated to the user profile page.");
    }
    private void validateBookAddedToUserCollection()
    {
        driver.waitForElementVisible(PageObjects.BookTitleLink(bookTitle));
        Report.Pass("Successfully validated that the book was added to the users collection under the users profile.");
    }
    private void DeleteBookFromProfile()
    {
        driver.waitForElementVisible(PageObjects.BookTitleLink(bookTitle));
        driver.waitForElementVisible(PageObjects.DeleteBookTrashCanButton(bookTitle));
        driver.clickByXPath(PageObjects.DeleteBookTrashCanButton(bookTitle));
        driver.waitForElementVisible(PageObjects.DeleteBookOkButton());
        driver.clickByXPath(PageObjects.DeleteBookOkButton());
        driver.pauseExecution(2);
        driver.acceptAlert();
        driver.pauseExecution(2);
        driver.waitForElementVisible(PageObjects.NoRowDataText());
        Report.Pass("Successfully validated that the book was added to the users collection under the users profile.");
    }

@AfterMethod
private void Logout()
{
    driver.waitForElementVisible(PageObjects.LogoutButton());
    driver.clickByXPath(PageObjects.LogoutButton());

    Report.Pass("Successfully logged out of the Book store.");
}

    @AfterClass
    private void KillDriver() {
        extent.flush();
        driver.closeFocusedBrowserTab();
    }
}
