package com.test.configuration;

import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.test.annotations.Section;
import com.test.extension.PageElement;
import com.test.helper.helpers.ClassHelper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class PageObjectModelTypeListener implements TypeListener {
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        if (isPageObject(typeLiteral.getRawType())) {
            typeEncounter.register(new PageObjectModelInjectionListener(typeEncounter.getProvider(Injector.class)));
        }
    }

    private boolean isPageObject(Class<?> rawType) {
        for (Field field : ClassHelper.getFieldsFromClass(rawType)) {
            Class<?> type = field.getType();
            if (PageElement.class.isAssignableFrom(type)) {
                return true;
            }
            if (field.isAnnotationPresent(Section.class)) {
                return true;
            }

            if (List.class.isAssignableFrom(type)) {
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                    if (!(parameterizedType.getActualTypeArguments()[0] instanceof Class)) {
                        return false;
                    }
                    Class genericTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    if (PageElement.class.isAssignableFrom(genericTypeArgument)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
