package com.test.configuration;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import com.test.exceptions.StopTestException;

public class PageObjectModelInjectionListener implements InjectionListener {
    private Provider<Injector> injector;

    public PageObjectModelInjectionListener(Provider<Injector> injector) {
        this.injector = injector;
    }

    public void afterInjection(Object page) {
        PageFactory pageFactory = injector.get().getInstance(PageFactory.class);
        try {
            pageFactory.getPage(String.valueOf(page));
        } catch (StopTestException e) {
            e.printStackTrace();
        }

    }
}
