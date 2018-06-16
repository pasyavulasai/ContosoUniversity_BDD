package com.test.handlers;

import com.test.extension.PageElementImpl;
import com.test.configuration.Constants;
import com.test.extension.dependencies.DependencyInjector;
import com.test.helper.helpers.FrameWrapper;
import com.test.helper.helpers.ReflectionHelper;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PageElementListHandler implements InvocationHandler, Refreshable {

    private static final Logger LOG = Logger.getLogger(PageElementListHandler.class.getName());
    private DependencyInjector driver;
    private SearchContext searchContext;
    private By by;
    private final FrameWrapper frame;
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator;
    private ArrayList<WebElement> webElements;
    private boolean needsRefresh;

    public PageElementListHandler(DependencyInjector driver, SearchContext searchContext, By by, FrameWrapper frame, WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator) {
        this.driver = driver;
        this.searchContext = searchContext;
        this.by = by;
        this.frame = frame;
        this.webDriverFrameSwitchingOrchestrator = webDriverFrameSwitchingOrchestrator;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (this.webElements == null) {
            List<WebElement> elements = this.searchContext.findElements(this.by);
            this.webElements = new ArrayList<>();

            for (WebElement element : elements) {
                this.webElements.add(this.getPageElementProxy(element));
            }
        } else if (this.needsRefresh) {
            this.refresh();
            this.needsRefresh = false;
        }

        try {
            LOG.fine(String.format("Calling %s on %s", method.getName(), this));
            return method.invoke(this.webElements, args);
        } catch (InvocationTargetException var7) {
            LOG.fine(String.format("Error calling %s on %s", method.getName(), this));
            throw var7.getCause();
        }
    }

    private WebElement getPageElementProxy(WebElement webElement) {
        WebElementHandler handler = new WebElementHandler(this.driver, this.searchContext, By.id(Constants.DUMMY_PAGE_LOCATOR_FOR_LISTS), this.frame, this.webDriverFrameSwitchingOrchestrator, webElement);
        handler.setParent(this);
        WebElement webElementProxy = (WebElement) Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, handler);
        return new PageElementImpl(webElementProxy);
    }

    public void invalidate() {
        this.needsRefresh = true;
        if (this.webElements == null) {
            return;
        }

        for (WebElement element : webElements) {
            if (element instanceof PageElementImpl) {
                WebElement wrappedElement = ((PageElementImpl)element).getWrappedElement();
                if (wrappedElement instanceof Proxy) {
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(wrappedElement);
                    if (invocationHandler instanceof Refreshable) {
                        ((Refreshable)invocationHandler).invalidate();
                    }
                }
            }
        }
    }

    public void refresh() {
        if (this.webElements == null) {
            return;
        }

        List<WebElement> elements = this.searchContext.findElements(this.by);
        Field elementField = ReflectionHelper.getField(PageElementImpl.class, Constants.PAGE_ELEMENT_CONTAINER_FIELD_NAME);
        Field webElementInvocationHandlerWebElementField = ReflectionHelper.getField(WebElementHandler.class, Constants.PAGE_ELEMENT_CONTAINER_FIELD_NAME);

        assert webElementInvocationHandlerWebElementField != null;

        webElementInvocationHandlerWebElementField.setAccessible(true);

        assert elementField != null;

        elementField.setAccessible(true);

        int i;
        for(i = elements.size(); i < this.webElements.size(); ++i) {
            this.webElements.remove(i);
        }

        for(i = 0; i < elements.size(); ++i) {
            WebElement e = elements.get(i);
            if (this.webElements.size() == i) {
                this.webElements.add(this.getPageElementProxy(e));
            }

            Object s = this.webElements.get(i);

            try {
                Object webElementProxy = elementField.get(s);
                if (webElementProxy instanceof Proxy) {
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(webElementProxy);
                    if (invocationHandler instanceof WebElementHandler) {
                        webElementInvocationHandlerWebElementField.set(invocationHandler, e);
                    }
                }
            } catch (IllegalAccessException var9) {
                var9.printStackTrace();
            } catch (IllegalArgumentException var10) {
                var10.printStackTrace();
            }
        }
    }

    public void setParent(Refreshable refreshable) {
        Refreshable parent = refreshable;
    }
}

