package ru.astondev.context.factories;

import ru.astondev.context.interfaces.BeanDefinition;
import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.BeansException;
import ru.astondev.context.exceptions.NoSuchBeanDefinitionException;

import java.util.Iterator;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory,
        AutowireCapableBeanFactory, ConfigurableBeanFactory {
    void ignoreDependencyType(Class<?> type);

    void ignoreDependencyInterface(Class<?> ifc);

    void registerResolvableDependency(Class<?> dependencyType, @Nullable Object autowiredValue);

    boolean isAutowireCandidate(String beanName,Object obj) throws NoSuchBeanDefinitionException;

    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    Iterator<String> getBeanNamesIterator();

    void clearMetadataCache();

    void freezeConfiguration();

    boolean isConfigurationFrozen();

    void preInstantiateSingletons() throws BeansException;
}
