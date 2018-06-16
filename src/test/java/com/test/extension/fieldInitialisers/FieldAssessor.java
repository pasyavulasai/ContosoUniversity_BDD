package com.test.extension.fieldInitialisers;

import com.test.extension.PageElement;
import com.test.extension.PageSection;
import com.test.annotations.Section;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class FieldAssessor {

    public static boolean isSeleniumPomListField(Field field) {
        return isValidPageElementList(field) || isValidWebElementList(field) || isValidPageSectionList(field);
    }

    public static boolean isSeleniumPomNonListField(Field field) {
        return isValidPageElement(field) || isValidPageSection(field) || isValidWebElement(field);
    }

    public static boolean isSeleniumPomField(Field field) {
        return isValidPageElement(field) || isValidPageSection(field) || isValidPageElementList(field) || isValidPageSectionList(field) || isValidWebElement(field) || isValidWebElementList(field);
    }

    static boolean isValidPageElementList(Field field) {
        Class<?> fieldType = field.getType();
        if (!List.class.isAssignableFrom(fieldType)) {
            return false;
        }

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        ParameterizedType genericTypeImpl = (ParameterizedType)genericType;
        if (!(genericTypeImpl.getActualTypeArguments()[0] instanceof Class)) {
            return false;
        }

        return PageElement.class.isAssignableFrom((Class)genericTypeImpl.getActualTypeArguments()[0]);
    }

    static boolean isValidPageElement(Field field) {
        return PageElement.class.isAssignableFrom(field.getType());
    }

    static boolean isValidPageSectionList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        } else if (field.isAnnotationPresent(Section.class)) {
            return true;
        } else {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return false;
            }

            ParameterizedType genericTypeImpl = (ParameterizedType)genericType;
            if (!(genericTypeImpl.getActualTypeArguments()[0] instanceof Class)) {
                return false;
            }

            Class<?> genericTypeArgument = (Class)genericTypeImpl.getActualTypeArguments()[0];
            if (PageElement.class.isAssignableFrom(genericTypeArgument)) {
                return false;
            } else if (WebElement.class.isAssignableFrom(genericTypeArgument)) {
                return false;
            } else if (PageSection.class.isAssignableFrom(genericTypeArgument)) {
                return true;
            } else {
                return hasSeleniumFindByAnnotation(field);
            }
        }
    }

    static boolean isValidPageSection(Field field) {
        Class<?> fieldType = field.getType();
        if (List.class.isAssignableFrom(fieldType)) {
            return false;
        } else if (PageElement.class.isAssignableFrom(fieldType)) {
            return false;
        } else if (WebElement.class.isAssignableFrom(fieldType)) {
            return false;
        } else if (field.isAnnotationPresent(Section.class)) {
            return true;
        } else if (PageSection.class.isAssignableFrom(fieldType)) {
            return true;
        } else {
            return hasSeleniumFindByAnnotation(field);
        }
    }

    static boolean isValidWebElement(Field field) {
        return !PageElement.class.isAssignableFrom(field.getType()) && WebElement.class.isAssignableFrom(field.getType());
    }

    static boolean isValidWebElementList(Field field) {
        Class<?> fieldType = field.getType();
        if (!List.class.isAssignableFrom(fieldType)) {
            return false;
        }

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        ParameterizedType genericTypeImpl = (ParameterizedType)genericType;
        if (!(genericTypeImpl.getActualTypeArguments()[0] instanceof Class)) {
            return false;
        }

        return WebElement.class.isAssignableFrom((Class)genericTypeImpl.getActualTypeArguments()[0]);
    }

    private static boolean hasSeleniumFindByAnnotation(Field field) {
        if (field.getAnnotation(FindBy.class) != null) {
            return true;
        } else if (field.getAnnotation(FindBys.class) != null) {
            return true;
        } else {
            return field.getAnnotation(FindAll.class) != null;
        }
    }
}

