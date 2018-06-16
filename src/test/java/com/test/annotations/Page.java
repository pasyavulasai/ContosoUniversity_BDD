package com.test.annotations;

import com.test.helper.NullPageIdHelper;
import com.test.helper.PageIdHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// DEMO custom annotation for specifying relative page paths and page ids
@Retention(RetentionPolicy.RUNTIME)
public @interface Page {
    String id() default "";
    String path() default "";
    String title() default "";
    Class<? extends PageIdHelper> pageIdDelegate() default NullPageIdHelper.class;
}
