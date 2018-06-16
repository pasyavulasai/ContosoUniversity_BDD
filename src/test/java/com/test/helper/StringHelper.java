package com.test.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class contains helper methods to manipulate with dates
 *
 */
public final class StringHelper {

    public static boolean isBlankOrNull(String string){
        return string == null || string.trim().isEmpty();
    }


    /**
     * Remove new line character from a given string
     */
    public static String removeNewLine(String str) {
        if (str != null) {
            return str.replace('\n', ' ');
        } else {
            return null;
        }
    }

    /**
     * Remove all new lines from a given string
     */
    public static String removeNewLines(String str) {
        if (str != null && str.contains("\n")) {
            return str.replaceAll("\\n", "");
        } else {
            return str;
        }
    }

    /**
     * Replace all new lines from a given string
     */
    public static String replaceNewLinesWithSpaces(String str) {
        if (str != null && str.contains("\n")) {
            return str.replaceAll("\\n", " ");
        } else {
            return str;
        }
    }

    /**
     * Remove spaces from a given string
     */
    public static String removeSpaces(String str) {
        if (str != null) {
            return str.replaceAll(" ", "");
        } else {
            return null;
        }
    }

    /**
     * Remove full stops from a given string
     */
    public static String removeFullStops(String str) {
        if (str != null) {
            return str.replaceAll("\\.", "");
        } else {
            return null;
        }
    }

    /**
     * Remove commas from a given string
     */
    public static String removeCommas(String str) {
        if (str != null) {
            return str.replaceAll("\\,", "");
        } else {
            return null;
        }
    }

    /**
     * Remove Tabs from a given string
     */
    public static String removeTabs(String str) {
        if (str != null) {
            return str.replaceAll("\\t", "");
        } else {
            return null;
        }
    }

    /**
     * Remove everything but characters and numbers from strings
     */
    public static String removeAllSymbolsAndSpaces(String str) {
        if (str != null) {
            return str.replaceAll("\\W", "");
        } else {
            return null;
        }
    }

    /**
     * Return 1st character of a string as a string
     */
    public static String getFirstCharacter(String str) {
        if (str == "" || str == null) {
            return null;
        } else {

            return str.substring(0, 1);

        }
    }

