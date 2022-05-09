package ApiTests;

import Base.ApiDriver;
import Configuration.Config;
import Utilities.ApiDataSetup.PetRelatedApiDataSetup;
import Utilities.Reporting.ExtentReport.ExtentManager;
import Utilities.Reporting.ExtentReport.ExtentTestManager;
import Utilities.Reporting.ExtentReport.Report;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utilities.Reporting.ExtentReport.ExtentManager.extent;

public class EverythingAboutYourPet {
    private String baseURL = Config.getEnviroment("environment");
    private final String SCENARIO_NAME = "CRUD Pet admission. ";
    public  String GeneratedPetID = "";


    @BeforeClass
    private void Setup() {
        ExtentManager.getReport();
        Config.ReadPropertiesFile();
    }

    @Test
    private void AddPetToPetstore(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test inserts a new pet into the pet store").assignCategory(SCENARIO_NAME);
        addPetToStore();
    }
    @Test(dependsOnMethods = "AddPetToPetstore")
    private void EditPetDetails(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test updates a current pet details in the pet store.").assignCategory(SCENARIO_NAME);
        editPetDetails();
    }
    @Test(dependsOnMethods = "EditPetDetails")
    private void DeletePetFromStore(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test Deletes a pet from the pet store.").assignCategory(SCENARIO_NAME);
        deletePetFromStore();
    }


    private void addPetToStore() {
        GeneratedPetID = ApiDriver.PostRequestPet(PetRelatedApiDataSetup.PostNewPet());
        Report.Pass("Successfully added a pet to the pet store.");
    }
    private void editPetDetails()
    {
        ApiDriver.PutRequest(PetRelatedApiDataSetup.UpdateExistingPetDataSetup("doggieUpdate",GeneratedPetID));
        Report.Pass("Successfully  edited pet details.");
    }
    private void deletePetFromStore()
    {
        ApiDriver.DeleteRequest(GeneratedPetID);
        Report.Pass("Successfully deleted a pet from the pet store.");
    }
    @AfterClass
    private void KillDriver() {
        extent.flush();
    }
}


