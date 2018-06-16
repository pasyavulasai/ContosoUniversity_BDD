package com.test.extension.fieldInitialisers;

import com.test.helper.helpers.FrameWrapper;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

public interface FieldInitialiser {
    Boolean initialiseField(Field var1, Object var2, SearchContext var3, FrameWrapper var4);
}
