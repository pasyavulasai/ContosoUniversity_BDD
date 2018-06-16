package com.test.helper.helpers;

import java.util.*;

public class SortingHelper {

    private SortingHelper() {
    }

    public static <T> List<T> asSortedList(Collection<T> c, Comparator<T> comparator) {
        List<T> list = new ArrayList<>(c);
        Collections.sort(list, comparator);
        return list;
    }
}
