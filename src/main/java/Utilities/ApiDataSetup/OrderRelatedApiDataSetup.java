package Utilities.ApiDataSetup;

import Utilities.Reporting.ExtentReport.Report;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OrderRelatedApiDataSetup {

    //================================================ After school program data setup ===============================================================

    public static String PlaceOrderApiDataSetup(int orderID)
    {
        String RequestBodyString = "";
        try {
            JSONObject dataSet = new JSONObject();
            dataSet.put("id", orderID);
            dataSet.put("petId", 0);
            dataSet.put("quantity", 1);
            dataSet.put("shipDate", "2022-02-01T13:56:03.083Z");
            dataSet.put("status", "placed");
            dataSet.put("complete", "true");

            JSONArray properties = new JSONArray();
            properties.add(dataSet);

            String RawJsonArrayString = properties.toJSONString();
            RequestBodyString =  RawJsonArrayString.substring(1, RawJsonArrayString.length() - 1);
        }  catch (Exception e) {
            Report.Fail("Error setting up Request body: "+e+"");
            e.printStackTrace();
        }
        return RequestBodyString;
    }
    public static String LookupExistingOrderApiDataSetup(String updatedUsername)
    {
        String RequestBodyString = "";
        try {
            // ExcelDataReader reader = new ExcelDataReader(new ExcelFile("PikMyKid", "AddStudent"), Keyword);
            JSONObject dataSet = new JSONObject();
            dataSet.put("id", 0);
            dataSet.put("username", updatedUsername);
            dataSet.put("firstName", "Garreth");
            dataSet.put("lastName", "Dean");
            dataSet.put("email", "garreth@yahoo.com");
            dataSet.put("password", "ThisIsNotAPassword1!");
            dataSet.put("phone", "0647785545");
            dataSet.put("userStatus", 0);
            JSONArray properties = new JSONArray();
            properties.add(dataSet);

            String RawJsonArrayString = properties.toJSONString();
            RequestBodyString =  RawJsonArrayString.substring(1, RawJsonArrayString.length() - 1);
        }  catch (Exception e) {
            Report.Fail("Error setting up Request body: "+e+"");
            e.printStackTrace();
        }
        return RequestBodyString;
    }
}
