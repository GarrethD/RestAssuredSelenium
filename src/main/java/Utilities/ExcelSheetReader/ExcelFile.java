package Utilities.ExcelSheetReader;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;

public class ExcelFile {
    private File excelFile;
    private final String FILE_PATH_EXCLUDING_NAME = System.getProperty("user.dir").concat("\\KeywordExcelFiles\\");
    private String fileName;
    private String sheetName;
    private XSSFWorkbook excelWorkbook;
    private XSSFSheet excelSheet;

    public ExcelFile(String fileName,String sheetName){
        try{
            if(doesFileExist(fileName)){
                this.fileName = fileName;
                this.sheetName = sheetName;
                this.excelWorkbook = new XSSFWorkbook(FILE_PATH_EXCLUDING_NAME.concat(fileName).concat(".xlsx"));
                this.excelSheet = excelWorkbook.getSheet(sheetName);

            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    private boolean doesFileExist(String fileName){
        excelFile = new File(FILE_PATH_EXCLUDING_NAME.concat(fileName).concat(".xlsx"));
        return excelFile.exists();
    }

    public XSSFWorkbook getExcelWorkbook(){
        return this.excelWorkbook;
    }

    public XSSFSheet getExcelSheet(){
        return this.excelSheet;
    }
}
