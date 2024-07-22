package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.context.BeanExpressionContext;
import ru.astondev.context.exceptions.BeansException;

public interface BeanExpressionResolver {
    @Nullable
    Object evaluate(@Nullable String value, BeanExpressionContext beanExpressionContext) throws BeansException;
}

