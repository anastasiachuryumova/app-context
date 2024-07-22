package ru.astondev.context.classes;

import ru.astondev.context.annotations.Nullable;

import java.util.function.Supplier;

public class Assert {
    public Assert() {
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void notNull(@Nullable Object object, String messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }
    @Nullable
    private static String nullSafeGet(@Nullable String messageSupplier) {
        return messageSupplier != null ? (String)messageSupplier : null;
    }
    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(String.valueOf(messageSupplier)));
        }
    }
}
