package ApiTests;

import Base.ApiDriver;
import Configuration.Config;
import Utilities.ApiDataSetup.OrderRelatedApiDataSetup;
import Utilities.Reporting.ExtentReport.ExtentManager;
import Utilities.Reporting.ExtentReport.ExtentTestManager;
import Utilities.Reporting.ExtentReport.Report;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utilities.Reporting.ExtentReport.ExtentManager.extent;

public class PetstoreOrders {
    private String baseURL = Config.getEnviroment("environment");
    private final String SCENARIO_NAME = "CRUD pet store order";
    String[] keyword = {"StudentAdd1", "StudentAdd2"};
    int OrderID = 123129;

    @BeforeClass
    private void Setup() {
        ExtentManager.getReport();
        Config.ReadPropertiesFile();
    }

    @Test
    private void PlaceOrder(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test places an order with the pet store").assignCategory(SCENARIO_NAME);
        placePetStoreOrder();
    }
    @Test(dependsOnMethods = "PlaceOrder")
    private void LookupOrder(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test looks up an already places order with the pet store.").assignCategory(SCENARIO_NAME);
        findPurchaseOrder();
    }
    @Test(dependsOnMethods = "LookupOrder")
    private void DeleteOrder(Method method) {
        ExtentTestManager.startTest(method.getName(), "This test deletes an existing placed order.").assignCategory(SCENARIO_NAME);
        deletePurchaseOrder();
    }

    private void placePetStoreOrder() {

        ApiDriver.PostCreateOrder(OrderRelatedApiDataSetup.PlaceOrderApiDataSetup(OrderID));
        Report.Pass("Successfully placed a new order with the pet store.");
    }

    private void findPurchaseOrder() {

        ApiDriver.GetOrder(OrderID);
        Report.Pass("Successfully looked up a pet store order.");
    }

    private void deletePurchaseOrder() {

        ApiDriver.DeleteOrderRequest(Integer.toString(OrderID));
        Report.Pass("Successfully deleted a pet store order.");
    }

    @AfterClass
    private void KillDriver() {
        extent.flush();
    }
}


