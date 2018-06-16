package com.test.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.test.extension.dependencies.DependencyInjector;
import com.test.exceptions.PageFactoryError;
import com.test.handlers.PageElementListHandler;
import com.test.helper.helpers.FrameWrapper;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;

public class FieldInitialiserForPageElementLists implements FieldInitialiser {

    @Inject
    private DependencyInjector dependencyInjector = null;

    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForPageElementLists() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageElementList(field)) {
            return false;
        }

        Annotations annotations = new Annotations(field);
        PageElementListHandler elementListHandler = new PageElementListHandler(this.dependencyInjector, searchContext, annotations.buildBy(), frame, this.webDriverFrameSwitchingOrchestrator);
        List webElementListProxy = (List) Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{List.class}, elementListHandler);

        try {
            field.setAccessible(true);
            field.set(page, webElementListProxy);
        } catch (IllegalAccessException var9) {
            throw new PageFactoryError(var9.getCause());
        }

        return true;
    }
}
