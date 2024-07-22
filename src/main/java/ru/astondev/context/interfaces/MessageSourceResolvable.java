package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;

@FunctionalInterface
public interface MessageSourceResolvable {
    @Nullable
    String[] getCodes();

    @Nullable
    default Object[] getArguments() {
        return null;
    }

    @Nullable
    default String getDefaultMessage() {
        return null;
    }
}
