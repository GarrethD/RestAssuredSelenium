package Utilities.Reporting.ExtentReport;


import Configuration.Config;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;



// ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

    static String systemDelimter = File.separator;
    public static ExtentSparkReporter spark;
    public static ExtentReports extent;
    final static String directoryPath = System.getProperty("user.dir") + systemDelimter + "ExtentReports";
    /**
     * The  getReporter method starts the Utilities.Reporting when a test runs. This does settings as well as configurations.
     * We set the save location of the reports and we set the config file to be used.
     */
    public synchronized static ExtentReports getReport() {
        //if the folder does not exist when the framework is pulled then this will create the folder.
        if(!new File(directoryPath).exists())
        {
            new File(directoryPath).mkdirs();
        }

        if (spark == null) {
            spark = new ExtentSparkReporter(System.getProperty("user.dir")+systemDelimter+"ExtentReports"+systemDelimter+"ExtentReportResults.html");
            // ExtentSparkReporter.loadXMLConfig("src"+systemDelimter+"main"+systemDelimter+"java"+systemDelimter+"Utilities.Reporting"+systemDelimter+"ExtentReport"+systemDelimter+"extent-config.xml");
            extent = new ExtentReports();

            if(Config.getXmlFileName("surefire.suiteXmlFiles").equals("Local"))
            {
                extent.attachReporter(spark);
                System.out.println("HTML Reporter has been initialized!");
            }

            try {
                extent.setSystemInfo("Host name", InetAddress.getLocalHost().getHostName());
                extent.setSystemInfo("Environemnt", ""+ Config.getEnviroment("environment")+"");
                extent.setSystemInfo("user", ""+System.getProperty("user.name")+"");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return extent;
    }


}
