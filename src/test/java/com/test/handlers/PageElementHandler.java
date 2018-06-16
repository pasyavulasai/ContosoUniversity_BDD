package com.test.handlers;

import com.test.extension.PageElementImpl;
import com.test.helper.helpers.FrameWrapper;
import com.test.extension.orchestration.WebDriverFrameSwitchingOrchestrator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

class PageElementHandler {

    private static final Logger LOG = Logger.getLogger(PageElementHandler.class.getName());
    private PageElementImpl pageElement;
    private FrameWrapper frame;
    private WebDriverFrameSwitchingOrchestrator webDriverOrchestrator;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            this.webDriverOrchestrator.useFrame(this.frame);
            return this.pageElement.canHandle(method)?method.invoke(this.pageElement, args):method.invoke(proxy, args);
        } catch (InvocationTargetException var5) {
            throw var5.getCause();
        }
    }
}
