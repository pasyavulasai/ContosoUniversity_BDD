package com.test.handlers;

public interface Refreshable {

    void invalidate();

    void refresh();

    void setParent(Refreshable var1);
}
