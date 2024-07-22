package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;

public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    @Nullable
    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

    int getSingletonCount();

    Object getSingletonMutex();
}
