package com.test.helper.helpers;

public class ArrayHelper {

    private ArrayHelper() {
    }

    public static <T> String join(Iterable<T> aArr, String sSep) {
        StringBuilder sbStr = new StringBuilder();
        boolean first = true;

        for ( T value : aArr) {
            if (!first) {
                sbStr.append(sSep);
            }

            first = false;
            sbStr.append(value.toString());
        }

        return sbStr.toString();
    }
}
