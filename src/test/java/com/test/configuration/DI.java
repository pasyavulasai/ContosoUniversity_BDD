package com.test.configuration;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.test.annotations.StaticInjection;
import com.test.exceptions.StopTestException;
import com.test.helper.testdata.FileBasedTestDataProvider;
import com.test.helper.testdata.TestDataProvider;
import com.test.extension.dependencies.DependencyInjector;
import com.test.extension.dependencies.InjectionError;
import cucumber.api.guice.CucumberModules;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import cucumber.runtime.java.guice.InjectorSource;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class DI extends AbstractModule implements InjectorSource,DependencyInjector {
    private Injector injector;

    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(WebDriverProvider.class)                .in(ScenarioScoped.class);
        bind(Configuration.class).toProvider(ConfigurationProvider.class)
                .in(ScenarioScoped.class);
        bind(PropertyConfiguration.class).toProvider(PropertyConfigurationProvider.class)
                .in(ScenarioScoped.class);
        bind(TestDataProvider.class).to(FileBasedTestDataProvider.class).in(ScenarioScoped.class);

        ConfigurationBuilder reflectionsConfiguration = new ConfigurationBuilder()
                .forPackages("com.test")
                .setScanners(new MethodAnnotationsScanner(),
                        new SubTypesScanner(), new TypeAnnotationsScanner());

        Reflections reflections = new Reflections(reflectionsConfiguration);

        setAllTestClassesInScenarioScope(reflections);
        setupStaticInjection(reflections);

        // PageObjectModelTypeListener interrogates each type to see if it's a page object.
        // It does this by looking at field types, if it's find any of PageElement,
        bindListener(Matchers.any(), new PageObjectModelTypeListener());
       Names.bindProperties(binder(), getProperties());
    }

    @Provides
    @ScenarioScoped
    private com.test.extension.PageFactory pageFactory() {
        return new com.test.extension.PageFactory(this);
    }

    private void setupStaticInjection(Reflections reflections) {
        // Find all classes that require static injection based on the annotation
        for (Class<?> aClass : reflections
                .getTypesAnnotatedWith(StaticInjection.class)) {
            requestStaticInjection(aClass);
        }
    }

    private void setAllTestClassesInScenarioScope(Reflections reflections) {
        ArrayList<Class<?>> scenarioScopedClasses = new ArrayList<Class<?>>();

        // All classes containing methods annotated with Cucumber keywords
        Class<? extends Annotation>[] cucumberKeywordClasses = new Class[] {
                Given.class, When.class, Then.class, And.class, But.class,
                Before.class, After.class };
        for (Class<? extends Annotation> cucumberKeywordClass : cucumberKeywordClasses) {
            for (Method method : reflections
                    .getMethodsAnnotatedWith(cucumberKeywordClass)) {
                if (!scenarioScopedClasses.contains(method.getDeclaringClass())) {
                    scenarioScopedClasses.add(method.getDeclaringClass());
                }
            }
        }

        // Mark all of the above with ScenarioScoped
        for (Class<?> klass : scenarioScopedClasses) {
            bind(klass).in(ScenarioScoped.class);
        }
    }

    private Properties getProperties() {

        try {
            Properties legacyProperties = Configuration
                    .getConfiguration()
                    .getProperties();

            legacyProperties.putAll(new TestExecutionSystemProperties()
                    .getProperties());
            return legacyProperties;

        } catch (StopTestException e) {
            e.printStackTrace();
        }
//        If we fail to load properties from configuration, we'll return system properties only
        return System.getProperties();
    }

    public Injector getInjector() {
        if (this.injector == null) {
            injector = Guice.createInjector(
                    Stage.PRODUCTION,
                    CucumberModules.SCENARIO,
                    this
            );
        }
        return injector;
    }

    @Override
    public <T> T get(Class<T> aClass) throws InjectionError {
        return getInjector().getInstance(aClass);
    }


}
