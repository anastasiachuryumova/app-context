package ru.astondev.context.exceptions;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.BeansException;

public class FatalBeanException extends BeansException {
    public FatalBeanException(String msg) {
        super(msg);
    }

    public FatalBeanException(String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
