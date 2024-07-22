package ru.astondev.context.context;

import ru.astondev.context.interfaces.ApplicationEventPublisher;
import ru.astondev.context.interfaces.EnvironmentCapable;
import ru.astondev.context.interfaces.MessageSource;
import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.factories.AutowireCapableBeanFactory;
import ru.astondev.context.factories.HierarchicalBeanFactory;
import ru.astondev.context.factories.ListableBeanFactory;

public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory,
        HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher {
    @Nullable
    String getId();

    String getApplicationName();

    String getDisplayName();

    long getStartupDate();

    @Nullable
    ApplicationContext getParent();

    AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;
}

