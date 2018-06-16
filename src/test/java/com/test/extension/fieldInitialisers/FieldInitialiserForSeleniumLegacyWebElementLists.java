package com.test.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.test.extension.ElementListImpl;
import com.test.extension.PageElement;
import com.test.extension.dependencies.DependencyInjector;
import com.test.exceptions.PageFactoryError;
import com.test.handlers.ElementListHandler;
import com.test.handlers.WebElementListHandler;
import com.test.helper.helpers.FrameWrapper;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public class FieldInitialiserForSeleniumLegacyWebElementLists implements FieldInitialiser {

    @Inject
    private DependencyInjector dependencyInjector = null;

    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidWebElementList(field)) {
            return false;
        }

        Annotations annotations = new Annotations(field);
        WebElementListHandler elementListHandler = new WebElementListHandler(this.dependencyInjector, searchContext, annotations.buildBy(), frame, this.webDriverFrameSwitchingOrchestrator);
        List webElementListProxy = (List) Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{List.class}, elementListHandler);
        ElementListImpl webElementListExtensions = new ElementListImpl(searchContext, webElementListProxy);
        InvocationHandler pageElementHandler = new ElementListHandler(webElementListProxy, webElementListExtensions);
        List pageElementListProxy = (List)Proxy.newProxyInstance(PageElement.class.getClassLoader(), new Class[]{List.class}, pageElementHandler);

        try {
            field.setAccessible(true);
            field.set(page, pageElementListProxy);
        } catch (IllegalAccessException var12) {
            throw new PageFactoryError(var12.getCause());
        }

        return true;
    }
}
