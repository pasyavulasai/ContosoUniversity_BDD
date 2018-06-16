package com.test.helper.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c0250492 on 12/05/2017.
 */
public class ExcelReader {

    private Fillo fillo = null;
    private Connection con;
    //private Recordset recordset;
    private String strQuery;
    //Connection con;

    public ExcelReader(){
        Fillo fillo = new Fillo();
    }

    public Map<String, String> getScenarioData(String excelPath, String sheetName, String scenarioField, String scenarioName) throws FilloException{

        strQuery = "select "+scenarioField+" ,"+scenarioName+" from "+sheetName;

        con = xcelConnect(excelPath);
        Recordset recordset = con.executeQuery(strQuery);

        Map<String, String> sCollection= new HashMap<>();

        while (recordset.next())
            sCollection.put(recordset.getField(scenarioField), recordset.getField(scenarioName));

        recordset.close();
        con.close();

        return sCollection;
    }

    public void updateScenarioData(String excelPath, String sheetName, String scenarioField, String scenarioName, String setValue, String searchValue) throws FilloException{

        con = xcelConnect(excelPath);
        strQuery = "Update "+sheetName+" set "+scenarioName+" ='"+setValue+"' where "+scenarioField+"'"+searchValue+"'";
        con.executeUpdate(strQuery);
        con.close();
    }

    private Connection xcelConnect(String excelPath) throws FilloException {
        return fillo.getConnection(excelPath);
    }
}
