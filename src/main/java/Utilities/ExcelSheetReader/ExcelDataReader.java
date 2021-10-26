package Utilities.ExcelSheetReader;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelDataReader {
    private ExcelFile excelFile;
    private Map<String, String> keywordData;

    public ExcelDataReader(ExcelFile excelFile, String keyword){
        this.excelFile = excelFile;
        this.keywordData = getKeywordData(keyword);
    }

    public String getCellData(int rowNum, int colNum) {
        XSSFCell cell = excelFile.getExcelSheet().getRow(rowNum).getCell(colNum);
        return cell.getStringCellValue();
    }

    private boolean keywordExistsInSheet(String keyword) {
        DataFormatter df = new DataFormatter();
        Iterator rowIterator = excelFile.getExcelSheet().rowIterator();
        String cellValue = "";
        boolean keywordExists = false;

        while (rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            XSSFCell cell = row.getCell(0);
            cellValue = df.formatCellValue(cell);

            if (!cellValue.equals("END")) {
                if (cellValue.equals(keyword)) {
                    keywordExists = true;
                    break;
                }
            }
            else {
                keywordExists = false;
            }

        }
        return keywordExists;
    }

    private int getRowIndexOfKeyword(String keyword) {

        if(keywordExistsInSheet(keyword)){
            Iterator rowIterator = excelFile.getExcelSheet().rowIterator();
            int rowIndex = 0;
            String cellValue = "";
            DataFormatter df = new DataFormatter();

            while (rowIterator.hasNext()) {
                XSSFRow row = (XSSFRow) rowIterator.next();
                XSSFCell cell = row.getCell(0);
                cellValue = df.formatCellValue(cell);

                if (cellValue.equals(keyword)) {
                    rowIndex = cell.getRowIndex();
                    break;
                }
            }

            return rowIndex;
        }
        else{
            return - 1;
        }
    }

    private Map<String, String> getKeywordData(String keyword) {
        int keywordRowIndex;
        XSSFCell parameterCell = null;
        XSSFCell parameterValueCell = null;
        DataFormatter df = new DataFormatter();
        String parameter = "";
        String parameterValue = "";

        if (keywordExistsInSheet(keyword)) {
            keywordRowIndex = getRowIndexOfKeyword(keyword);
            keywordData = new HashMap<String,String>();

            boolean isCellEmpty = false;
            int i = 1;

            while(!isCellEmpty){
                parameterCell = excelFile.getExcelSheet().getRow(keywordRowIndex).getCell(i);
                parameterValueCell = excelFile.getExcelSheet().getRow(keywordRowIndex + 1).getCell(i);
                if(!df.formatCellValue(parameterCell).isEmpty()){
                    parameter = df.formatCellValue(parameterCell);
                    parameterValue = df.formatCellValue(parameterValueCell);
                    keywordData.put(parameter,parameterValue);
                    i++;
                }
                else{
                    isCellEmpty = true;
                }
            }

        }
        else{
            //TODO
            System.out.println("Keyword doesn't exist");
        }

        return keywordData;

    }

    public String getParameterValue(String parameter){
        return this.keywordData.get(parameter).toString().trim();
    }


}
