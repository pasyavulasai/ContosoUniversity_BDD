package com.test.helper.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelUtils {
    String sheetName;
    final String USER_DIR= System.getProperty("user.dir");
    String Path = USER_DIR+"//src//test//resources//TestData//";
    public String workBookPath;
    String scenario;
    XSSFWorkbook xlsWorkbook;
    XSSFSheet xlsSheet;
    Cell xlsCell;
    Row xlsRow;
    FileInputStream fis;
    ArrayList<String> columnNames;

    public void upDateCellDataByColName( XSSFSheet sheet,int rowNum, String colName, String data, boolean makeBold) {
        try {
            readColumnHeadingsToList(sheet);
            int colNum = columnNames.indexOf(colName);
            updateCellData(sheet,rowNum, colNum, data, makeBold);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readColumnHeadingsToList(XSSFSheet sheetName) {
        try {
            columnNames = new ArrayList<String>();
            Row row = sheetName.getRow(0);
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                columnNames.add(cells.next().getStringCellValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveExcelWithChanges(String excelPath, XSSFWorkbook workbook) {
        try {
            FileOutputStream fos = new FileOutputStream(excelPath);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateCellData(XSSFSheet sheetName,int rowNum, int colNum, String data, boolean makebold) {
        try {
            xlsRow = sheetName.getRow(rowNum);
            if (xlsRow == null)
                xlsRow = sheetName.createRow(rowNum);
            xlsCell = xlsRow.getCell(colNum);
            if (xlsCell == null)
                xlsCell = xlsRow.createCell(colNum);
            xlsCell.setCellValue(data);
            if (makebold)
                makeCellBold(xlsCell);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void makeCellBold(Cell xlsCell) {
        XSSFFont font = xlsWorkbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        XSSFCellStyle style = xlsWorkbook.createCellStyle();
        style.setFont(font);
        xlsCell.setCellStyle(style);
    }

    public String setWorkBookPath(String sWorkBookName) {
        workBookPath = Path + sWorkBookName + ".xlsx";
        return workBookPath;
    }

    public ArrayList<Map<String, String>> getMapFromGivenExcel(String sWorkBookName, String sSheetName ) {
        setWorkBookPath(sWorkBookName);
        ArrayList<Map<String, String>> tdRows = getMap(workBookPath, sSheetName);
        System.out.println("Map from Excel initialised");
        return tdRows;
    }

    private ArrayList<Map<String, String>> getMapFromExcel(String sworkBook, String sheetName, String sTCName) {
        ArrayList<String> colNames = new ArrayList<>();
        ArrayList<Map<String, String>> mapArray = null;
        XSSFRow row = null;
        XSSFSheet sheet = null;
        int sheetRows = 0;
        int rowCols = 0;

        Map<String, String> rowMap = null;

        try {
            FileInputStream file = new FileInputStream(new File(sworkBook));
            XSSFWorkbook workBook = new XSSFWorkbook(file);
            sheet = workBook.getSheet(sheetName);
            sheetRows = sheet.getPhysicalNumberOfRows();
            mapArray = new ArrayList<Map<String, String>>(sheetRows - 1);
            row = sheet.getRow(0);

            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                colNames.add("" + row.getCell(i).getStringCellValue());
            }

            rowCols = colNames.size();
            System.out.println(rowCols);

            for (int i = 1; i < sheetRows; i++) {
                row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(sTCName) && row.getCell(2).getStringCellValue().equalsIgnoreCase("Y")) {
                    rowMap = new LinkedHashMap<String, String>(rowCols);

                    for (int c = 0; c < rowCols; c++) {
                        String key = colNames.get(c);
                        String value = "" + getCellValue(row.getCell(c));
                        rowMap.put(key, value);
                    }
                    mapArray.add(rowMap);
                    System.out.println(rowMap);
                }
            }
//            workBook.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapArray;
    }


    private ArrayList<Map<String, String>> getMap(String sworkBook, String sheetName) {
        ArrayList<String> colNames = new ArrayList<>();
        ArrayList<Map<String, String>> mapArray = null;
        XSSFRow row = null;
        XSSFSheet sheet = null;
        int sheetRows = 0;
        int rowCols = 0;

        Map<String, String> rowMap = null;

        try {
            FileInputStream file = new FileInputStream(new File(sworkBook));
            XSSFWorkbook workBook = new XSSFWorkbook(file);
            sheet = workBook.getSheet(sheetName);
            sheetRows = sheet.getPhysicalNumberOfRows();
            mapArray = new ArrayList<Map<String, String>>(sheetRows - 1);

            row = sheet.getRow(0);

            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                colNames.add("" + row.getCell(i).getStringCellValue());
            }

            rowCols = colNames.size();
            //System.out.println(rowCols);

            for (int i = 1; i < sheetRows; i++) {
                row = sheet.getRow(i);
                //int colNum = columnNames.indexOf("Enabled");
                //if ( row.getCell(colNum).getStringCellValue().equalsIgnoreCase("Y")) {
                    rowMap = new LinkedHashMap<String, String>(rowCols);

                    for (int c = 0; c < rowCols; c++) {
                        String key = colNames.get(c);
                        //System.out.println(c);
                        String valueFromCell =getCellValue(row.getCell(c));
                        String value= new StringBuilder(String.valueOf(valueFromCell)).append("").toString();
                        rowMap.put(key, value);
                    }
                    mapArray.add(rowMap);
                    //System.out.println(rowMap);
                //}
            }
//            workBook.close();
            file.close();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return mapArray;
    }

    private String getCellValue(XSSFCell cell) {
        String value = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                value = "" + cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                double floating_value = cell.getNumericCellValue();
                double fractionalPart = floating_value % 1;
                long integralPart = (long) Math.floor(floating_value);
                if (fractionalPart >= 0.0001)
                    value = "" + floating_value;
                else
                    value = "" + integralPart;
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;


        }
        return value;
    }


    public  void updateExcel(String sWorkBookName, String sheetName, int rowNo, String columnName, String value) throws Exception {
        setWorkBookPath(sWorkBookName);
        updateExcel(sheetName,rowNo,columnName,value);
    }

    public void updateExcel(String sheetName, int rowNo, String columnName, String value) throws Exception {
        FileOutputStream outFile = null;
        FileInputStream fis = null;
        String filePath = workBookPath;

        try {
            fis = new FileInputStream(new File(filePath));

            final XSSFWorkbook workbook = new XSSFWorkbook(fis);
            final XSSFSheet sheet = workbook.getSheet(sheetName);

            int endRow = sheet.getLastRowNum();
            int startRow = sheet.getFirstRowNum();

            XSSFRow headerRow = sheet.getRow(startRow);
            //System.out.println("Row Number" + rowNo);
            Iterator<Cell> itr = headerRow.iterator();
            while (itr.hasNext()) {
                Cell cell = itr.next();

                //System.out.println(cell.getStringCellValue());
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {

                    int colNo = cell.getColumnIndex();
                    //System.out.println("   Updating " + columnName + " at col No" + colNo + " in Excel...");
                    sheet.getRow(rowNo).getCell(colNo, Row.CREATE_NULL_AS_BLANK).setCellValue(new String(value));
                    //System.out.println("   " + columnName + " = " + value);

                }
            }
            fis.close();
            outFile = new FileOutputStream(new File(filePath));
            workbook.write(outFile);
            outFile.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
            throw e;
        } catch (IOException ie) {
            ie.getMessage();
            throw ie;
        }
    }

    public HashMap<String, String> readRowDataWithGivenCellValue(String WorkBookPath,String sheetName,String columnName,String expectedCellValue) {
        HashMap<String, String> rowDataMap= new HashMap<String, String>();
        try {
            fis= new FileInputStream(WorkBookPath);
            xlsWorkbook= new XSSFWorkbook(fis);
            xlsSheet=xlsWorkbook.getSheet(sheetName);
            int rowCount = xlsSheet.getPhysicalNumberOfRows();
            readColumnHeadingsToList(xlsSheet);
            int colNumWithRequiredValue = columnNames.indexOf(columnName);

            for(int rowNum=1;rowNum<rowCount;rowCount++) {
                xlsRow= xlsSheet.getRow(rowNum);
                if(xlsRow.getCell(colNumWithRequiredValue).getStringCellValue().equals(expectedCellValue)) {
                    int totalColumnCount = xlsRow.getPhysicalNumberOfCells();
                    for(int colNum= 0;colNum<totalColumnCount;colNum++) {
                        String colHeader= xlsSheet.getRow(0).getCell(colNum).getStringCellValue().toString();
                        //System.out.println("rowNum=  "+rowNum+"colNum= "+colNum);
                        DataFormatter formatter = new DataFormatter();
                        XSSFCell cell= xlsSheet.getRow(rowNum).getCell(colNum);
                        String stringCellValue = formatter.formatCellValue(cell);
                        //String stringCellValue = xlsSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
                        rowDataMap.put(colHeader,stringCellValue);
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowDataMap;
    }

    public String getDataFromExcel(String WorkBookPath,String sheetName,String columnName,String expectedCellValue,String returnIdentifiedCol)
    {
        HashMap<String,String> rowData = readRowDataWithGivenCellValue(WorkBookPath,sheetName,columnName,expectedCellValue);
        return rowData.get(returnIdentifiedCol);
    }


    public int getRowNoWithGivenCellValue(String sWorkBookName,String sheetName,String columnName,String expectedCellValue) {
        setWorkBookPath(sWorkBookName);
        HashMap<String, String> rowDataMap= new HashMap<String, String>();
        int index=0;
        try {
            fis= new FileInputStream(workBookPath);
            xlsWorkbook= new XSSFWorkbook(fis);
            xlsSheet=xlsWorkbook.getSheet(sheetName);
            int rowCount = xlsSheet.getPhysicalNumberOfRows();
            readColumnHeadingsToList(xlsSheet);
            int colNumWithRequiredValue = columnNames.indexOf(columnName);

            for(int rowNum=1;rowNum<rowCount;rowCount++) {
                xlsRow= xlsSheet.getRow(rowNum);
                if(xlsRow.getCell(colNumWithRequiredValue).getStringCellValue().equals(expectedCellValue)) {
                    index= rowNum;

                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }

}


