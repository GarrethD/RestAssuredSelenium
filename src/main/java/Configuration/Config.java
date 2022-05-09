package Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config {
    public static Properties CredentialProp = new Properties();
//=======================================================Change these variables to test specific users , browsers or domain combos=============================================

    static String url = URL.BASE_URL.getURL();
    static String user = "User1";

//===============================================================================================================================================================================

    public static String getEnviroment(String Url) {
        String value = System.getProperty(Url);
        if (value == null) {
            System.out.println("No URL was selected. Defaulting To Base url");
            return url;
        }
        System.out.println("Booting up tests using " + value + " Url");
        return value;
    }

    public static String getXmlFileName(String suiteXmlFile) {
        String value = System.getProperty(suiteXmlFile);
        if (value == null) {
            System.out.println("No Klov Report will be generated. A Extent Report will be generated instead");
            return "Local";
        }
        System.out.println("Klov Report name will be set to : " + suiteXmlFile);
        return value;
    }
    public static void ReadPropertiesFile() {
        String systemDelimter = File.separator;
        try {
            CredentialProp.load(new FileInputStream(System.getProperty("user.dir").concat((systemDelimter + "Properties" + systemDelimter + "Credential.properties"))));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getPropertiesFile() {
        return CredentialProp;
    }

}
