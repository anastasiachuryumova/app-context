package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;

import java.util.function.Supplier;

public interface StartupStep {
    String getName();

    long getId();

    @Nullable
    Long getParentId();

    StartupStep tag(String key, String value);

    StartupStep tag(String key, Supplier<String> value);

    StartupStep.Tags getTags();

    void end();

    public interface Tag {
        String getKey();

        String getValue();
    }

    public interface Tags extends Iterable<StartupStep.Tag> {
    }
}
