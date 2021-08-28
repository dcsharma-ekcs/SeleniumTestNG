package com.copperchips;

import util.ExcelUtils;



public class TestDataFrom_Excel {

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String fileName = projectPath + "/excel/file_example_XLSX_10.xlsx";
        int sheetIndex = 0;
        ExcelUtils excelUtils = new ExcelUtils(fileName,sheetIndex);

        int rowCount = excelUtils.getRowCount();
        System.out.println("rowCount : " + rowCount);

        String cellDataString = excelUtils.getCellDataString(1,1);
        System.out.println("cellData : " + cellDataString);

        double cellDataDouble = excelUtils.getCellDataNumber(1,0);
        System.out.println("cellData : " + cellDataDouble);
    }
}
