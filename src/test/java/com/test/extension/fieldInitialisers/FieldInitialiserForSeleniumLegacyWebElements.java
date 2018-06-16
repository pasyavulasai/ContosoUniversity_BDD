package com.test.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.test.extension.dependencies.DependencyInjector;
import com.test.handlers.WebElementHandler;
import com.test.helper.helpers.FrameWrapper;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class FieldInitialiserForSeleniumLegacyWebElements implements FieldInitialiser {

    @Inject
    private DependencyInjector dependencyInjector = null;

    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidWebElement(field)) {
            return false;
        }

        Annotations annotations = new Annotations(field);

        try {
            WebElementHandler elementHandler = new WebElementHandler(this.dependencyInjector, searchContext, annotations.buildBy(), frame, this.webDriverFrameSwitchingOrchestrator);
            WebElement proxyElement = (WebElement) Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, elementHandler);
            field.setAccessible(true);
            field.set(page, proxyElement);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return true;
    }
}
