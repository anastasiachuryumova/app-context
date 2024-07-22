package ru.astondev.context.interfaces;

import ru.astondev.context.exceptions.BeansException;
import ru.astondev.context.factories.ConfigurableListableBeanFactory;

@FunctionalInterface
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
