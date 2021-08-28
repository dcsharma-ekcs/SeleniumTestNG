package util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {

/*
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String fileName = projectPath + "/excel/data_XLSX_10.xlsx";
        int sheetIndex = 0;
        Object[][] data;
        data = testData(fileName, sheetIndex);
    }

 */

    @Test(dataProvider = "testCSV")
    public static void test1(String username, String password, String country){

        System.out.println("data: "+username + " | " +password + " | " +country);
    }

    @DataProvider(name = "testExcel")
    public static Object[][] getData(){
        String projectPath = System.getProperty("user.dir");
        String fileName;
        fileName = projectPath + "/excel/data_XLSX_10.xlsx";
        int sheetIndex = 0;
        //testData(fileName);
        Object data[][] = testData(fileName, sheetIndex);
        return data;
    }

    public static Object[][] testData( String fileName,int sheetIndex){

        ExcelUtils excel = new ExcelUtils(fileName, sheetIndex);

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();
        Object data[][] =new Object[rowCount][colCount];
        for(int i=0; i<rowCount; i++){
            System.out.println("");
            for(int j=0; j<colCount; j++){
                String cellData = excel.getCellDataString(i,j);
                data[i][j] =  cellData;
                System.out.println("data : " + cellData);
            }
        }
        return data;
    }


    @DataProvider(name = "testCSV")
    public Iterator<Object []> provider( ) throws InterruptedException, IOException {
        List<Object []> testCases = new ArrayList<>();
        String[] data= null;
        BufferedReader br = null;
        String line = null;
        String cvsSplitBy = ",";

        //this loop is pseudo code
        String projectPath = System.getProperty("user.dir");
        String csvFile;
        csvFile = projectPath + "/excel/csvdata.csv";

        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            // use comma as separator
            data= line.split(cvsSplitBy);
            testCases.add(data);
        }

        return testCases.iterator();
    }

}
