package com.test.helper.utils;

import com.google.inject.Inject;
import com.test.ui.CommonVariables;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class CommonFunctions {


    SimpleDateFormat sdf;
    Calendar cal;
    String strDate;

    @Inject
    private CommonVariables cmnVrbls;

    private String pageTitle;


//    public String getCurrentDate() {
//        cal = Calendar.getInstance();
//        sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
//        strDate = sdf.format(cal.getTime());
//        System.out.println("Current date in String Format: " + strDate);
//        return strDate;
//    }
//
//    public String getCurrentDate(String expectedDateFormat) {
//        cal = Calendar.getInstance();
//        sdf = new SimpleDateFormat(expectedDateFormat);
//        strDate = sdf.format(cal.getTime());
//        System.out.println("Current date in String Format: " + strDate);
//        return strDate;
//    }
//
//    public String getFutureDate(String expectedDateFormat) {
//        cal = Calendar.getInstance();
//        sdf = new SimpleDateFormat(expectedDateFormat);
//        cal.setTime(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        Date futureDate = cal.getTime();
//        strDate = sdf.format(futureDate);
//        System.out.println("Current date in String Format: " + strDate);
//        return strDate;
//    }

//    public String getMonthOldPastDate(String expectedDateFormat) {
//        cal = Calendar.getInstance();
//        sdf = new SimpleDateFormat(expectedDateFormat);
//        cal.setTime(cal.getTime());
//        cal.add(Calendar.MONTH, -1);
//        Date pastDate = cal.getTime();
//        strDate = sdf.format(pastDate);
//        System.out.println("Current date in String Format: " + strDate);
//        return strDate;
//    }


//    public int getCurrentDayPlusOneDay() {
//        return( Integer.parseInt(getFutureDate("dd")));
//    }
//
//    public int getCurrentHour() {
//        return( Integer.parseInt(getCurrentDate("HH")));
//    }
//
//    public int getCurrentMin() {
//        return( Integer.parseInt(getCurrentDate("mm")));
//    }
//
//    public int getCurrentMonthPlusOneDay() {
//        return( Integer.parseInt(getFutureDate("MM")));
//    }
//
//    public int getCurrentYearPlusOneDay() {
//        return( Integer.parseInt(getFutureDate("YYYY")));
//    }
//
//    public String getTodaysDay() {
//
//        return (getCurrentDate("dd"));
//    }

//    public String getTodaysMonth() {
//
//        return (getCurrentDate("MM"));
//    }
//
//    public String getTodaysYear() {
//
//        return (getCurrentDate("YYYY"));
//    }
//
//    public String getMonthOldDay() {
//
//        return (getMonthOldPastDate("dd"));
//    }
//
//    public String getMonthOldMonth() {
//
//        return (getMonthOldPastDate("MM"));
//    }
//
//    public String getMonthOldYear() {
//
//        return (getMonthOldPastDate("YYYY"));
//    }

//    public int getDigitFromString(String str) {
//        return Integer.parseInt(str.replaceAll("\\D+", ""));
//    }
//
//    public String getRandomString(int length) {
//        return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(length).toUpperCase();
//
//    }
//
//    public String getRandomNumber(int length) {
//        return org.apache.commons.lang3.RandomStringUtils.randomNumeric(length);
//
//    }
//
//    public String getScenarioNumber(String scenarioName) {
//        // TODO: 10/11/2017 will modify the method to say 1.1 Scenario . will find the XLS rownumber based on string captured from scenarioname
//        return (scenarioName.split("-",2)[0]);
//    }
//
//    public String splitStringOnFirstOccurenceOfAnotherPattern(String string,String pattern) {
//
//        return (string.split(pattern,2)[0]);
//    }

//    public String splitStringOnFirstOccurenceOfAnotherPattern(String string,String pattern,int integer) {
//
//        return (string.split(pattern,2)[integer]);
//    }
//
//
//    public void test() {
//        System.out.println(getScenarioNumber("1-Scenario is"));
//    }
//
//
//    public String replaceString(String orignalString, String templateStringToReplace, String toBeReplaceWithString ) {
//        return orignalString.replace(templateStringToReplace,toBeReplaceWithString);
//
//    }
//    public String replaceTemplateString(String orignalString, String toBeReplaceWithString ) {
//        return orignalString.replace("TEMPLATE",toBeReplaceWithString);
//
//    }
//
//    public String convertString(Integer intVrbl ) {
//        return String.valueOf(intVrbl);
//
//    }

//    public int convertInt(String stringVrbl ) {
//        return Integer.parseInt(stringVrbl);
//
//    }
//    public String getSubString(String stringVrbl, int startIndex,int endindex ) {
//        return stringVrbl.substring(startIndex, endindex);
//
//    }
//
//    public int getRowIndex(ArrayList<Map<String,String>> arrayList, String stringToMatch ) {
//        int index = -1;
//        for(int i=0;i<arrayList.size();i++) {
//            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//    }

//    public String IdentifyIndexOfGivenValue( String string,String IndexToFind) {
//        int index =getDigitFromString(IndexToFind);
//
//        return String.valueOf(string.charAt(index-1));
//
//    }
//
//    public boolean isStringNotEqualTo(String stringOne, String stringTwo ) {
//        return !stringOne.equals(stringTwo);
//    }
//
//    public int getRowIndexNew(ArrayList<Map<String, String>> arrayList, String stringToMatch, String environment) {
//        int x=-1;
//        if (environment.equals("Prod")) {
//            x= getProdIndex(arrayList,stringToMatch);
//        } else if (environment.equals("Pre")) {
//            x= getPreIndex(arrayList,stringToMatch);
//        }
//        return x;
//    }

//    private int getProdIndex(ArrayList<Map<String, String>> arrayList, String stringToMatch) {
//        int index=-1;
//
//        boolean areAllValuesChecked = areAllValuesChecked(arrayList);
//        Assert.assertTrue(areAllValuesChecked);
//        for(int i=0;i<arrayList.size();i++) {
//            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//
//    }

//    public boolean areAllValuesChecked(ArrayList<Map<String, String>> arrayList) {
//        boolean areAllValuesTrue = false;
//        //String productionShee= "ProductionSheet";
//        for (Map<String, String> anArrayList : arrayList) {
//            if (anArrayList.get("ProductionSheet").equals("Yes")) {
//                areAllValuesTrue = true;
//
//            } else
//                return false;
//        }
//
//        return areAllValuesTrue;
//    }
//
//
//    private int getPreIndex(ArrayList<Map<String, String>> arrayList, String stringToMatch) {
//
//        int index=-1;
//        for(int i=0;i<arrayList.size();i++) {
//            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//
//    }


    public String getCurrentDate() {
    cal = Calendar.getInstance();
    sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
    strDate = sdf.format(cal.getTime());
    System.out.println("Current date in String Format: " + strDate);
    return strDate;
}

    public String getCurrentDate(String expectedDateFormat) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(expectedDateFormat);
        strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }

    public String getFutureDate(String expectedDateFormat) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(expectedDateFormat);
        cal.setTime(cal.getTime());
        cal.add(Calendar.DATE, 1);
        Date futureDate = cal.getTime();
        strDate = sdf.format(futureDate);
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }

    public String getMonthOldPastDate(String expectedDateFormat) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(expectedDateFormat);
        cal.setTime(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        Date pastDate = cal.getTime();
        strDate = sdf.format(pastDate);
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }


    public int getCurrentDayPlusOneDay() {
        return( Integer.parseInt(getFutureDate("dd")));
    }

    public int getCurrentHour() {
        return( Integer.parseInt(getCurrentDate("HH")));
    }

    public int getCurrentMin() {
        return( Integer.parseInt(getCurrentDate("mm")));
    }

    public int getCurrentMonthPlusOneDay() {
        return( Integer.parseInt(getFutureDate("MM")));
    }

    public int getCurrentYearPlusOneDay() {
        return( Integer.parseInt(getFutureDate("YYYY")));
    }

    public String getTodaysDay() {

        return (getCurrentDate("dd"));
    }

    public String getTodaysMonth() {

        return (getCurrentDate("MM"));
    }

    public String getTodaysYear() {

        return (getCurrentDate("YYYY"));
    }

    public String getMonthOldDay() {

        return (getMonthOldPastDate("dd"));
    }

    public String getMonthOldMonth() {

        return (getMonthOldPastDate("MM"));
    }

    public String getMonthOldYear() {

        return (getMonthOldPastDate("YYYY"));
    }

    public int getDigitFromString(String str) {
        return Integer.parseInt(str.replaceAll("\\D+", ""));
    }

    public String getRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();

    }

    public String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);

    }

    public String getScenarioNumber(String scenarioName) {
        // TODO: 10/11/2017 will modify the method to say 1.1 Scenario . will find the XLS rownumber based on string captured from scenarioname  
        return (scenarioName.split("-",2)[0]);
    }

    public String splitStringOnFirstOccurenceOfAnotherPattern(String string,String pattern) {

        return (string.split(pattern,2)[0]);
    }

    public String splitStringOnFirstOccurenceOfAnotherPattern(String string,String pattern,int integer) {

        return (string.split(pattern,2)[integer]);
    }


    public void test() {
        System.out.println(getScenarioNumber("1-Scenario is"));
    }


    public String replaceString(String orignalString, String templateStringToReplace, String toBeReplaceWithString ) {
        return orignalString.replace(templateStringToReplace,toBeReplaceWithString);

    }
    public String replaceTemplateString(String orignalString, String toBeReplaceWithString ) {
        return orignalString.replace("TEMPLATE",toBeReplaceWithString);

    }

    public String convertString(Integer intVrbl ) {
        return String.valueOf(intVrbl);

    }

    public int convertInt(String stringVrbl ) {
        return Integer.parseInt(stringVrbl);

    }
    public String getSubString(String stringVrbl, int startIndex,int endindex ) {
        return stringVrbl.substring(startIndex, endindex);

    }

    public int getRowIndex(ArrayList<Map<String,String>> arrayList, String stringToMatch ) {
        int index = -1;
        for(int i=0;i<arrayList.size();i++) {
            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public String IdentifyIndexOfGivenValue( String string,String IndexToFind) {
        int index =getDigitFromString(IndexToFind);
        return String.valueOf(string.charAt(index-1));
    }

    public boolean isStringNotEqualTo(String stringOne, String stringTwo ) {
        return !stringOne.equals(stringTwo);
    }

    public int getRowIndexNew(ArrayList<Map<String, String>> arrayList, String stringToMatch, String environment) {
        int x=-1;
        if (environment.equals("Prod")) {
            x= getProdIndex(arrayList,stringToMatch);
        } else if (environment.equals("Pre")) {
            x= getPreIndex(arrayList,stringToMatch);
        }
        return x;
    }

    private int getProdIndex(ArrayList<Map<String, String>> arrayList, String stringToMatch) {
        int index=-1;

        boolean areAllValuesChecked = areAllValuesChecked(arrayList);
        Assert.assertTrue(areAllValuesChecked);
        for(int i=0;i<arrayList.size();i++) {
            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
                index = i;
                break;
            }
        }
        return index;

    }

    public boolean areAllValuesChecked(ArrayList<Map<String, String>> arrayList) {
        boolean areAllValuesTrue = false;
        //String productionShee= "ProductionSheet";
        for (Map<String, String> anArrayList : arrayList) {
            if (anArrayList.get("ProductionSheet").equals("Yes")) {
                areAllValuesTrue = true;

            } else
                return false;
        }
        return areAllValuesTrue;
    }


    private int getPreIndex(ArrayList<Map<String, String>> arrayList, String stringToMatch) {
        int index=-1;
        for(int i=0;i<arrayList.size();i++) {
            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
                index = i;
                break;
            }
        }
        return index;
    }



}
