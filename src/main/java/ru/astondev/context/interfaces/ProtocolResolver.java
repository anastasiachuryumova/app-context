package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;

@FunctionalInterface
public interface ProtocolResolver {
    @Nullable
    Object resolve(String location, Object resourceLoader);
}
