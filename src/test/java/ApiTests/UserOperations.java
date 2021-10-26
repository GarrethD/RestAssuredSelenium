package ApiTests;

import Base.ApiDriver;
import Env_Configuration.Config;
import Utilities.ApiDataSetup.UserRelatedApiDataSetup;
import Utilities.Reporting.ExtentReport.ExtentManager;
import Utilities.Reporting.ExtentReport.ExtentTestManager;
import Utilities.Reporting.ExtentReport.Report;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utilities.Reporting.ExtentReport.ExtentManager.extent;

public class UserOperations {
    private String baseURL = Config.getEnviroment("environment");
    private final String SCENARIO_NAME = "CRUD User Account";
    String newUsername = "";
    String oldUsername = "";

    @BeforeClass
    private void Setup() {
        ExtentManager.getReport();
        Config.ReadPropertiesFile();
    }


    @Test
    private void CreateNewUser(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test creates a new pet store user.").assignCategory(SCENARIO_NAME);
        createNewUser();
    }

    @Test(dependsOnMethods = "CreateNewUser")
    private void UpdatePetstoreUserDetails(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test updates the pet store user details.").assignCategory(SCENARIO_NAME);
        editExistingUser();
    }

    @Test(dependsOnMethods = "UpdatePetstoreUserDetails")
    private void DeletePetstoreUser(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test updates the pet store user details.").assignCategory(SCENARIO_NAME);
        deletePetstoreUser();
    }

    private void createNewUser() {
        ApiDriver.PostRequestUser(UserRelatedApiDataSetup.PostNewUser());
        Report.Pass("Successfully added a new user to the pet store.");
    }

    private void editExistingUser() {
        oldUsername = Config.getPropertiesFile().getProperty("Username");
        newUsername = Config.getPropertiesFile().getProperty("UpdatedUsername");
        ApiDriver.PutRequestUser(UserRelatedApiDataSetup.updateExistingUser(newUsername), oldUsername);
        Report.Pass("Successfully updated the pet store user details.");
    }

    private void deletePetstoreUser() {
        ApiDriver.DeleteRequestUser(newUsername);
        Report.Pass("Successfully deleted a pet store user.");
    }

    @AfterClass
    private void KillDriver() {
        extent.flush();
    }
}


