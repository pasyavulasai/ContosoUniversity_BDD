package com.test.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.test.extension.PageElement;
import com.test.extension.PageElementImpl;
import com.test.extension.PageFactory;
import com.test.extension.dependencies.DependencyInjector;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import com.test.handlers.WebElementHandler;
import com.test.helper.helpers.FrameWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

//import com.google.inject.Provider;

public class FieldInitialiserForPageSections implements FieldInitialiser {

    @Inject
    private DependencyInjector dependencyInjector = null;

    @Inject
    private Provider<PageFactory> pageFactory = null;

    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageSection(field)) {
            return false;
        }

        Annotations annotations = new Annotations(field);
        PageElement container = this.getPageElementProxy(annotations.buildBy(), searchContext, frame);

        try {
            Object pageSection = this.dependencyInjector.get(field.getType());
            this.pageFactory.get().initializeContainer(pageSection, container, frame);
            field.setAccessible(true);
            field.set(page, pageSection);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return true;

    }

    private PageElement getPageElementProxy(By by, SearchContext searchContext, FrameWrapper frame) {
        if (frame != null && frame.frameBy.equals(by)) {
            by = By.xpath("//*");
        }

        WebElementHandler elementHandler = new WebElementHandler(this.dependencyInjector, searchContext, by, frame, this.webDriverFrameSwitchingOrchestrator);
        WebElement proxyElement = (WebElement) Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, elementHandler);
        return new PageElementImpl(proxyElement);
    }
}

