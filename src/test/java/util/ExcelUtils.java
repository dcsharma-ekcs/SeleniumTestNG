package util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;


public class ExcelUtils {

    static XSSFWorkbook myWorkBook;
    static XSSFSheet mySheet;
    static File myFile;
    static int rowCount;
    static String cellDataString;
    static double cellDataDouble;


   public ExcelUtils( String fileName, int sheetIndex){
        try {
            myFile = new File(fileName);
            FileInputStream fis = new FileInputStream(myFile);
            myWorkBook = new XSSFWorkbook(fis);
            mySheet = myWorkBook.getSheetAt(sheetIndex);
            //XSSFSheet mySheet = myWorkBook.getSheet("Sheet1");


        }catch (Exception exp){

        }


   }

    public static int getRowCount() {

        try {
            rowCount = mySheet.getPhysicalNumberOfRows();
            //System.out.println("No of row: " + rowCount);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
        }
        return rowCount;

    }

    public static int getColCount() {

        try {

            rowCount = mySheet.getRow(0).getPhysicalNumberOfCells();
            //System.out.println("No of row: " + colCount);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
        }
        return rowCount;

    }

    public static String getCellDataString(int row, int cellIndex){

        try {

            cellDataString = mySheet.getRow(row).getCell(cellIndex).getStringCellValue();
            //System.out.println("cellData: " + cellDataString);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
        }

        return cellDataString;
    }

    public static double getCellDataNumber(int row, int cellIndex){

        try {

            cellDataDouble = mySheet.getRow(row).getCell(cellIndex).getNumericCellValue();
            // System.out.println("cellData: " + cellDataDouble);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
        }

        return cellDataDouble;
    }



}
