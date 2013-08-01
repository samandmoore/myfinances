package com.myfinances.common;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: documentation
 *
 * @author Sam Moore
 * @since 7/31/13 11:31 PM
 */
public class ModelState {

    public static final ModelState EMPTY = new ModelState() {
        @Override
        public void add(String key, String... errorMessages) {
            // noop
        }
    };

    private Map<String, List<String>> errors = new HashMap<>();

    public void add(String key, String... errorMessages) {
        List<String> specificErrors = errors.get(key);

        if (specificErrors == null) {
            specificErrors = Lists.newArrayList();
        }

        for (String e : errorMessages) {
            specificErrors.add(e);
        }

        errors.put(key, specificErrors);
    }

    public Map<String, List<String>> asMap() {
        return Collections.unmodifiableMap(errors);
    }
}
