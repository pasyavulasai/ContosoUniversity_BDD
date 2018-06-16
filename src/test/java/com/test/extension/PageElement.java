package com.test.extension;

import com.test.handlers.DynamicHandler;
import com.test.handlers.Refreshable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

public interface PageElement extends WebElement, Locatable, WebElementExtensions, WrapsElement, SearchContext, DynamicHandler, Refreshable {
}
