package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.interfaces.Resource;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
    @Nullable
    ClassLoader getClassLoader();
}
