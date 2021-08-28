package com.copperchips;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import static util.ExcelUtils.*;

public class RearExcelFile {

    static String projectPath;
    static XSSFWorkbook myWorkBook;
    static XSSFSheet mySheet;

    public static void main(String[] args) throws IOException {
       

        try {
            projectPath = System.getProperty("user.dir");
            System.out.println(projectPath);
            File myFile = new File(projectPath + "/excel/file_example_XLSX_10.xlsx");
            FileInputStream fis = new FileInputStream(myFile);

            myWorkBook = new XSSFWorkbook(fis);
            mySheet = myWorkBook.getSheetAt(0);
            //XSSFSheet mySheet = myWorkBook.getSheet("Sheet1");

            int rowCount = mySheet.getPhysicalNumberOfRows();
            System.out.println("No of row: " + rowCount);
            Iterator<Row> rowIterator = mySheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    //System.out.print(cell.getStringCellValue() + "\t");

                }

            }
            System.out.println("");

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
        }


    }
}

