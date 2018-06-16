package com.test.helper.helpers;

import com.test.extension.fieldInitialisers.FieldInitialiser;

import java.util.Comparator;

public class FieldInitialiserSort implements Comparator<Class<? extends FieldInitialiser>> {

    public int compare(Class<? extends FieldInitialiser> aClass, Class<? extends FieldInitialiser> t1) {
        return aClass.getSimpleName().compareTo(t1.getSimpleName());
    }
}
