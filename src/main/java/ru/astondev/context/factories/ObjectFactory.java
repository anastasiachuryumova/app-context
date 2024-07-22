package ru.astondev.context.factories;

import ru.astondev.context.exceptions.BeansException;

@FunctionalInterface
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
