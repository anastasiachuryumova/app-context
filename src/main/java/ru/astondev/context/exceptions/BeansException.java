package ru.astondev.context.exceptions;

import ru.astondev.context.annotations.Nullable;

public class BeansException extends Exception {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
