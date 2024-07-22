package ru.astondev.context.exceptions;

import ru.astondev.context.annotations.Nullable;

public class NoSuchBeanDefinitionException extends Exception {
    public NoSuchBeanDefinitionException(String msg) {
        super(msg);
    }

    public NoSuchBeanDefinitionException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
