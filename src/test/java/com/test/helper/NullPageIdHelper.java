package com.test.helper;

public class NullPageIdHelper implements PageIdHelper {

    @Override
    public String getPageId() {
        throw new UnsupportedOperationException();
    }
}
