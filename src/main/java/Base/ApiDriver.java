package Base;

import Utilities.Reporting.ExtentReport.Report;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiDriver {
    public static String PostRequestPet(String body) {
        int RequestResponse;
        String userID = "";
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2";
            Response response = given()
                    .header("Content-type", "application/json")
                    .body(body)
                    .when()
                    .post("/pet")
                    .then()
                    .extract().response();
            RequestResponse = response.getStatusCode();
            if (RequestResponse !=200) {
                Report.Fail("Failed to create user by API.");
                System.out.println(response.getBody().asString());
            }
            userID  = response.jsonPath().getString("id");
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            System.out.println(e);
            e.printStackTrace();
        }
        return userID;
    }
    public static void PutRequest(String body) {
        int RequestResponse;
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2";
            Response response = given()
                    .header("Content-type", "application/json")
                    .body(body)
                    .when()
                    .put("/pet")
                    .then()
                    .extract().response();
            RequestResponse = response.getStatusCode();
            if (RequestResponse !=200) {
                Report.Fail("Failed to create user by API.");
                System.out.println(response.getBody().asString());
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            e.printStackTrace();
        }
    }
    public static boolean DeleteRequest(String petID) {
        String RequestResponse = "";
        Boolean isDeleted = false;
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2/"+petID+"";
            Response response = given()
                    .header("Content-type", "application/json")
                    // .body("{\"schoolId\":" + schoolID + ",\"schoolStudentId\":\"" + studentID + "\"}")
                    .when()
                    .delete("URL goes here")
                    .then()
                    .extract().response();
            RequestResponse = response.getBody().asString();
            if (RequestResponse.contains("200")) {
                isDeleted = true;
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            e.printStackTrace();
        }
        return isDeleted;
    }

//============================================================ User based APIs ===============================================================
public static void PostRequestUser(String body) {
    int RequestResponse;

    try {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post("/user")
                .then()
                .extract().response();
        RequestResponse = response.getStatusCode();
        if (RequestResponse !=200) {
            Report.Fail("Failed to create user by API.");
            System.out.println(response.getBody().asString());
        }

    } catch (Exception e) {
        Report.Fail("Error making request: " + e + "");
        System.out.println(e);
        e.printStackTrace();
    }

}
    public static void PutRequestUser(String body,String oldUsername) {
        int RequestResponse;
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2/";
            Response response = given()
                    .header("Content-type", "application/json")
                    .body(body)
                    .when()
                    .put("/user/"+oldUsername+"")
                    .then()
                    .extract().response();
            RequestResponse = response.getStatusCode();
            if (RequestResponse !=200) {
                Report.Fail("Failed to update user by API.");
                System.out.println(response.getBody().asString());
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            e.printStackTrace();
        }
    }
    public static boolean DeleteRequestUser(String username) {
        String RequestResponse = "";
        Boolean isDeleted = false;
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2/";
            Response response = given()
                    .header("Content-type", "application/json")
                    .when()
                    .delete("/user/" + username + "")
                    .then()
                    .extract().response();
            RequestResponse = response.getBody().asString();
            if (RequestResponse.contains("200")) {
                isDeleted = true;
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            e.printStackTrace();
        }
        return isDeleted;
    }

 //=================================================================Order based APIs======================================================================
    public static void PostCreateOrder(String body) {
        int RequestResponse;

        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2";
            Response response = given()
                    .header("Content-type", "application/json")
                    .body(body)
                    .when()
                    .post("/store/order")
                    .then()
                    .extract().response();
            RequestResponse = response.getStatusCode();
            if (RequestResponse !=200) {
                Report.Fail("Failed to place order by API.");
                System.out.println(response.getBody().asString());
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public static void GetOrder(int orderID) {
        int RequestResponse;
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2";
            Response response = given()
                    .header("Content-type", "application/json")
                    .when()
                    .get("/store/order/"+orderID+"")
                    .then()
                    .extract().response();
            RequestResponse = response.getStatusCode();
            if (RequestResponse !=200) {
                Report.Fail("Failed to get order by API.");
                System.out.println(response.getBody().asString());
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            e.printStackTrace();
        }
    }
    public static boolean DeleteOrderRequest(String orderID) {
        String RequestResponse = "";
        Boolean isDeleted = false;
        try {
            RestAssured.baseURI = "https://petstore.swagger.io/v2/";
            Response response = given()
                    .header("Content-type", "application/json")
                    .when()
                    .delete("/store/order/"+orderID+"")
                    .then()
                    .extract().response();
            RequestResponse = response.getBody().asString();
            if (RequestResponse.contains("200")) {
                isDeleted = true;
            }
        } catch (Exception e) {
            Report.Fail("Error making request: " + e + "");
            e.printStackTrace();
        }
        return isDeleted;
    }
}


