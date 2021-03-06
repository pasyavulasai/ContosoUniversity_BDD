package com.test.extension.dependencies;

import com.google.inject.AbstractModule;
import com.test.extension.fieldInitialisers.FieldInitialiser;
import com.test.helper.helpers.FieldInitialiserSort;
import com.test.helper.helpers.SortingHelper;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

class FieldInitialiserDependencyModule extends AbstractModule {

    private Reflections reflections;

    protected void configure() {
//        Multibinder fieldInitialiserMultibinder = Multibinder.newSetBinder(this.binder(), FieldInitialiser.class);
        Set fieldInitialisers = this.getReflections().getSubTypesOf(FieldInitialiser.class);
        List sortedInitialisers = SortingHelper.asSortedList(fieldInitialisers, new FieldInitialiserSort());
        Iterator var4 = sortedInitialisers.iterator();

        while(var4.hasNext()) {
            Class fieldInitialiserClass = (Class)var4.next();
//            fieldInitialiserMultibinder.addBinding().to(fieldInitialiserClass).in(Singleton.class);
        }

    }

    private Reflections getReflections() {
        if (this.reflections != null) {
            return this.reflections;
        } else {
            String packageName = FieldInitialiser.class.getPackage().getName();
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setScanners(new Scanner[]{new SubTypesScanner()});
            configurationBuilder.filterInputsBy((new FilterBuilder()).includePackage(new String[]{packageName}));
            configurationBuilder.setUrls(ClasspathHelper.forPackage(packageName, new ClassLoader[0]));
            this.reflections = new Reflections(configurationBuilder);
            return this.reflections;
        }
    }
}
