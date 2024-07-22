package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.TypeMismatchException;

import java.lang.reflect.Field;

public interface TypeConverter {
    @Nullable
    <T> T convertIfNecessary(@Nullable Object value, @Nullable Class<T> requiredType) throws TypeMismatchException;

    @Nullable
    <T> T convertIfNecessary(@Nullable Object value, @Nullable Class<T> requiredType, @Nullable Object obj) throws TypeMismatchException;

    @Nullable
    <T> T convertIfNecessary(@Nullable Object value, @Nullable Class<T> requiredType, @Nullable Field field) throws TypeMismatchException;

    @Nullable
    default <T> T convertIfNecessary(@Nullable Object value, @Nullable Class<T> requiredType, @Nullable String s) throws TypeMismatchException {
        throw new UnsupportedOperationException("TypeDescriptor resolution not supported");
    }
}
