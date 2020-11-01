package com.poshmark.assignment.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class PrintExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(Exclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
