package com.test.helper;

import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.ArrayList;
import java.util.List;

@ScenarioScoped
public class ScenarioTagsHelper {

    private final List<String> tags = new ArrayList<String>();

    public void addAll(Iterable<String> newTags) {
        for (String tag : newTags) {
            tags.add(tag.replace("@", ""));
        }
    }

    public List<String> getTags() {
        return tags;
    }

    public void clear() {
        this.tags.clear();
    }
}
