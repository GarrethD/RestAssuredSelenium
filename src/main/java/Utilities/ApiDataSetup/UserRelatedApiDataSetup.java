package Utilities.ApiDataSetup;

import Utilities.Reporting.ExtentReport.Report;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserRelatedApiDataSetup {

    //================================================ After school program data setup ===============================================================

    public static String PostNewUser()
    {
        String RequestBodyString = "";
        try {
           // ExcelDataReader reader = new ExcelDataReader(new ExcelFile("PikMyKid", "AddStudent"), Keyword);
            JSONObject dataSet = new JSONObject();
            dataSet.put("id", 0);
            dataSet.put("username", "GarrethD");
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
    public static String updateExistingUser(String updatedUsername)
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
