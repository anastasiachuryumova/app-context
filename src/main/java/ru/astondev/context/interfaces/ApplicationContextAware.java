package ru.astondev.context.interfaces;

import ru.astondev.context.context.ApplicationContext;
import ru.astondev.context.exceptions.BeansException;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
