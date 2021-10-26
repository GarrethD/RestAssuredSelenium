package Utilities.ApiDataSetup;

import Utilities.Reporting.ExtentReport.Report;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PetRelatedApiDataSetup {

    //================================================ After school program data setup ===============================================================
    public static String GetMethod(int schoolId, String programName)
    {
        String RequestBodyString = "";
        try {
            JSONObject dataSet = new JSONObject();
            dataSet.put("SchoolId", schoolId);
            dataSet.put("Type", "AfterSchoolProgram");
            dataSet.put("Value", programName);
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
    public static String PostNewPet()
    {
        String RequestBodyString = "";
        try {
           // ExcelDataReader reader = new ExcelDataReader(new ExcelFile("PikMyKid", "AddStudent"), Keyword);
            JSONObject dataSet = new JSONObject();
            dataSet.put("id", 0);
            dataSet.put("category.id", 0);
            dataSet.put("category.name", "string");
            dataSet.put("name", "doggie");

            JSONArray array = new JSONArray();
            array.add("String");
            dataSet.put("photoUrls",array);

            JSONArray array2 = new JSONArray();
            array.add("id:0");
            array.add("name:string");
            dataSet.put("tags.id",array2);

            dataSet.put("status","available");

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
    public static String UpdateExistingPetDataSetup(String UpdatePetName,String PetId)
    {
        String RequestBodyString = "";
        try {
            // ExcelDataReader reader = new ExcelDataReader(new ExcelFile("PikMyKid", "AddStudent"), Keyword);
            JSONObject dataSet = new JSONObject();
            dataSet.put("id", PetId);
            dataSet.put("category.id", 0);
            dataSet.put("category.name", "string");
            dataSet.put("name", UpdatePetName);

            JSONArray array = new JSONArray();
            array.add("String");
            dataSet.put("photoUrls",array);

            JSONArray array2 = new JSONArray();
            array.add("id:0");
            array.add("name:string");
            dataSet.put("tags.id",array2);

            dataSet.put("status","available");

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
