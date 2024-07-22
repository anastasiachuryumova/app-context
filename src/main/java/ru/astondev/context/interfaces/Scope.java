package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.factories.ObjectFactory;

public interface Scope {
    Object get(String name, ObjectFactory<?> objectFactory);

    @Nullable
    Object remove(String name);

    void registerDestructionCallback(String name, Runnable callback);

    @Nullable
    Object resolveContextualObject(String key);

    @Nullable
    String getConversationId();
}
