package ru.astondev.context.context;

import java.util.HashMap;
import java.util.Map;

public class DefaultBootstrapContext {

    private final Map<Class<?>, Object> instances = new HashMap();


    public DefaultBootstrapContext() {
    }

}
