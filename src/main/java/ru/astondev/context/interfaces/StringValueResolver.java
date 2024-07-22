package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;

@FunctionalInterface
public interface StringValueResolver {
    @Nullable
    String resolveStringValue(String strVal);
}