    /**
     * Remove everything but numbers from a String
     */
    public static String removeAllSymbolsCharactersAndSpaces(String str) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) >= '0' && str.charAt(i) <= '9'))
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * This method will check that the String is not empty i. checking that it
     * neither null nor blank
     *
     * @return boolean
     */

    public static boolean notEmpty(String str) {
        String text = str.trim();
        return (text != null && text.length() > 0);

    }

    /**
     * Remove leading zero from a String which starts with zero
     */
    public static String removeLeadingZero(String str) {
        if (str != null && str.startsWith("0")) {
            return str.replaceFirst("0", "");
        } else {
            return str;
        }
    }

    /**
     * Split Date and Time on new line '\n'
     *
     * @return
     */
    public static String[] splitTextStringOnNewLine(String text) {
        if (text.contains("\n")) {
            String[] scheduledDepartDateTimeArray = text.split("\n");
            return scheduledDepartDateTimeArray;
        }
        return null;
    }

    /**
     * This method will take two strings one is complete string and another is
     * search string passed in. It will return remaining text after search
     * string.
     *
     * @return String strSuffix
     * completeString , searchString
     */
    public static String getStringAfterText(String completeString,
            String searchString) {

        /*
         * int strLength = searchString.length(); String strSuffix =
         * completeString.substring(strLength, completeString .length());
         */
        if (completeString.contains(searchString)) {
            int lastIndexOf = completeString.lastIndexOf(searchString);
            return completeString
                    .substring(lastIndexOf + searchString.length()).trim();
        } else {
            return ("Unable to find " + searchString + " in " + completeString);
        }

    }

    public static String getStringBeforeText(String completeString,
            String searchString) {
        if (completeString.contains(searchString)) {
            int indexOf = completeString.indexOf(searchString);
            return completeString.substring(0, indexOf).trim();
        } else {
            return ("Unable to find " + searchString + " in " + searchString);
        }

    }

    /**
     * This method simply extracts the number displayed inside any string The
     * string may consists of special characters, letters or anything other than
     * numbers and '.'
     *
     * @param subjectString
     * @return
     */
    public static String extractNumberFromString(String subjectString) {

        String returnString = "";
        returnString = subjectString.replaceAll("([^0-9^.])", "");

        return returnString;
    }

    /**
     * This method simply removes the number displayed inside any string The
     * string may consists of special characters, letters or anything
     *
     * @param subjectString
     * @return
     */
    public static String removeNumberFromString(String subjectString) {

        String returnString = null;
        if (subjectString == null || subjectString.equals("")) {
            returnString = "";
        } else {
            returnString = subjectString.replaceAll("[0-9]", "");
        }

        return returnString;
    }

    /**
     * Removes space from the Strings of a List
     *
     * @param paramList
     * @return
     */
    public static List<String> removeSpaceFromStringsOfList(
            List<String> paramList) {
        List<String> newStringList = new ArrayList<String>();

        for (int cnt = 0; cnt < paramList.size(); cnt++) {
            newStringList.add(StringHelper.removeSpaces(paramList.get(cnt)
                    .toString()));
        }
        return newStringList;

    }

    /**
     * The method will verify whether a integer exists in the supplied String
     *
     * @param subjectString
     * @return boolean
     */
    public static boolean isIntegerInString(String subjectString) {
        Pattern intsOnly = Pattern.compile("\\d+");
        Matcher makeMatch = intsOnly.matcher(subjectString);
        /*
         * makeMatch.find(); String inputInt = makeMatch.group();
         *
         */
        if (makeMatch.find())
            return true;
        else
            return false;
    }

    /**
     * Method to format the String into Named format (ex. subjectString:
     * january, resultingString: January)
     *
     * @param subjectString
     * @return String
     */
    public static String getNamedFormatString(String subjectString) {

        String firstDeli = subjectString.substring(0, 1);
        String secondDeli = subjectString.substring(1, subjectString.length());

        return firstDeli.toUpperCase() + secondDeli;
    }

    /**
     * Method to get the substring of length n from the end of a string.
     *
     * @param inputString     - String from which substring to be extracted.
     * @param subStringLength - int Desired length of the substring.
     * @return lastCharacters- String
     */
    public static String getLastnCharacters(String inputString,
            int subStringLength) {
        int length = inputString.length();
        if (length <= subStringLength) {
            return inputString;
        }
        int startIndex = length - subStringLength;
        return inputString.substring(startIndex);
    }

    /**
     * The method will return the integer from the supplied String
     *
     * @param subjectString
     * @return String
     */
    public static String getIntegerInString(String subjectString) {
        Pattern intsOnly = Pattern.compile("\\d+");
        Matcher makeMatch = intsOnly.matcher(subjectString);
        makeMatch.find();
        String inputInt = makeMatch.group();

        return inputInt;
    }

    /**
     * This method will take two character and return a substring within a give
     * search string.
     *
     * @param completeString
     * @param startChar
     * @param endChar
     * @return String substring of the original search string
     */
    public static String getStringBetweenChars(String completeString,
            String startChar, String endChar) {

        int startPoint, endPoint;

        try {
            startPoint = completeString.indexOf(startChar) + 1;
            endPoint = completeString.indexOf(endChar, startPoint);

            return completeString.substring(startPoint, endPoint);

        } catch (Exception e) {

            return "Error in getStringBetweenChars() " + startChar + " or "
                    + endChar + " not found in " + completeString;

        }

    }

    /**
     * This method will pattern match and given string and a regular expression
     *
     * @param inputString
     * @return boolean Whether the pattern match is successful or not
     */
    public static boolean regExMatcher(String inputString, String reExPattern) {

        Matcher match = null;

        Pattern stringMatch = Pattern.compile(reExPattern);
        match = stringMatch.matcher(inputString);

        if (match.find()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method will take two texts and return a substring within a give
     * search string.
     *
     * @param completeString
     * @param startText
     * @param endText
     * @return String substring of the original search string
     */
    public static String getStringBetweenTexts(String completeString,
            String startText, String endText) {

        int startPoint, endPoint;

        try {
            startPoint = completeString.indexOf(startText) + startText.length();
            endPoint = completeString.indexOf(endText);

            return completeString.substring(startPoint, endPoint);

        } catch (Exception e) {

            return "Error in getStringBetweenChars() " + startText + " or "
                    + endText + " not found in " + completeString;

        }

    }

    public static boolean isTitleValid(String title) {
        String[] validTitles = { "Mr", "Mrs", "Miss", "Mstr" };
        for (int x = 0; x < validTitles.length; x++) {
            if (validTitles[x].toUpperCase().equals(title.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reverse an array list of Strings
     *
     * @param original
     * @return
     */
    public ArrayList<String> getReversedArray(ArrayList<String> original) {
        ArrayList<String> copy = new ArrayList<String>(original);
        Collections.reverse(copy);
        return copy;
    }

    /**
     * Find a String is empty or N an array list of Strings
     *
     * @return
     */

    public static boolean isStringEmptyOrNull(String str) {

        if ("".equals(str) || str == null) {
            return true;
        }
        return false;
    }

    /**
     * Converts underscore string to camel case (starting with lowercase)
     *
     * @param string
     * @return String
     */
    static String toCamelCase(String string) {
        String[] parts = string.split("[_\\.]");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + firstLetterUpperCase(part);
        }
        return firstLetterLowerCase(camelCaseString);
    }

    /**
     * Converts provided string by setting first letter's case to uppercase
     *
     * @param string
     * @return String
     */
    static String firstLetterUpperCase(String string) {
        return string.substring(0, 1).toUpperCase() +
                string.toLowerCase().substring(1);
    }

    /**
     * Converts provided string by setting first letter's case to lowercase
     *
     * @param string
     * @return String
     */
    static String firstLetterLowerCase(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

}
