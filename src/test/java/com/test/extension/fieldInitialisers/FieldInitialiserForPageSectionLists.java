package com.test.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.test.extension.PageFactory;
import com.test.annotations.Section;
import com.test.extension.dependencies.DependencyInjector;
import com.test.exceptions.PageFactoryError;
import com.test.handlers.PageSectionListHandler;
import com.test.helper.helpers.FrameWrapper;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

public class FieldInitialiserForPageSectionLists implements FieldInitialiser {

    @Inject
    private DependencyInjector dependencyInjector = null;

    @Inject
    private Provider<PageFactory> pageFactory = null;

    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForPageSectionLists() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageSectionList(field)) {
            return false;
        }

        ParameterizedType genericTypeImpl = (ParameterizedType)field.getGenericType();
        Type genericTypeArgument = genericTypeImpl.getActualTypeArguments()[0];
        Annotations annotations = new Annotations(field);
        PageSectionListHandler pageSectionListHandler = new PageSectionListHandler(this.dependencyInjector, searchContext, annotations.buildBy(), genericTypeArgument, this.pageFactory, frame, this.webDriverFrameSwitchingOrchestrator);
        Object proxyInstance = Proxy.newProxyInstance(Section.class.getClassLoader(), new Class[]{List.class}, pageSectionListHandler);
        field.setAccessible(true);

        try {
            field.set(page, proxyInstance);
        } catch (IllegalAccessException var12) {
            throw new PageFactoryError(var12.getCause());
        }

        return true;
    }
}
